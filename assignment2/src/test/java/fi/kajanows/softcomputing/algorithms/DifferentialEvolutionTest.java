package fi.kajanows.softcomputing.algorithms;

import fi.kajanows.softcomputing.algorithms.individuals.DoubleIndividual;
import fi.kajanows.softcomputing.algorithms.individuals.DoubleIndividualFunction;
import fi.kajanows.softcomputing.algorithms.individuals.MultiparamDoubleFunction;
import fi.kajanows.softcomputing.algorithms.individuals.MultiparamDoubleIndividual;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kgjan on 8.11.2015.
 */
public class DifferentialEvolutionTest {

    @Test
    public void testFindOptimum() throws Exception {
        DoubleIndividualFunction function = i -> i.getValue() * i.getValue();

        DifferentialEvolution ev = new DifferentialEvolution(100, function,
                new DoubleIndividual(1555),
                new DoubleIndividual(1200),
                new DoubleIndividual(2),
                new DoubleIndividual(0.2),
                new DoubleIndividual(-2.6),
                new DoubleIndividual(4));

        System.out.println(((DoubleIndividual)ev.findOptimum()).getValue());
        assertEquals(0, ((DoubleIndividual)ev.findOptimum()).getValue(), 0.2);
    }

    @Test
    public void testFindOptimumForSphereFunction() throws Exception {
        final int n = 5;
        DoubleIndividualFunction function = individual -> {
            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum += individual.getValue() * individual.getValue();
            }
            return sum;
        };

        DifferentialEvolution ev = new DifferentialEvolution(100000, function,
                new DoubleIndividual(15),
                new DoubleIndividual(10),
                new DoubleIndividual(6),
                new DoubleIndividual(12),
                new DoubleIndividual(-2),
                new DoubleIndividual(-6));

        assertEquals(0, ((DoubleIndividual)ev.findOptimum()).getValue(), 0.2);
    }

    private void printMultipartResult(MultiparamDoubleIndividual i) {
        for (double d : i.getValue()) {
            System.out.println(d);
        }
    }

    private void assertAllParamsCloseToZero(MultiparamDoubleIndividual i) {
        for (double d : i.getValue()) {
            assertEquals(0, d, 0.1);
        }
    }

    @Test
    public void testFindOptimumForSphereFunction2() throws Exception {
        MultiparamDoubleFunction function = individual -> {
            double sum = 0;
            for (int i = 0; i < individual.getValue().length; i++) {
                sum += individual.getValue()[i] * individual.getValue()[i];
            }
            return sum;
        };

        DifferentialEvolution ev = new DifferentialEvolution(100000, function,
                new MultiparamDoubleIndividual(15, 4, 7, 20, 5),
                new MultiparamDoubleIndividual(10, 6, 2, 88, 2),
                new MultiparamDoubleIndividual(6, 3, 5, 1, 0),
                new MultiparamDoubleIndividual(0, 0, 1, 3, 0),
                new MultiparamDoubleIndividual(-2, -1, 0, 5, 6),
                new MultiparamDoubleIndividual(0, 1, 2, 3, 4));

        printMultipartResult(((MultiparamDoubleIndividual)ev.findOptimum()));
        assertAllParamsCloseToZero(((MultiparamDoubleIndividual)ev.findOptimum()));
    }

    @Test
    public void testFindOptimumForRosenbrockFunction() throws Exception {
        MultiparamDoubleFunction function = individual -> {
            double [] x = individual.getValue();
            double sum = 0;
            for (int i = 0; i < x.length - 1; i++) {
                sum += 100 * Math.pow(x[i + 1] - Math.pow(x[i], 2), 2) + Math.pow(x[i] - 1, 2);
            }
            return sum;
        };

        DifferentialEvolution ev = new DifferentialEvolution(100000, function,
                new MultiparamDoubleIndividual(15, 4, 7, 20, 5),
                new MultiparamDoubleIndividual(10, 6, 2, 88, 2),
                new MultiparamDoubleIndividual(6, 3, 5, 1, 0),
                new MultiparamDoubleIndividual(0, 0, 1, 3, 0),
                new MultiparamDoubleIndividual(-2, -1, 0, 5, 6),
                new MultiparamDoubleIndividual(0, 1, 2, 3, 4));

        printMultipartResult(((MultiparamDoubleIndividual)ev.findOptimum()));
        assertAllParamsCloseToZero(((MultiparamDoubleIndividual)ev.findOptimum()));
    }
}