package fi.kajanows.softcomputing.algorithms;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by kgjan on 8.11.2015.
 */
public class DifferentialEvolutionTest {

    @Test
    public void testFindOptimum() throws Exception {
        IntegerIndividualFunction function = i -> i.getValue() * i.getValue() - (2 * i.getValue()); // the minimum is 1,-1

        DifferentialEvolution ev = new DifferentialEvolution(1000000, function,
                new IntegerIndividual(15),
                new IntegerIndividual(8),
                new IntegerIndividual(2),
                new IntegerIndividual(30));

        //because in this case we're working on the assumption that we only have integer numbers, the results +/- 4 should be considered correct
        int v = ((IntegerIndividual)ev.findOptimum()).getValue();
        assertTrue(v <= 4 );
    }
}