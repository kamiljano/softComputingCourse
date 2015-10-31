package fi.kajanows.softcomputing.algorithms;

import org.junit.Test;

import fi.kajanows.softcomputing.algorithms.dto.Point;

import static org.junit.Assert.*;

/**
 * Created by kjanowsk on 2015-10-31.
 */
public class SteepestDescentTest {

    @Test
    public void testFindLocalOptimum() throws Exception {
        SteepestDescent algorithm = new SteepestDescent(0.00001, 0.01, new Point(10, 10));
        Point p = algorithm.findLocalOptimum();
        assertEquals(0.5, p.getX(), 0.01);
        assertEquals(0.67, p.getY(), 0.01);
    }
}