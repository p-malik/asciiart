package com.asciiart;

import com.asciiart.converter.CoordinateConverter;
import com.asciiart.model.XYCoordinate;
import com.asciiart.service.AsciiArtGenerator;

/**
 * The main class to print ASCII art for map of UK.
 * We are using the  Mercator spherical earth projection formula to convert the
 * longitude/latitude to X/Y, so that it can be drawn on a flat surface.
 * The drawing is created using the Graphics2D API in java and
 * the image calculations are calculated and scaled in pixels.
 */
public class AsciiMain {

    public static void main(String[] args) throws Exception {

        //TODO : Take input from user for the files
        XYCoordinate xyCoordinate = CoordinateConverter.convertCordinatesToXY("ukpostcodes.csv", "src/main/resources/ukpostcodes.csv.zip");

        AsciiArtGenerator service = new AsciiArtGenerator();
        service.generateMap(xyCoordinate);
    }
}
