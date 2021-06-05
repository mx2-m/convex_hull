package com.company;


public class Vector2D {

    public double dX;
    public double dY;


    public Vector2D() {
        dX = dY = 0.0;
    }

    public Vector2D(double dX, double dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public String toString() {
        return "Vector(" + dX + ", " + dY + ")";
    }

    // Compute magnitude of vector

    public double length() {
        return Math.sqrt(dX * dX + dY * dY);
    }

    // Sum of two vectors

    public Vector2D add(Vector2D v1) {
        Vector2D v2 = new Vector2D(this.dX + v1.dX, this.dY + v1.dY);
        return v2;
    }

    // Subtract vector v1 from v

    public Vector2D sub(Vector2D v1) {
        Vector2D v2 = new Vector2D(this.dX - v1.dX, this.dY - v1.dY);
        return v2;
    }

    // Scale vector by a constant

    public Vector2D scale(double scaleFactor) {
        Vector2D v2 = new Vector2D(this.dX * scaleFactor, this.dY * scaleFactor);
        return v2;
    }

    // Normalize a vectors length

    public Vector2D normalize() {
        Vector2D v2 = new Vector2D();

        double length = Math.sqrt(this.dX * this.dX + this.dY * this.dY);
        if (length != 0) {
            v2.dX = this.dX / length;
            v2.dY = this.dY / length;
        }

        return v2;
    }

    // Dot product of two vectors

    public double dotProduct(Vector2D v1) {
        return this.dX * v1.dX + this.dY * v1.dY;
    }


    public double crossProduct(Vector2D v1) {
        return this.dX * v1.dY - this.dY * v1.dX;
    }

    // cos(Φ)=(A • B) / (|A| |B|)=(Ax*Bx + Ay*By) / (√(Ax2+Ay2) * √(Bx2 + By2))
    public double angle(Vector2D v1) {
        double result = this.dotProduct(v1) / (this.length() * v1.length());
        // return Math.acos(result)*(180/Math.PI);
        return Math.acos(result);
    }

    public static void main(String[] args) {
        Vector2D v = new Vector2D(3, 2);
        Vector2D v1 = new Vector2D(0, 2);

        System.out.println(v.angle(v1));
    }


}
