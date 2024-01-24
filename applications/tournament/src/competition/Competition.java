package competition;

import competition.exception.InvalidNumberOfCompetitorsException;
import competition.exception.InvalidNumberOfGroupsException;
import competition.util.MapUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class to design a Competition abstract class
 * A Competition is an abstract class and control the playing system in the competition
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public abstract class Competition {

    /**
     * the number of points win per match
     */
    protected static final int NB_POINTS_WIN_PER = 1;

    /**
     * the list of the competitors
     */
    protected final List<Competitor> competitors;

    /**
     * the match's type
     */
    protected Match match;

    /**
     * Create a Competition with a given list of competitors
     *
     * @param pCompetitors the competitors list
     * @param pMatch       the match
     */
    protected Competition(List<Competitor> pCompetitors, Match pMatch) {
        this.competitors = pCompetitors;
        this.match = pMatch;
    }


    /**
     * starts the competition game
     */
    public void play() {
        try {
            this.checkConditions(this.competitors);
            this.resetPoints();
            this.play(this.competitors);
            this.displayPoints(this.competitors);
            this.displayRank(ranking());
        } catch (Exception e) {
            System.out.println("\n" + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * checks if the competition conditions are satisfied
     *
     * @param competitors list of the competitors
     * @throws InvalidNumberOfCompetitorsException if the number of Competitors is incorrect
     * @throws InvalidNumberOfGroupsException      if the number of groups is incorrect
     */
    public void checkConditions(List<Competitor> competitors) throws InvalidNumberOfCompetitorsException, InvalidNumberOfGroupsException {
        if (competitors.isEmpty()) {
            throw new InvalidNumberOfCompetitorsException("There's not enough competitors, please input more than one players. Please try again! \n");
        }
    }

    /**
     * Reset the players point
     */
    public void resetPoints() {
        for (Competitor competitor : this.competitors) {
            competitor.resetPoints();
        }
    }

    /**
     * play a Match with two competitors
     *
     * @param c1 the first competitor
     * @param c2 the second competitor
     * @return the winner of the match
     */
    protected Competitor playMatch(Competitor c1, Competitor c2) {
        Competitor winner = this.match.playMatch(c1, c2);
        winner.addPoints(NB_POINTS_WIN_PER);
        return winner;
    }


    /**
     * Creates a map containing the competitors and their points ranked in descending order
     *
     * @return a map containing the competitors and their points ranked in descending order
     */
    public Map<Competitor, Integer> ranking() {
        Map<Competitor, Integer> rank = new LinkedHashMap<>();
        for (Competitor competitor : this.competitors) {
            rank.put(competitor, competitor.getPoints());
        }
        return MapUtil.sortByDescendingValue(rank);
    }


    /**
     * Display the player's point
     *
     * @param results list of players that played
     */
    public void displayPoints(List<Competitor> results) {
        System.out.println("\n*** POINTS ***");
        results.forEach(x -> System.out.println(x.getName() + " : " + x.getPoints()));
    }

    /**
     * display the player rank
     *
     * @param results map of the players that play this game and their points
     */
    public void displayRank(Map<Competitor, Integer> results) {
        AtomicInteger val = new AtomicInteger(1);
        System.out.println("\n*** RANK ***");
        results.forEach((id, name) -> System.out.println("Name : " + id.getName() + ", Rank : " + val.getAndIncrement()));
    }

    /**
     * display the information about the current match, the name of the players and the winner of the match
     *
     * @param competitor1 the first competitor
     * @param competitor2 the second competitor
     * @param winner      the winner of the round
     * @param number      the match number
     * @param syn         the connector symbol to display
     */
    public void displayProcess(Competitor competitor1, Competitor competitor2, Competitor winner, int number, String syn) {
        System.out.println(number + ". " + competitor1.getName() + " " + syn + " " + competitor2.getName() + " : " + winner.getName() + " winner\n");
        System.out.println("----------------------------------------------------\n");
    }

    /**
     * get the match
     *
     * @return the match
     */
    public Match getMatch() {
        return this.match;
    }

    /**
     * an abstract class that launch many matches given a list of competitors
     *
     * @param competitors the list of competitors
     */
    protected abstract void play(List<Competitor> competitors);

    /**
     * return the list of competitors of this Competition
     *
     * @return the list of competitors of this Competition
     */
    public List<Competitor> getCompetitors() {
        return this.competitors;
    }


    /**
     * returns the total number of points won by the competitors for this competition
     *
     * @return the total number of points won by the competitors for this competition
     */
    public int getTotalPoint() {
        int totalPoint = 0;
        for (Competitor competitor : this.competitors) {
            totalPoint += competitor.getPoints();
        }
        return totalPoint;
    }
}