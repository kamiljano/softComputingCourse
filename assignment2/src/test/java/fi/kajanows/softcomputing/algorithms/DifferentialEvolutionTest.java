package fi.kajanows.softcomputing.algorithms;

import org.junit.Test;

/**
 * Created by kgjan on 8.11.2015.
 */
public class DifferentialEvolutionTest {

    @Test
    public void testFindOptimum() throws Exception {
        IntegerIndividualFunction function = i -> i.getValue() * i.getValue();

        DifferentialEvolution ev = new DifferentialEvolution(10000, function,
                new IntegerIndividual(15),
                new IntegerIndividual(10),
                new IntegerIndividual(6),
                new IntegerIndividual(12),
                new IntegerIndividual(-2),
                new IntegerIndividual(-6));

        System.out.println(((IntegerIndividual)ev.findOptimum()).getValue());
    }

    @Test
    public void test() {
        System.out.println(Integer.toBinaryString(-7));
        System.out.println(Integer.parseUnsignedInt("11111111111111111111111111111001", 2));
        System.out.println(Integer.parseUnsignedInt("111", 2));
    }
}