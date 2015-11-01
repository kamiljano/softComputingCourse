package fi.kajanows.softcomputing.algorithms.dto;

/**
 * Created by kjanowsk on 2015-11-01.
 */
public class Point3D {

    private double x;
    private double y;
    private double z;

    public Point3D(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
