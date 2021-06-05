package com.company;

import java.util.*;

public class GrahamsScan {

    Point o;

    public int orientation(Point p0, Point p1, Point p2) {
        double cp1 = (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y);
        if (cp1 > 0)
            return 1;
        else if (cp1 == 0)
            return 0;
        else
            return -1;
    }

    /*public static void swap(Point c1, Point c2) {
        Point temp = c1;
        c1 = c2;
        c2 = temp;
    }*/

/*
    public ArrayList<Point> grahamsScan(ArrayList<Point> points) {

        o = new Point((points.get(0).x + points.get(1).x + points.get(2).x) / 3, (points.get(0).y + points.get(1).y + points.get(2).y) / 3);

        ArrayList<Point> pointsWithAngle = new ArrayList<>();
        ArrayList<Point> solution= new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {

            double theta = Math.atan2(points.get(i).x - o.x, points.get(i).y - o.y);
            if (theta < 0)
                theta = theta + 2 * Math.PI;
            pointsWithAngle.add(new Point(points.get(i).x, points.get(i).y, theta));
        }

        //Collections.sort(pointsWithAngle, Point.yCompare);

        Collections.sort(pointsWithAngle, Point.angleCompare);

        Point e = pointsWithAngle.get(0);


        for (int i = 0; i < pointsWithAngle.size(); i++) {
            if (pointsWithAngle.get(i).y > e.y) {
                e = pointsWithAngle.get(i);

            }
        }

        int j = pointsWithAngle.indexOf(e);
      /* Point p1 = pointsWithAngle.get(j);
        Point p2 = pointsWithAngle.get(j + 1);
        Point p3 = pointsWithAngle.get(j + 2);*/


     /*  solution.add(e);

        Collections.swap(pointsWithAngle, 0, j);


        Point p1 = pointsWithAngle.get(0);
        Point p2 = pointsWithAngle.get(1);
        Point p3 = pointsWithAngle.get(2);


        do {


            for (int i = 3; i < pointsWithAngle.size(); i++) {


                if (orientation(p1, p2, p3) == -1) {      //desna usmjerenost
                    solution.remove(p2);

                    p1 = pointsWithAngle.get(i - 1);
                    p2 = pointsWithAngle.get(i);
                    if (pointsWithAngle.size() > i + 1)
                    p3 = pointsWithAngle.get(i + 1);

                } else if (orientation(p1, p2, p3) == 1) {      //leva usmjerenost
                    solution.add(p2);

                    p1 = pointsWithAngle.get(i + 1);

                    if (pointsWithAngle.size() > i + 3) {
                        p2 = pointsWithAngle.get(i + 2);
                        p3 = pointsWithAngle.get(i + 3);

                    }
                }
            }


        } while (p2 != e);


        return solution;
    }*/


    //
    public ArrayList<Point> grahamsScan(ArrayList<Point> points) {
        Deque<Point> stack = new ArrayDeque<Point>();


        Collections.sort(points, Point.yCompare);

        o = points.get(0);
        Collections.sort(points, (b, c) -> {
            /*
             * the ref point should always be pushed to the beginning
             */
            if (b == o) return -1;
            if (c == o) return 1;

            int ccw = orientation(o, b, c);

            if (ccw == 0) {

                if (Double.compare(b.x, c.x) == 0) {

                    return b.y < c.y ? -1 : 1;
                } else {
                    return b.x < c.x ? -1 : 1;
                }
            } else {
                return ccw * -1;
            }
        });
        for (int i = 0; i < points.size(); i++)
            stack.push(points.get(i));
        stack.push(o);
        stack.push(points.get(1));

        for (int i = 2, size = points.size(); i < size; i++) {
            Point next = points.get(i);
            Point p = stack.pop();

            while (stack.peek() != null && orientation(stack.peek(), p, next) <= 0) {
                p = stack.pop();
            }

            stack.push(p);
            stack.push(points.get(i));
        }

       /* Point p = stack.pop();
        if (orientation(stack.peek(), p, o) > 0) {
            stack.push(p);
        }*/

        return new ArrayList<>(stack);
    }


    public Point getO() {
        return o;
    }

    public static void main(String[] args) {
        ArrayList<Point> solution = new ArrayList<>();
        Point a = new Point(1, 2, 1.4);
        Point b = new Point(2, 1, 1);
        Point c = new Point(5, 4, 0);

        solution.add(a);
        solution.add(c);
        solution.add(b);

        Collections.sort(solution, Point.angleCompare);
        //Collections.sort(solution);

        for (Point str : solution) {
            System.out.println(str.x);
        }


    }
}
