package com.company;

import java.util.Comparator;

public class Point {
    double x;
    double y;
    double angle;
    GrahamsScan gs = new GrahamsScan();
     Point  initial =gs.getO();

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y, double angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public double y() {
        return y;
    }

    /* @Override
    public int compareTo(Object o) {
        double compareage=((Point)o).getAngle();
        return Double.compare(this.angle,compareage);

    }*/

    public static Comparator<Point> angleCompare = new Comparator<Point>() {

        public int compare(Point p1, Point p2) {

            double point1 = p1.getAngle();
            double point2 = p2.getAngle();

            /*For ascending order*/
            return Double.compare(point1, point2);

        }
    };

    public static Comparator<Point> yCompare = new Comparator<Point>() {

        public int compare(Point p1, Point p2) {

            double point1 = p1.y;
            double point2 = p2.y;

            return Double.compare(point2, point1);

        }
    };




}
