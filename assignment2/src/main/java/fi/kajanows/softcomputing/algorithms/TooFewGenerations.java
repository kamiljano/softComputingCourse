package fi.kajanows.softcomputing.algorithms;

/**
 * Created by kgjan on 8.11.2015.
 */
public class TooFewGenerations extends Exception {

    public TooFewGenerations() {
        super("Too few generations. To make the algorithm work there has to be at least 1");
    }
}
