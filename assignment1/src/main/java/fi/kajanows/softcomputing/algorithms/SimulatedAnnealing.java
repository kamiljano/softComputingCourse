package fi.kajanows.softcomputing.algorithms;

import java.util.Random;

import fi.kajanows.softcomputing.algorithms.dto.Point2D;
import fi.kajanows.softcomputing.algorithms.dto.Point3D;

/**
 * Created by kjanowsk on 2015-10-30.
 */
public class SimulatedAnnealing {

    public static final int NUMBER_OF_ITERATIONS_WHEN_SEARCHING_FOR_NEIGHBOUR = 1000;

    public static final double SATISFIABLE_TEMPERATURE = 0.0000001;

    private final double coolingRate;
    private final double startingTemperature;
    private final double minXY;
    private final double maxXY;
    private final Random random = new Random();

    public SimulatedAnnealing(final double coolingRate, final double startingTemperature, final double minXY, final double maxXY) {
        this.coolingRate = coolingRate;
        this.startingTemperature = startingTemperature;
        this.minXY = minXY;
        this.maxXY = maxXY;
    }

    public Point2D findOptimum(final SingleVariableFunction function) {
        return findOptimum(function, getRandom(minXY, maxXY));
    }

    public Point2D findOptimum(final SingleVariableFunction function, double current_x) {
        double temperature = startingTemperature;
        while (temperature > 0.00001) {
            double newX = getNewNeighbour(current_x, function, temperature);
            if (shouldMoveToTheNewX(current_x, newX, temperature, function)) {
                current_x = newX;
            }
            temperature *= coolingRate;
        }
        return new Point2D(current_x, function.calculate(current_x));
    }

    public Point3D findOptimum(final TwoVariableFunction function) {
        return findOptimum(function, new Point2D(getRandom(minXY, maxXY), getRandom(minXY, maxXY)));
    }

    public Point3D findOptimum(final TwoVariableFunction function, Point2D currentPoint) {
        double temperature = startingTemperature;
        while (temperature > SATISFIABLE_TEMPERATURE) {
            Point2D newPoint = getNewNeighbour(currentPoint, function, temperature);
            if (shouldMoveToTheNewPoint(currentPoint, newPoint, temperature, function)) {
                currentPoint = newPoint;
            }
            temperature *= coolingRate;
        }
        return new Point3D(currentPoint.getX(), function.calculate(currentPoint.getX(), currentPoint.getY()), currentPoint.getY());
    }

    private Point2D getNewNeighbour(final Point2D currentPoint, final TwoVariableFunction function, final double temp) {
        for (int i = 0; i < NUMBER_OF_ITERATIONS_WHEN_SEARCHING_FOR_NEIGHBOUR; i++) {
            Point2D newPoint = new Point2D(getRandom(minXY, maxXY), getRandom(minXY, maxXY));
            double currentE = function.calculate(currentPoint.getX(), currentPoint.getY());
            double newE = function.calculate(newPoint.getX(), newPoint.getY());
            double delta = newE - currentE;
            if (delta >= 0 && delta <= temp) {
                return newPoint;
            }
        }
        return currentPoint;
    }

    private double getNewNeighbour(final double x, final SingleVariableFunction function, final double temp) {
        for (int i = 0; i < NUMBER_OF_ITERATIONS_WHEN_SEARCHING_FOR_NEIGHBOUR; i++) {
            double newX = getRandom(minXY, maxXY);
            double currentE = function.calculate(x);
            double newE = function.calculate(newX);
            double delta = newE - currentE;
            if (delta >= 0 && delta <= temp) {
                return newX;
            }
        }
        return x;
    }

    private double getRandom(final double rangeMin, final double rangeMax) {
        return rangeMin + (rangeMax - rangeMin) * random.nextDouble();
    }

    private boolean shouldMoveToTheNewX(final double x, final double newX, final double temp, final SingleVariableFunction function) {
        return acceptanceProbability(function.calculate(x), function.calculate(newX), temp) >= random.nextDouble();
    }

    private boolean shouldMoveToTheNewPoint(final Point2D currentPoint, final Point2D newPoint, final double temp, final TwoVariableFunction function) {
        return acceptanceProbability(function.calculate(currentPoint.getX(), currentPoint.getY()), function.calculate(newPoint.getX(), newPoint.getY()), temp) >= random.nextDouble();
    }

    private double acceptanceProbability(final double fx1, final double fx2, final double temp) {
        if (fx2 > fx1) {
            return 1;
        }
        return Math.exp((-1) * (fx2 - fx1) / temp);
    }
}
