package com.asciiart.model;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Objects;

public class XYCoordinate {
    List<Coordinate> coordinateList;
    Point2D.Double maxXY;

    public XYCoordinate(List<Coordinate> coordinateList, Point2D.Double maxXY) {
        this.coordinateList = coordinateList;
        this.maxXY = maxXY;
    }

    public List<Coordinate> getCoordinateList() {
        return coordinateList;
    }

    public Point2D.Double getMaxXY() {
        return maxXY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XYCoordinate that = (XYCoordinate) o;
        return Objects.equals(coordinateList, that.coordinateList) &&
                Objects.equals(maxXY, that.maxXY);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coordinateList, maxXY);
    }
}
