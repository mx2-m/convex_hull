package com.company;

import java.util.ArrayList;

public class QuickHull {


    private ArrayList<Point> solution = new ArrayList<>();

    public int orientation(Point p1, Point p2, Point p3) {
        double cp1 = (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }

    public double distance(Point p1, Point p2, Point p3) {
        double ABx = p2.x - p1.x;
        double ABy = p2.y - p1.y;
        double num = ABx * (p1.y - p3.y) - ABy * (p1.x - p3.x);
        if (num < 0)
            num = -num;
        return num;
    }


    public ArrayList<Point> quickHull(ArrayList<Point> points) {

        Point min = points.get(0);

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x < min.x) {
                min = points.get(i);
            }
        }

        Point max = points.get(0);

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x > max.x) {
                max = points.get(i);
            }
        }

        Point E1 = min;
        Point E2 = max;

        solution.add(E1);
        solution.add(E2);
        points.remove(E1);
        points.remove(E2);

        ArrayList<Point> s1 = new ArrayList<Point>();
        ArrayList<Point> s2 = new ArrayList<Point>();

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (orientation(E1, E2, p) == -1)   //lijevo
                s1.add(p);
            else if (orientation(E1, E2, p) == 1) //desno
                s2.add(p);
        }



       /* double dist = 0;
        Point trianglePoint = s1.get(0);
        for (int i = 0; i < s1.size(); i++) {
            Point p = s1.get(i);
            double distance = distance(E1, E2, p);
            if (distance > dist) {
                dist = distance;
                trianglePoint = s1.get(i);
            }
        }

        solution.add(trianglePoint);

        ArrayList<Point> sA = new ArrayList<Point>();
        ArrayList<Point> sB = new ArrayList<Point>();

        //Vector2D v1=new Vector2D(Math.abs(trianglePoint.x-E1.x),Math.abs(trianglePoint.y-E1.y));
        // Vector2D v2=new Vector2D(Math.abs(E2.x-trianglePoint.x),Math.abs(E2.y-trianglePoint.y));


     /*   double dist2 = 0;
        Point trianglePoint2 = s2.get(0);
        for (int i = 0; i < s2.size(); i++) {
            Point p = s2.get(i);
            double distance = distance(E1, E2, p);
            if (distance > dist) {
                dist = distance;
                trianglePoint = s2.get(i);
            }
        }

        solution.add(trianglePoint2);*/

        hullSet(E1, E2, s2, solution);
        hullSet(E2, E1, s1, solution);


        return solution;

    }

    public void hullSet(Point eA, Point eB, ArrayList<Point> set, ArrayList<Point> hull) {
        int insertPosition = hull.indexOf(eB);
        if (set.size() == 0)
            return;
        if (set.size() == 1) {
            Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }

        double dist = 0;
        Point trianglePoint = set.get(0);
        for (int i = 0; i < set.size(); i++) {
            Point p = set.get(i);
            double distance = distance(eA, eB, p);
            if (distance > dist) {
                dist = distance;
                trianglePoint = set.get(i);
            }
        }

        hull.add(insertPosition, trianglePoint);
        set.remove(trianglePoint);

        ArrayList<Point> sA = new ArrayList<Point>();
        ArrayList<Point> sB = new ArrayList<Point>();


        for (int i = 0; i < set.size(); i++) {
            Point M = set.get(i);
            if (orientation(eA, trianglePoint, M) == 1) {
                sA.add(M);
            }
        }

        for (int i = 0; i < set.size(); i++) {
            Point M = set.get(i);
            if (orientation(trianglePoint, eB, M) == 1) {
                sB.add(M);
            }
        }
        hullSet(eA, trianglePoint, sA, hull);
        hullSet(trianglePoint, eB, sB, hull);

    }


     /*  public ArrayList<Point> sort(ArrayList<Point> points) {

        Point o = new Point((points.get(0).x + points.get(1).x + points.get(2).x) / 3, (points.get(0).y + points.get(1).y + points.get(2).y) / 3);
        //Vector2D oVector = new Vector2D(o.x, o.y);

        ArrayList<Point> pointsWithAngle = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {

            double theta = Math.atan2(points.get(i).x - o.x, points.get(i).y - o.y);
            if (theta < 0)
                theta = theta + 2 * Math.PI;
            pointsWithAngle.add(new Point(points.get(i).x, points.get(i).y, theta));
        }


        Collections.sort(pointsWithAngle, Point.angleCompare);
        return pointsWithAngle;
    }*/


}
