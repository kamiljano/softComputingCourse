package fi.kajanows.softcomputing.algorithms.individuals;

/**
 * Created by kgjan on 8.11.2015.
 */
public interface Individual <T extends Individual> {

    T plus(final T i);

    T minus(final T i);

    T makeBaby(final T i);
}
