package fi.kajanows.softcomputing.algorithms;

/**
 * Created by kgjan on 8.11.2015.
 */
public interface IndividualFunction <T extends Individual> {

    double calculate(final T i);

}
