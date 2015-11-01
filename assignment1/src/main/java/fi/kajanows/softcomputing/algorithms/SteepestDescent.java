package fi.kajanows.softcomputing.algorithms;

import fi.kajanows.softcomputing.algorithms.dto.Point2D;

/**
 * Created by kjanowsk on 2015-10-31.
 */
public class SteepestDescent {

    private final double precision;
    private final double stepSize;
    private final Point2D startingPoint;

    public SteepestDescent(final double precision, final double stepSize, final Point2D startingPoint) {
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

    private boolean shouldIterate(final Point2D oldPoint, final Point2D newPoint) {
        return (Math.abs(newPoint.getX() - oldPoint.getX()) + Math.abs(newPoint.getY() - oldPoint.getY())) > precision;
    }

    private Point2D calculateNewPosition(final Point2D oldPoint) {
        return new Point2D(calculateNextPosition1(oldPoint.getX()), calculateNextPosition2(oldPoint.getY()));
    }

    public Point2D findLocalOptimum() {
        final Point2D oldPoint = new Point2D();
        final Point2D newPoint = new Point2D(startingPoint.getX(), startingPoint.getY());
        do {
            oldPoint.setLocation(newPoint);
            newPoint.setLocation(calculateNewPosition(oldPoint));
        } while(shouldIterate(oldPoint, newPoint));
        return oldPoint;
    }
}
