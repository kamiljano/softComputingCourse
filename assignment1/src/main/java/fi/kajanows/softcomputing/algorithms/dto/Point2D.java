package fi.kajanows.softcomputing.algorithms.dto;

/**
 * Created by kjanowsk on 2015-10-31.
 */
public class Point2D {

    private double x;
    private double y;

    public Point2D(final double x, final double y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Point2D() {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setLocation(final Point2D location) {
        this.x = location.x;
        this.y = location.y;
    }

    public void setLocation(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
