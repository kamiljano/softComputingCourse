package fi.kajanows.softcomputing.algorithms;

import org.junit.Test;

import fi.kajanows.softcomputing.algorithms.dto.Point2D;
import fi.kajanows.softcomputing.algorithms.dto.Point3D;

import static org.junit.Assert.*;

/**
 * Created by kjanowsk on 2015-10-30.
 */
public class SimulatedAnnealingTest {

    private static final double COOLING_RATE = 0.999;
    private static final double STARTING_TEMP = 1000;

    @Test
    public void testFindOptimum_simple() throws Exception {
        SimulatedAnnealing annealing = new SimulatedAnnealing(COOLING_RATE, STARTING_TEMP, -2, 2);
        Point2D optimum = annealing.findOptimum(x -> -(x * x) + 1);
        assertEquals(1, optimum.getY(), 0.01);
        assertEquals(0, optimum.getX(), 0.01);
    }

    @Test
    public void testFindOptimum_complex() throws Exception {
        SimulatedAnnealing annealing = new SimulatedAnnealing(COOLING_RATE, STARTING_TEMP, -2, 2);
        Point2D optimum = annealing.findOptimum(x -> Math.sin(x) / x);
        assertEquals(1, optimum.getY(), 0.01);
        assertEquals(0, optimum.getX(), 0.01);
    }

    @Test
    public void testFindOptimum_complex2() throws Exception {
        SimulatedAnnealing annealing = new SimulatedAnnealing(COOLING_RATE, STARTING_TEMP, -2, 2);
        Point2D optimum = annealing.findOptimum(x -> Math.sin(1 + x) / (2 * (1 + x)));
        assertEquals(0.5, optimum.getY(), 0.01);
        assertEquals(-1, optimum.getX(), 0.01);
    }

    @Test
    public void testFindOptimum_3d() throws Exception {
        SimulatedAnnealing annealing = new SimulatedAnnealing(COOLING_RATE, STARTING_TEMP, -2, 2);
        Point3D optimum = annealing.findOptimum((x, y) -> -(x * x) - (y * y) + 1);
        assertEquals(0, optimum.getX(), 0.01);
        assertEquals(0, optimum.getZ(), 0.01);
        assertEquals(1, optimum.getY(), 0.01);
    }

    @Test
    public void testFindOptimum_3d_actualHomeworkFunction() throws Exception {
        SimulatedAnnealing annealing = new SimulatedAnnealing(COOLING_RATE, 10000000, 0, 5);
        Point3D optimum = annealing.findOptimum((x, y) -> Math.pow((x * x) + y - 11, 2) + Math.pow((y * y) + x - 7, 2), new Point2D(2.5, 2.5));
        assertEquals(5, optimum.getX(), 0.01);
        assertEquals(5, optimum.getZ(), 0.01);
        assertEquals(890, optimum.getY(), 1);
    }

}