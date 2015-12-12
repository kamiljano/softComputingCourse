package jmetal.kajanows;

import org.junit.Test;

import jmetal.core.Solution;
import jmetal.core.SolutionSet;
import jmetal.problems.ConstrEx;
import jmetal.problems.ZDT.ZDT1;
import jmetal.util.JMException;

import static org.junit.Assert.*;

/**
 * Created by kjanowsk on 2015-12-12.
 */
public class NsgaiiTest {

    @Test
    public void testCalculate() throws JMException, ClassNotFoundException {
        Nsgaii nsgaii = new Nsgaii(new ZDT1("ArrayReal", 30));
        SolutionSet solution = nsgaii.calculate();

        solution.printVariablesToFile("ZDT1_VAR");
        solution.printObjectivesToFile("ZDT1_FUN");
    }

    @Test
    public void testCalculate2() throws JMException, ClassNotFoundException {
        Nsgaii nsgaii = new Nsgaii(new ConstrEx("Real"));
        SolutionSet solution = nsgaii.calculate();

        solution.printVariablesToFile("ConstrEx_VAR");
        solution.printObjectivesToFile("ConstrEx_FUN");
    }
}