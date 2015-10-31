package fi.kajanows.softcomputing.algorithms;

import fi.kajanows.softcomputing.algorithms.dto.Point;

/**
 * Created by kjanowsk on 2015-10-31.
 */
public class SteepestDescent {

    private final double precision;
    private final double stepSize;
    private final Point startingPoint;

    public SteepestDescent(final double precision, final double stepSize, final Point startingPoint) {
        this.precision = precision;
        this.stepSize = stepSize;
        this.startingPoint = startingPoint;
    }

    private double der1(final double x1) {
        return 8 * x1 - 4;
    }

    private double der2(final double x2) {
        return 6 * x2 - 4;
    }

    private double calculateNextPosition1(final double x1) {
        return x1 - stepSize * der1(x1);
    }

    private double calculateNextPosition2(final double x2) {
        return x2 - stepSize * der2(x2);
    }

    private boolean shouldIterate(final Point oldPoint, final Point newPoint) {
        return (Math.abs(newPoint.getX() - oldPoint.getX()) + Math.abs(newPoint.getY() - oldPoint.getY())) > precision;
    }

    private Point calculateNewPosition(final Point oldPoint) {
        return new Point(calculateNextPosition1(oldPoint.getX()), calculateNextPosition2(oldPoint.getY()));
    }

    public Point findLocalOptimum() {
        final Point oldPoint = new Point();
        final Point newPoint = new Point(startingPoint.getX(), startingPoint.getY());
        do {
            oldPoint.setLocation(newPoint);
            newPoint.setLocation(calculateNewPosition(oldPoint));
        } while(shouldIterate(oldPoint, newPoint));
        return oldPoint;
    }
}
