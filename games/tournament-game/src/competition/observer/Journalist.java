package competition.observer;

import competition.Competitor;

/**
 * A class to design a Journalist from the interface Observer.
 * A Journalist announces match results.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class Journalist implements Observer {

    /** the Journalist's name */
    private String name;

    /** create a Journalist with given name
     * @param pName the Journalist's name
     */
    public Journalist(String pName){
        name = pName;
    }

    /** display the winner of a match
     * @param c1 the first competitor
     * @param c2 the second competitor
     * @param winner the winning competitor
     */
    public void update(Competitor c1, Competitor c2, Competitor winner){
        System.out.println("The Journalist " + this.name + " announce the result :");
        System.out.println(c1.getName() + " vs " + c2.getName() + " --> " + winner.getName() + " wins !\n");
    }

    /** returns the Journalist name
     * @return the Journalist name
     */
    public String getName(){
        return this.name;
    }
}