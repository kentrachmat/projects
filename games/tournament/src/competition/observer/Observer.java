package competition.observer;

import competition.Competitor;

/**
 * An interface to design all types of Observers.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public interface Observer {
    /**
     * allows observers to do some things with the results of a match
     *
     * @param c1     the first competitor
     * @param c2     the second competitor
     * @param winner the winning competitor
     */
    public void update(Competitor c1, Competitor c2, Competitor winner);
}