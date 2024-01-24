package competition;

import competition.observer.Observer;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class to design a RandomMatch implemented the Match interface.
 * A RandomMatch is a Match where each competitor has a 50% chance of winning.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class RandomMatch implements Match {

    private static final Random rand = new Random();

    /** the list of observers */
    private ArrayList<Observer> observers = new ArrayList<>();

    /**
     * play a match with two competitors
     *
     * @param c1 the first Competitor
     * @param c2 the second Competitor
     * @return the Competitor who won the match
     */
    public Competitor playMatch(Competitor c1, Competitor c2) {
        Competitor winner;
        winner = this.designatesTheWinner(c1, c2, rand.nextInt(2));
        notifyObservers(c1, c2, winner);
        return winner;
    }

    /**
     * generate the winner of the match
     *
     * @param c1  the first Competitor
     * @param c2  the second Competitor
     * @param num the counter number to tell whether the player is winning or not
     * @return the winner of the match
     */
    public Competitor designatesTheWinner(Competitor c1, Competitor c2, int num) {
        return num == 0 ? c1 : c2;
    }

    /** add an observer to this match
     * @param observer the observer
     */
    public void addObserver(Observer observer){
        observers.add(observer);
    }


    /** remove an observer to this match
     * @param observer the observer
     */
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    /** notify observers of the result of a match
     * @param c1 the first competitor
     * @param c2 the second competitor
     * @param winner the winning competitor
     */
    public void notifyObservers(Competitor c1, Competitor c2, Competitor winner){
        for(Observer obs : this.observers){
            obs.update(c1, c2, winner);
        }
    }
}