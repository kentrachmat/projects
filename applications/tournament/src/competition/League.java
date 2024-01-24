package competition;

import java.util.List;

/**
 * A class to design a League extended with the Competition class.
 * A LeagueCompetition is a Competition where the participants compete 
 * until all of the competitors faced all of the their team mates.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class League extends Competition {

    /**
     * create a LeagueCompetition with given list of competitors
     *
     * @param pCompetitors the list of competitors
     * @param pMatch       the Match's type
     */
    public League(List<Competitor> pCompetitors, Match pMatch) {
        super(pCompetitors, pMatch);
    }
    
    /**
     * launch the match with a given list of competitors
     *
     * @param competitors the list of competitors
     */
    protected void play(List<Competitor> competitors) {
        System.out.println("\n*** THE LEAGUE GAME STARTS ! ***");
        int i = 1;
        for (Competitor competitor1 : competitors) {
            for (Competitor competitor2 : competitors) {
                if (competitor1 != competitor2) {
                    Competitor winner = playMatch(competitor1, competitor2);
                    displayProcess(competitor1, competitor2, winner, i++, "->");
                }
            }
        }
    }
}