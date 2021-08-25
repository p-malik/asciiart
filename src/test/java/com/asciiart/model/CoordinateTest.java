package com.asciiart.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;

public class CoordinateTest {
    @Test
    public void testCoordinateConversion() {
        Point2D.Double expMercatorCordinates = new Point2D.Double(4898057.594904037, 2511525.234845713);
        Coordinate coordinate = new Coordinate(1, "test", 22, 44);
        Assertions.assertEquals(expMercatorCordinates, coordinate.getMercatorCordinates());
    }
}
