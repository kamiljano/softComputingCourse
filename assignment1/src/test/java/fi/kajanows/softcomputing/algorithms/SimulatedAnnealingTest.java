package fi.kajanows.softcomputing.algorithms;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kjanowsk on 2015-10-30.
 */
public class SimulatedAnnealingTest {

    @Test
    public void testFindMaximum_simple() throws Exception {
        final double coolingRate = 0.999;
        final double startingTemp = 1000;
        SimulatedAnnealing annealing = new SimulatedAnnealing(coolingRate, startingTemp, -2, 2);
        double fx = annealing.findMaximum(x -> -(x * x) + 1);
        assertEquals(1, fx, 0.01);
    }

    @Test
    public void testFindMaximum_complex() throws Exception {
        final double coolingRate = 0.999;
        final double startingTemp = 1000;
        SimulatedAnnealing annealing = new SimulatedAnnealing(coolingRate, startingTemp, -2, 2);
        double fx = annealing.findMaximum(x -> Math.sin(x)/ x);
        assertEquals(1, fx, 0.01);
    }

    @Test
    public void testFindMaximum_complex2() throws Exception {
        final double coolingRate = 0.999;
        final double startingTemp = 1000;
        SimulatedAnnealing annealing = new SimulatedAnnealing(coolingRate, startingTemp, -2, 2);
        double fx = annealing.findMaximum(x -> Math.sin(1 + x)/(2*(1 + x)));
        assertEquals(0.5, fx, 0.01);
    }
}