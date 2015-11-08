package fi.kajanows.softcomputing.algorithms;

/**
 * Created by kgjan on 8.11.2015.
 */
public class TooSmallPopulationException extends Exception {

    public TooSmallPopulationException() {
        super("The population has to contain at least 4 individuals");
    }
}
