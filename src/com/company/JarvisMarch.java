package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

public class JarvisMarch {

    private ArrayList<Point> solution = new ArrayList<>();


    public double evklidskaRazdalja(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y1 - y2) * (y1 - y2) + (x1 - x2) * (x1 - x2));
    }

    boolean equal(double v1, double v2) {
        double eps = 0.000001;
        if (Math.abs(v1 - v2) < eps)
            return true;
        else
            return false;
    }


    public ArrayList<Point> jarvisMarch(ArrayList<Point> points) {

        Point min = points.get(0);

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).y > min.y) {
                min = points.get(i);
            }
        }
        points.remove(min);
        solution.add(min);

        Point s1 = points.get(0);

        double temp = 3.14;
        for (int i = 0; i < points.size(); i++) {
            Vector2D minV = new Vector2D(min.x, min.y);
            Vector2D nextPoint = new Vector2D(points.get(i).x - min.x, points.get(i).y - min.y);

            double theta = nextPoint.angle(minV.normalize());

            if (theta < temp) {
                s1 = points.get(i);
                temp = theta;
            } else if (equal(theta, temp)) {    //ako su uglovi isti prednost ima tacka koja ima manju evklidsku razdalju
                double razdalja1 = evklidskaRazdalja(points.get(i).x, points.get(i).y, min.x, min.y);
                double razdalja2 = evklidskaRazdalja(points.get(i - 1).x, points.get(i - 1).y, min.x, min.y);
                if (razdalja1 < razdalja2) {
                    s1 = points.get(i);
                    temp = theta;
                } else {
                    s1 = points.get(i - 1);
                    temp = theta;
                }
            }
        }

        points.add(min);

        do {
            solution.add(s1);
            points.remove(s1);
            double tmp = 3.14;
            int i = solution.size() - 1;

            for (int j = 0; j < points.size(); j++) {
                Vector2D A = new Vector2D(solution.get(i).x - solution.get(i - 1).x, solution.get(i).y - solution.get(i - 1).y);
                Vector2D B = new Vector2D(points.get(j).x - solution.get(i).x, points.get(j).y - solution.get(i).y);

                double theta = B.angle(A.normalize());
                if (theta < tmp) {
                    s1 = points.get(j);
                    tmp = theta;
                }
            }
        } while (min.x != s1.x | min.y != s1.y);


        return solution;
    }


    public static void main(String[] args) {
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(0, 3));
        points.add(new Point(2, 3));
        points.add(new Point(1, 1));
        points.add(new Point(2, 1));
        points.add(new Point(3, 0));
        points.add(new Point(0, 0));
        points.add(new Point(3, 3));

        ArrayList<Point> solution = new JarvisMarch().jarvisMarch(points);
        for (int i = 0; i < solution.size(); i++)
            System.out.println(solution.get(i).x + " " + solution.get(i).y);


    }


}
