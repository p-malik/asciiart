package com.asciiart.converter;

import com.asciiart.exception.AsciiArtRuntimeException;
import com.asciiart.exception.ValidationException;
import com.asciiart.model.Coordinate;
import com.asciiart.model.XYCoordinate;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CoordinateConverter {

    public static XYCoordinate convertCordinatesToXY(String fileName, String zipFilePath) throws AsciiArtRuntimeException {

        try (ZipFile zipFile = new ZipFile(zipFilePath)) {
            Predicate<ZipEntry> isFile = ze -> !ze.isDirectory();
            Predicate<ZipEntry> isCSV = ze -> ze.getName().matches(".*csv");
            Predicate<ZipEntry> isFileNameCorrect = ze -> ze.getName().matches(fileName);

            List<Coordinate> coordinateList = zipFile.stream()
                    .filter(isFile.and(isCSV).and(isFileNameCorrect))
                    //.collect(this::buildCoordinateList);
                    .flatMap(ze -> buildCoordinateList(zipFile, ze).stream())
                    .collect(Collectors.toList());

            //Need min and max coordinates to get the relative position
            // and deduce the global ratio for scaling the map
            //Need to do 2 loops, so that min can be deducted from xy points, to move the map.
            Point2D.Double minXY = findMinCoordinate(coordinateList);
            Point2D.Double maxXY = findMaxCoordinate(coordinateList, minXY);

            return new XYCoordinate(coordinateList, maxXY);

        } catch (IOException e) {
            // error while opening a ZIP file
            throw new AsciiArtRuntimeException(e.getMessage(), e);
        }
    }

    private static Point2D.Double findMinCoordinate(List<Coordinate> coordinateList) {
        Point2D.Double minXY = new Point2D.Double(-1, -1);
        coordinateList.forEach(coordinate -> {
            Point2D.Double xy = coordinate.getMercatorCordinates();
            if (xy != null) {
                // The reason we need to determine the min X and Y values is because in order to draw the map,
                // we need to offset the position so that there will be no negative X and Y values
                minXY.x = (minXY.x == -1) ? xy.x : Math.min(minXY.x, xy.x);
                minXY.y = (minXY.y == -1) ? xy.y : Math.min(minXY.y, xy.y);
            }
        });
        return minXY;
    }

    private static Point2D.Double findMaxCoordinate(List<Coordinate> coordinateList, Point2D.Double minXY) {
        Point2D.Double maxXY = new Point2D.Double(-1, -1);
        coordinateList.forEach(coordinate -> {
            Point2D.Double xy = coordinate.getMercatorCordinates();
            if (xy != null) {
                xy.x = xy.x - minXY.x;
                xy.y = xy.y - minXY.y;

                // now, we need to keep track the max X and Y values
                maxXY.x = (maxXY.x == -1) ? xy.x : Math.max(maxXY.x, xy.x);
                maxXY.y = (maxXY.y == -1) ? xy.y : Math.max(maxXY.y, xy.y);
            }
        });
        return maxXY;
    }

    private static List<Coordinate> buildCoordinateList(ZipFile zipFile, ZipEntry zipEntry) {
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        try (InputStream inputStream = zipFile.getInputStream(zipEntry);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String headers = reader.readLine(); // this will read the first line
            if (headers == null) {
                throw new ValidationException("first line of the postcodes csv file is missing " + zipEntry.getName());
            }

            if (!headers.contains("id,postcode,latitude,longitude")) {
                throw new ValidationException(
                        "first line of the extent map csv file is incorrect and not \"" + headers + "\"");
            }

            Pattern pattern = Pattern.compile(",");
            coordinateList = reader.lines().map(line -> {
                String[] columns = pattern.split(line);
                return new Coordinate(Long.parseLong(columns[0]), columns[1], Double.parseDouble(columns[2]), Double.parseDouble(columns[3]));
            }).collect(Collectors.toList());

            return coordinateList;

        } catch (IOException e) {
            throw new AsciiArtRuntimeException(e.getMessage(), e);
        }
    }
}
