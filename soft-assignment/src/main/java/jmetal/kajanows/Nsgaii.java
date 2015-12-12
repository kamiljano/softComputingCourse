package jmetal.kajanows;

import java.util.HashMap;

import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.metaheuristics.nsgaII.NSGAII;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.problems.ZDT.ZDT1;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.JMException;

/**
 * Created by kjanowsk on 2015-12-12.
 */
public class Nsgaii {

    private final Problem problem;
    private Algorithm algorithm;
    private Operator crossover;
    private Operator mutation;
    private Operator selection;

    public Nsgaii(final Problem problem) throws JMException {
        this.problem = problem;
        initAlgorithm(problem);
        initCrossover();
        initMutation();
        selection = SelectionFactory.getSelectionOperator("BinaryTournament2", null);

        algorithm.addOperator("crossover",crossover);
        algorithm.addOperator("mutation",mutation);
        algorithm.addOperator("selection",selection);
        algorithm.setInputParameter("indicators", null) ;
    }

    private void initAlgorithm(final Problem problem) {
        this.algorithm = new NSGAII(problem);
        algorithm.setInputParameter("populationSize",100);
        algorithm.setInputParameter("maxEvaluations",25000);
    }

    private void initCrossover() throws JMException {
        HashMap<String, Double> parameters = new HashMap<>();
        parameters.put("probability", 0.9) ;
        parameters.put("distributionIndex", 20.0) ;
        this.crossover = CrossoverFactory.getCrossoverOperator("SBXCrossover", parameters);
    }

    private void initMutation() throws JMException {
        HashMap<String, Double>parameters = new HashMap<>() ;
        parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
        parameters.put("distributionIndex", 20.0) ;
        mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);
    }

    public SolutionSet calculate() throws JMException, ClassNotFoundException {
        return algorithm.execute();
    }

}
