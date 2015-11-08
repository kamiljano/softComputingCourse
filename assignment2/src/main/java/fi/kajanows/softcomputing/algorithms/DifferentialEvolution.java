package fi.kajanows.softcomputing.algorithms;

import java.util.Random;

/**
 * Created by kgjan on 8.11.2015.
 */
public class DifferentialEvolution {

    private static final int MINIMUM_NUMBER_OF_INDIVIDUALS = 4;

    private final Random random = new Random();

    private final Individual[] individuals;

    private final int numberOfGenerations;

    private IndividualFunction function;

    public DifferentialEvolution(int numberOfGenerations, IndividualFunction function, Individual... individuals) throws TooSmallPopulationException, TooFewGenerations {
        if (individuals.length < MINIMUM_NUMBER_OF_INDIVIDUALS) {
            throw new TooSmallPopulationException();
        }
        if (numberOfGenerations < 1) {
            throw new TooFewGenerations();
        }
        this.individuals = individuals;
        this.numberOfGenerations = numberOfGenerations;
        this.function = function;
    }

    private Individual chooseRandomAgent() {
        return individuals[random.nextInt(individuals.length)];
    }

    private boolean isDistinct(final Individual agent, final Individual[] alreadySelectedIndividuals, final Individual toBeTested) {
        if (agent == toBeTested) {
            return false;
        }
        for (Individual i : alreadySelectedIndividuals) {
            if (i == toBeTested) {
                return false;
            }
        }
        return true;
    }

    private Individual[] pickThreeRandomDistinctIndividualsOtherThanAgent(final Individual agent) {
        Individual[] result = new Individual[3];
        for (int i = 0; i < 3; i++) {
            Individual abc;
            do {
                abc = individuals[random.nextInt(individuals.length)];
            } while(!isDistinct(agent, result, abc));
            result[i] = abc;
        }
        return result;
    }

    private Individual createDonor(final Individual[] abc) {
        return abc[0].plus(abc[1].minus(abc[2]));
    }

    private void replaceFatherWithBaby(Individual father, Individual baby) {
        for (int i = 0; i < individuals.length; i++) {
            if (individuals[i] == father) {
                individuals[i] = baby;
                break;
            }
        }
    }

    private boolean babyIsBetterThanFather(final Individual baby, final Individual father) {
        return function.calculate(baby) < function.calculate(father);
    }

    public Individual findOptimum() {
        Individual baby = null;
        for (int i = 0; i < numberOfGenerations; i++) {
            Individual father = chooseRandomAgent();
            Individual abc[] = pickThreeRandomDistinctIndividualsOtherThanAgent(father);
            Individual mother = createDonor(abc);
            baby = father.makeBaby(mother);
            if (babyIsBetterThanFather(baby, father)) {
                replaceFatherWithBaby(father, baby);
            }
        }
        return baby;
    }
}
