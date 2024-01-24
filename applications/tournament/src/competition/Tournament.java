package competition;

import competition.exception.InvalidNumberOfCompetitorsException;
import competition.exception.InvalidNumberOfGroupsException;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to design a Tournament from the abstract class Competition.
 * A TournamentCompetition is a Competition where Competitors compete against each other round per round until there is a winner.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class Tournament extends Competition {

    /**
     * create a TournamentCompetition with given list of competitors
     *
     * @param pCompetitors the list of competitors
     * @param pMatch       the Match's type
     */
    public Tournament(List<Competitor> pCompetitors, Match pMatch) {
        super(pCompetitors, pMatch);
    }

    /**
     * checks if the TournamentCompetition conditions are satisfied
     *
     * @param competitors the list of competitors
     * @throws InvalidNumberOfCompetitorsException if TournamentCompetition conditions aren't satisfied
     */
    @Override
    public void checkConditions(List<Competitor> competitors) throws InvalidNumberOfCompetitorsException, InvalidNumberOfGroupsException {
        super.checkConditions(competitors);
        if ((!competitors.isEmpty()) && ((competitors.size() & (competitors.size() - 1)) != 0)) {
            throw new InvalidNumberOfCompetitorsException("The number of the participants must be a power of 2 in a tournament");
        }
    }

    /**
     * Launch many matches with a given list of competitors
     *
     * @param competitors the list of competitors
     */
    protected void play(List<Competitor> competitors) {
        System.out.println("\n*** THE TOURNAMENT GAME STARTS ! ***");
        int round = 0;
        int counter = 1;
        List<Competitor> winners = competitors;

        while (winners.size() != 1) {
            System.out.println("\n* Round " + ++round + " *");
            for (int i = 0; i < winners.size(); i = i + 2) {
                Competitor win = playMatch(winners.get(i), winners.get(i + 1));
                displayProcess(winners.get(i), winners.get(i + 1), win, counter++, "vs");
            }
            winners = removeEliminatedCompetitors(winners, round * Competition.NB_POINTS_WIN_PER);
        }
    }

    /**
     * remove the eliminated competitors
     *
     * @param competitors a list of winners and losers competitors
     * @param roundNumber the round number
     * @return a list with only winners competitors
     */
    protected List<Competitor> removeEliminatedCompetitors(List<Competitor> competitors, int roundNumber) {
        ArrayList<Competitor> winners = new ArrayList<>();
        for (Competitor competitor : competitors) {
            if (competitor.getPoints() == roundNumber) {
                winners.add(competitor);
            }
        }
        return winners;
    }
}