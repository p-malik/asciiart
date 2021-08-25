package com.asciiart.service;

import com.asciiart.exception.AsciiArtRuntimeException;
import com.asciiart.model.Coordinate;
import com.asciiart.model.XYCoordinate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AsciiArtGenerator {

    private static final int IMAGE_WIDTH_IN_PX = 300;

    private static final int IMAGE_HEIGHT_IN_PX = 500;

    private static final int MINIMUM_IMAGE_PADDING_IN_PX = 50;

    public void generateMap(final XYCoordinate xyCoordinate) throws AsciiArtRuntimeException {
        int paddingBothSides = MINIMUM_IMAGE_PADDING_IN_PX * 2;

        // the actual drawing space for the map on the image
        int mapWidth = IMAGE_WIDTH_IN_PX - paddingBothSides;
        int mapHeight = IMAGE_HEIGHT_IN_PX - paddingBothSides;

        try {
            BufferedImage bufferedImage = createBufferedImage(xyCoordinate, mapHeight, mapWidth);
            // create the image file -
            ImageIO.write(bufferedImage, "PNG", new File("map-img.png"));

            //create ASCII art with 2 characters
            drawAsciiArt(bufferedImage, mapHeight, mapWidth);
        } catch (IOException e) {
            throw new AsciiArtRuntimeException(e.getMessage(), e);
        }
    }

    private BufferedImage createBufferedImage(final XYCoordinate xyCoordinate, final int mapHeight, final int mapWidth) {
        Point2D.Double maxXY = xyCoordinate.getMaxXY();
        // configuring the buffered image and graphics to draw the map
        BufferedImage bufferedImage = new BufferedImage(IMAGE_WIDTH_IN_PX,
                IMAGE_HEIGHT_IN_PX,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufferedImage.createGraphics();
        Map<RenderingHints.Key, Object> map = new HashMap<RenderingHints.Key, Object>();
        map.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        map.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        map.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RenderingHints renderHints = new RenderingHints(map);
        g.setRenderingHints(renderHints);


        // determine the width and height ratio because we need to magnify the map to fit into the given image dimension
        double mapWidthRatio = mapWidth / maxXY.x;
        double mapHeightRatio = mapHeight / maxXY.y;

        double globalRatio = Math.min(mapWidthRatio, mapHeightRatio);

        // now we need to readjust the padding to ensure the map is always drawn on the center of the given image dimension
        double heightPadding = (IMAGE_HEIGHT_IN_PX - (globalRatio * maxXY.y)) / 2;
        double widthPadding = (IMAGE_WIDTH_IN_PX - (globalRatio * maxXY.x)) / 2;

        // for each coordinate, draw the boundary using polygon
        Polygon polygon = new Polygon();
        for (Coordinate coordinate : xyCoordinate.getCoordinateList()) {
            Point2D.Double point = coordinate.getMercatorCordinates();
            if (point != null) {
                int adjustedX = (int) (widthPadding + (point.getX() * globalRatio));

                // need to invert the Y since 0,0 starts at top left
                int adjustedY = (int) (IMAGE_HEIGHT_IN_PX - heightPadding - (point.getY() * globalRatio));

                polygon.addPoint(adjustedX, adjustedY);
            }
        }

        g.draw(polygon);
        return bufferedImage;
    }

    private void drawAsciiArt(final BufferedImage bufferedImage, final int mapHeight, final int mapWidth) {
        for (int y = 0; y < mapHeight; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < mapWidth; x++) {
                sb.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : bufferedImage.getRGB(x, y) == -1 ? "#" : "*");
            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);
        }
    }
}