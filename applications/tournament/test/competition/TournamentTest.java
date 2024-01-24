package competition;

import competition.exception.InvalidNumberOfCompetitorsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TournamentTest extends CompetitionTest {

    protected Tournament myTournament;

    @BeforeEach
    void setUpTournament() {
        myTournament = createCompetition();
    }

    protected Tournament createCompetition() {
        Competitor c1 = new Competitor("Arthur");
        Competitor c2 = new Competitor("Julien");
        Competitor c3 = new Competitor("Louise");
        Competitor c4 = new Competitor("Marine");
        ArrayList<Competitor> competitors = new ArrayList<>();
        competitors.add(c1);
        competitors.add(c2);
        competitors.add(c3);
        competitors.add(c4);
        return new Tournament(competitors, new MockMatch());
    }


    @Test
    public void testRemoveEliminatedCompetitors() {
        Competitor c1 = myTournament.getCompetitors().get(0);
        Competitor c3 = myTournament.getCompetitors().get(2);
        c1.addPoints(Competition.NB_POINTS_WIN_PER);
        c3.addPoints(Competition.NB_POINTS_WIN_PER);
        ArrayList<Competitor> expectedResult = new ArrayList<>();
        expectedResult.add(c1);
        expectedResult.add(c3);
        assertEquals(expectedResult, myTournament.removeEliminatedCompetitors(myTournament.getCompetitors(), Competition.NB_POINTS_WIN_PER));
    }

    @Test
    public void testPlayTournamentGiveOnlyOneWinner() {
        myTournament.play();
        Map<Competitor, Integer> results;
        results = myTournament.ranking();
        Iterator<Competitor> iterator = results.keySet().iterator();
        if (iterator.next().getPoints() <= iterator.next().getPoints()) {
            fail();
        }
    }

    @Test
    public void testMatchWinnerGetPoints() {
        myTournament.play();
        Map<Competitor, Integer> results;
        results = myTournament.ranking();
        Iterator<Competitor> iterator = results.keySet().iterator();
        if (iterator.next().getPoints() != iterator.next().getPoints() + Competition.NB_POINTS_WIN_PER) {
            fail();
        }
    }


    @Test
    public void testCheckConditionsWhenNotPowerOfTwoNumberOfCompetitors() {
        ArrayList<Competitor> competitors = new ArrayList<>();
        Competitor c1 = new Competitor("Arthur");
        Competitor c2 = new Competitor("Julien");
        Competitor c3 = new Competitor("Maxime");
        competitors.add(c1);
        competitors.add(c2);
        competitors.add(c3);
        Tournament tournament = new Tournament(competitors, new MockMatch());
        assertThrows(InvalidNumberOfCompetitorsException.class, () -> tournament.checkConditions(tournament.getCompetitors()));
    }
}