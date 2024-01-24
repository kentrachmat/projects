package competition;

import competition.observer.Observer;

/**
 * An interface to design all types of matches.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public interface Match {

    /**
     * play a Match with two competitors
     *
     * @param c1 the first competitor
     * @param c2 the second competitor
     * @return the competitor who won the match
     */
    Competitor playMatch(Competitor c1, Competitor c2);

    
    /** add an observer to this match
     * @param observer the observer
     */
    public void addObserver(Observer observer);

    /** remove an observer to this match
     * @param observer the observer
     */
    public void removeObserver(Observer observer);

    /** notify observers of the result of a match
     * @param c1 the first competitor
     * @param c2 the second competitor
     * @param winner the winning competitor
     */
    public void notifyObservers(Competitor c1, Competitor c2, Competitor winner);

}