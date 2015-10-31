package fi.kajanows.softcomputing.algorithms.dto;

/**
 * Created by kjanowsk on 2015-10-31.
 */
public class Point {

    private double x;
    private double y;

    public Point(final double x, final double y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Point() {

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setLocation(final Point location) {
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
