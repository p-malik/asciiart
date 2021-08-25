package com.asciiart.model;

import java.awt.geom.Point2D;
import java.util.Objects;

public class Coordinate {
    final static double RADIUS_MAJOR = 6378137.0;
    final static double RADIUS_MINOR = 6356752.3142;

    //id,postcode,latitude,longitude
    private final long id;
    private final String postcode;

    private final Point2D.Double mercatorCordinates;


    public Coordinate(long id, String postcode, double latitude, double longitude) {
        this.id = id;
        this.postcode = postcode;
        this.mercatorCordinates = lonLatToMercator(longitude, latitude);
    }

    public long getId() {
        return id;
    }

    public String getPostcode() {
        return postcode;
    }

    public Point2D.Double getMercatorCordinates() {
        return mercatorCordinates;
    }

    //convert the longitude/latitude to X/Y using Mercator projection formula
    private Point2D.Double lonLatToMercator(final double longitude, final double latitude) {
        Point2D.Double xy = new Point2D.Double();
        xy.x = xAxisProjection(longitude);
        xy.y = yAxisProjection(latitude);
        if (Double.isNaN(xy.x) || Double.isNaN(xy.y)) {
            //Ignore those coordiantes - TODO : Make this handling better
            return null;
        }

        return xy;
    }

    private double xAxisProjection(double input) {
        return Math.toRadians(input) * RADIUS_MAJOR;
    }

    private double yAxisProjection(double input) {
        return Math.log(Math.tan(Math.PI / 4 + Math.toRadians(input) / 2)) * RADIUS_MAJOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return id == that.id &&
                Objects.equals(postcode, that.postcode) &&
                Objects.equals(mercatorCordinates, that.mercatorCordinates);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, postcode, mercatorCordinates);
    }
}
