package fi.kajanows.softcomputing.algorithms;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kjanowsk on 2015-10-31.
 */
public class SteepestDescentTest {

    @Test
    public void testFindMinimum() throws Exception {
        SteepestDescent algorithm = new SteepestDescent(0.01, 1e-11, 9000);
    }
}