package competition.observer;

import competition.Competitor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A class to design a Bookmaker from the interface Observer.
 * A Bookmaker manages odds of all competitors.
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
*/

public class Bookmaker implements Observer {

    /** the Bookmaker's name */
    private String name;
    /** a map that contains the odds for each competitor */
    private Map<Competitor, Integer> odds = new LinkedHashMap<>();

    /** create a Bookmaker with given name
     * @param pName the Bookmaker's name
     */
    public Bookmaker(String pName){
        name = pName;
    }

    /** updates the odds and display them
     * @param c1 the first competitor
     * @param c2 the second competitor
     * @param winner the winning competitor
     */
    public void update(Competitor c1, Competitor c2, Competitor winner){
        Competitor loser = c1 == winner ? c2 : c1;
        int previousOddWinner;
        int previousOddLoser;
        
        if(this.odds.containsKey(winner)){
            previousOddWinner = this.odds.get(winner);
            if(previousOddWinner > 1){
                this.odds.put(winner, previousOddWinner - 1);
            }
        }
        else{
            previousOddWinner = 3;
            this.odds.put(winner, 2);
        }
        if(this.odds.containsKey(loser)){
            previousOddLoser = this.odds.get(loser);
            this.odds.put(loser, previousOddLoser + 1);
        }
        else{
            previousOddLoser = 3;
            this.odds.put(loser, 4);
        }
        System.out.println("The Bookmaker " + this.name + " announces the evolution of the odds :");
        System.out.println("winner : "+ winner.getName() +" (odds "+ previousOddWinner +"), loser : "+ loser.getName() +" (odds "+ previousOddLoser +"). "+ winner.getName() +"\'s odds go to " + this.odds.get(winner) + ", "+ loser.getName() +"\'s to " + this.odds.get(loser) + "\n");
    }

    /** returns the bookmaker name
     * @return the bookmaker name
     */
    public String getName(){
        return this.name;
    }

    /** returns the map of odds 
     * @return the map of odds 
     */
    public Map<Competitor, Integer> getOdds(){
        return this.odds;
    }
}