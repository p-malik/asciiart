package com.asciiart.converter;

import com.asciiart.exception.AsciiArtRuntimeException;
import com.asciiart.model.Coordinate;
import com.asciiart.model.XYCoordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CoordinateConverterTest {
    @Test
    public void testInvalidZipFile() {
        Assertions.assertThrows(AsciiArtRuntimeException.class, () -> {
            CoordinateConverter.convertCordinatesToXY("invalid", "src/test/resources/invalid.csv.zip");
        });
    }

    @Test
    public void testNonExistantZipFile() {
        Assertions.assertThrows(AsciiArtRuntimeException.class, () -> {
            CoordinateConverter.convertCordinatesToXY("invalid", "src/test/resources/test.zip");
        });
    }

    @Test
    public void testValidZipFile() {
        Point2D.Double expMercatorCordinates = new Point2D.Double(0.0, 0.0);
        Coordinate coordinate = new Coordinate(1, "AB10 1XG", 22, 44);
        Point2D.Double xy = coordinate.getMercatorCordinates();
        xy.x = 0.0;
        xy.y = 0.0;
        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(coordinate);
        XYCoordinate expectedXYCord = new XYCoordinate(coordinateList, expMercatorCordinates);
        XYCoordinate actualXYCord = CoordinateConverter.convertCordinatesToXY("valid.csv", "src/test/resources/valid.csv.zip");
        Assertions.assertEquals(expectedXYCord, actualXYCord);
    }
}
