package fi.kajanows.softcomputing.algorithms;

/**
 * Created by kjanowsk on 2015-11-01.
 */
public interface TwoVariableFunction {

    /**
     * Calculate F(x, y)
     *
     * @param x parameter for the function to calculate the value for
     * @param y parameter for the function to calculate the value for
     * @return F(x, y)
     */
    double calculate(double x, double y);
}
