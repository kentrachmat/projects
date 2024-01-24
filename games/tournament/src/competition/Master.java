package competition;

import java.util.Iterator;
import competition.exception.InvalidNumberOfCompetitorsException;
import competition.exception.InvalidNumberOfGroupsException;
import competition.strategy.SelectionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class to design a Master from the abstract class Competition.
 * A Master is a Competition where Competitors compete in a League followed by a Tournament.
 * For the first step the Players will have to play the League game and after that
 * all the player chosen by a method will pass to the second step which is the Tournament game
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class Master extends Competition {
    /**
     * number of tje groups 
     */
    private final int nbGroups;
    /**
     * strategy of the groups  
     */
    private final SelectionStrategy strategy;
    /**
     * ranking of all Competitors during the first stage 
     */
    private Map<Competitor, Integer> globalGroupStageRanking;

    /**
     * ranking of all Competitors during the second stage
     */
    private Map<Competitor, Integer> globalKnockoutStageRanking;

    /**
     * the list of Competitors selected for the second stage
     */
    private List<Competitor> knockoutStageParticipants;


    /**
     * Create a Master with a given list of Competitors, number of groups, and  selection strategy
     *
     * @param pCompetitors the list of competitors
     * @param pMatch       the Match's type
     * @param pNbGroups    the number of groups
     * @param pStrategy    the Strategy
     */
    public Master(List<Competitor> pCompetitors, Match pMatch, int pNbGroups, SelectionStrategy pStrategy) {
        super(pCompetitors, pMatch);
        nbGroups = pNbGroups;
        strategy = pStrategy;
    }


    /**
     * launch many Matches with given list of competitors
     *
     * @param competitors the list of competitors
     */
    protected void play(List<Competitor> competitors) {
        //Create groups
        List<ArrayList<Competitor>> groups = createGroups(competitors);
        System.out.println("\n*** THE MASTER GAME STARTS ! ***");

        //Play the league and stores the result
        List<Map<Competitor, Integer>> ranksByGroup = this.playLeagueFormatStage(groups);
        globalGroupStageRanking = this.ranking();

        //Selects competitors for the next stage
        this.resetPoints();
        knockoutStageParticipants = competitorsSelection(ranksByGroup);

        //Plays the tournament and stores the result
        this.playKnockoutStage(knockoutStageParticipants);
        globalKnockoutStageRanking = this.ranking();

        this.summingPoints();
    }


    /**
     * create the groups
     *
     * @param competitors the list of competitors
     * @return an ArrayList of each group of competitors
     */
    public List<ArrayList<Competitor>> createGroups(List<Competitor> competitors) {
        ArrayList<ArrayList<Competitor>> groups = new ArrayList<>();
        int i;
        for (i = 0; i < nbGroups; i++) {
            ArrayList<Competitor> group = new ArrayList<>();
            groups.add(group);
        }
        i = 0;
        for (Competitor c : competitors) {
            groups.get(i).add(c);
            i++;
            if (i > nbGroups - 1) {
                i = 0;
            }
        }
        return groups;
    }


    /**
     * plays the first stage
     *
     * @param groups an ArrayList of Competitors (the groups)
     * @return an ArrayList of Maps (results of each group)
     */
    public List<Map<Competitor, Integer>> playLeagueFormatStage(List<ArrayList<Competitor>> groups) {
        ArrayList<Map<Competitor, Integer>> ranks = new ArrayList<>();
        for (ArrayList<Competitor> group : groups) {
            Competition league = new League(group, match);
            league.play();
            Map<Competitor, Integer> rank = league.ranking();
            ranks.add(rank);
        }
        return ranks;
    }


    /**
     * checks if the MasterCompetition conditions are satisfied
     * (overriding the parents method)
     * 
     * @param competitors list of the competitors
     * @throws InvalidNumberOfCompetitorsException if there are less than one competitor
     * @throws InvalidNumberOfGroupsException      if there are less than one group
     */
    @Override
    public void checkConditions(List<Competitor> competitors) throws InvalidNumberOfCompetitorsException, InvalidNumberOfGroupsException {
        super.checkConditions(competitors);
        if (nbGroups < 1) {
            throw new InvalidNumberOfGroupsException("There is not enough groups. There must be at least one group in a Master.");
        }
    }


    /**
     * display the MasterCompetition ranking and the winner
     * (overriding the parents method)
     * 
     * @param results a Map containing the Competitors with their points ranked in descending order
     */
    @Override
    public void displayRank(Map<Competitor, Integer> results) {
        int pointsMax=0;
        Competitor c1;
        System.out.println("\nRank of Master competition (descending order):");
        int i = 0;
        for (Competitor competitor : globalKnockoutStageRanking.keySet()) {
            if (knockoutStageParticipants.contains(competitor)) {
                i++;
                System.out.println(i + " - " + competitor.getName());
            }
        }
        for (Competitor competitor : globalGroupStageRanking.keySet()) {
            if (!knockoutStageParticipants.contains(competitor)) {
                i++;
                System.out.println(i + " - " + competitor.getName());
            }
        }
        System.out.println("\n" + globalKnockoutStageRanking.keySet().iterator().next().getName() + " is the winner of the Master competition ! Special congratulation to ");
        Iterator iterator = results.keySet().iterator();
        c1 = (Competitor) iterator.next();
        pointsMax = c1.getPoints();
        do {
            System.out.println(c1.getName());
            c1 = (Competitor) iterator.next();
        }
        while(c1.getPoints() == pointsMax && iterator.hasNext());
        System.out.println("who has the most points in this competition");
        System.out.println("Thank you for playing our game");
    }

    /**
     * Display the player's point
     * (overriding the parents method)
     * 
     * @param results list of players that played
     */
    @Override
    public void displayPoints(List<Competitor> results) {
        System.out.println("\n-------------------");
        System.out.println("\n*** The players total points for Master competition ***");
        results.forEach(x -> System.out.println(x.getName() + " : " + x.getPoints()));
    }


    /**
     * counts the point total obtained for each player in this master competition
     */
    public void summingPoints() {
        int pointsToAdd;
        for (Competitor competitor : competitors) {
            pointsToAdd = globalGroupStageRanking.get(competitor);
            competitor.addPoints(pointsToAdd);
        }
    }


    /**
     * selects the qualified competitors
     *
     * @param ranks an ArrayList of Maps (results of each group in the group stage)
     * @return an ArrayList of selected Competitors
     */
    public List<Competitor> competitorsSelection(List<Map<Competitor, Integer>> ranks) {
        return strategy.chooseCompetitors(ranks);
    }


    /**
     * Play the second stage
     *
     * @param participants an ArrayList of Competitors
     */
    public void playKnockoutStage(List<Competitor> participants) {
        Competition tournament = new Tournament(participants, match);
        tournament.play();
    }

    /**
     * Returns the method
     *
     * @return the method
     */
    public SelectionStrategy getStrategy() {
        return this.strategy;
    }

    /**
     * Returns the amount of groups
     *
     * @return the amount of groups
     */
    public int getNbGroups() {
        return this.nbGroups;
    }

    /**
     * Returns the list of competitors
     *
     * @return the list of competitors
     */
    @Override
    public List<Competitor> getCompetitors() {
        return this.competitors;
    }
}
