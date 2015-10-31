package fi.kajanows.softcomputing.algorithms;

import java.util.Random;

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

    public double findMaximum(final SingleVariableFunction function) {
        double temperature = startingTemperature;
        double current_x = getRandom(min_x, max_x);
        while (temperature > 0.00001) {
            double newX = getNewNeigbour(current_x, function, temperature);
            if (shouldMoveToTheNewX(current_x, newX, temperature, function)) {
                current_x = newX;
            }
            temperature *= coolingRate;
        }
        return function.calculate(current_x);
    }

    private double getNewNeigbour(double x, SingleVariableFunction function, double temp) {
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

    private boolean shouldMoveToTheNewX(double x, double newX, double temp, SingleVariableFunction function) {
        return acceptanceProbability(function.calculate(x), function.calculate(newX), temp) >= random.nextDouble();
    }

    private double acceptanceProbability(double fx1, double fx2, double temp) {
        if (fx2 > fx1) {
            return 1;
        }
        return Math.exp((-1) * (fx2 - fx1) / temp);
    }
}
