package fi.kajanows.softcomputing.algorithms;

import java.util.Random;

import fi.kajanows.softcomputing.algorithms.dto.Point;

/**
 * Created by kjanowsk on 2015-10-30.
 */
public class SimulatedAnnealing {

    private final double coolingRate;
    private final double startingTemperature;
    private final double min_x;
    private final double max_x;
    private final Random random = new Random();

    public SimulatedAnnealing(final double coolingRate, final double startingTemperature, final double min_x, final double max_x) {
        this.coolingRate = coolingRate;
        this.startingTemperature = startingTemperature;
        this.min_x = min_x;
        this.max_x = max_x;
    }

    public Point findOptimum(final SingleVariableFunction function) {
        return findOptimum(function, getRandom(min_x, max_x));
    }

    public Point findOptimum(final SingleVariableFunction function, double current_x) {
        double temperature = startingTemperature;
        while (temperature > 0.00001) {
            double newX = getNewNeighbour(current_x, function, temperature);
            if (shouldMoveToTheNewX(current_x, newX, temperature, function)) {
                current_x = newX;
            }
            temperature *= coolingRate;
        }
        return new Point(current_x, function.calculate(current_x));
    }

    private double getNewNeighbour(final double x, SingleVariableFunction function, final double temp) {
        for (int i = 0; i < 1000; i++) {
            double newX = getRandom(min_x, max_x);
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

    private double acceptanceProbability(final double fx1, final double fx2, final double temp) {
        if (fx2 > fx1) {
            return 1;
        }
        return Math.exp((-1) * (fx2 - fx1) / temp);
    }
}
