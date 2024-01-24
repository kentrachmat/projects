package competition;

import competition.exception.InvalidNumberOfCompetitorsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public abstract class CompetitionTest {

    protected Competition myCompetition;

    protected abstract Competition createCompetition();

    @BeforeEach
    void setUp() {
        this.myCompetition = this.createCompetition();
    }

    @Test
    public void testRanking() {
        myCompetition.play();
        myCompetition.getCompetitors().get(0).setPoints(2);
        myCompetition.getCompetitors().get(1).setPoints(4);
        myCompetition.getCompetitors().get(2).setPoints(3);
        myCompetition.getCompetitors().get(3).setPoints(5);
        Map<Competitor, Integer> rank;
        rank = myCompetition.ranking();
        int points = rank.keySet().iterator().next().getPoints();
        for (Competitor c : rank.keySet()) {
            if (c.getPoints() > points) {
                fail();
            }
            points = c.getPoints();
        }
    }

    @Test
    public void testResetPoints() {
        myCompetition.getCompetitors().get(0).setPoints(2);
        myCompetition.getCompetitors().get(1).setPoints(4);
        myCompetition.getCompetitors().get(2).setPoints(3);
        myCompetition.getCompetitors().get(3).setPoints(5);
        myCompetition.resetPoints();
        for (Competitor c : myCompetition.getCompetitors()) {
            assertEquals(0, c.getPoints());
        }
    }

    @Test
    public void testGetTotalPoint() {
        myCompetition.resetPoints();
        assertEquals(0, myCompetition.getTotalPoint());
        myCompetition.getCompetitors().get(0).setPoints(1);
        myCompetition.getCompetitors().get(1).setPoints(2);
        myCompetition.getCompetitors().get(2).setPoints(3);
        myCompetition.getCompetitors().get(3).setPoints(4);
        assertEquals(10, myCompetition.getTotalPoint());
    }

    @Test
    public void testPlayMatchPlayerDoNtGetPointsWhenHeLose() {
        Competitor c1 = myCompetition.getCompetitors().get(0);
        Competitor c2 = myCompetition.getCompetitors().get(1);
        assertEquals(0, c1.getPoints());
        assertEquals(0, c2.getPoints());
        myCompetition.playMatch(c1, c2);
        assertEquals(0, c2.getPoints());
    }

    @Test
    public void testPlayMatchPlayerWinPointsWhenHeWin() {
        Competitor c1 = myCompetition.getCompetitors().get(0);
        Competitor c2 = myCompetition.getCompetitors().get(1);
        assertEquals(0, c1.getPoints());
        assertEquals(0, c2.getPoints());
        myCompetition.playMatch(c1, c2);
        assertEquals(Competition.NB_POINTS_WIN_PER, c1.getPoints());
    }

    @Test
    public void testCheckConditionsWhenNotEnoughCompetitors() {
        myCompetition.getCompetitors().clear();
        assertEquals(0, myCompetition.getCompetitors().size());
        assertThrows(InvalidNumberOfCompetitorsException.class, () -> myCompetition.checkConditions(myCompetition.getCompetitors()));
    }
}