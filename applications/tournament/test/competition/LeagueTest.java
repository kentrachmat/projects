package competition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeagueTest extends CompetitionTest {

    protected League myLeague;

    @BeforeEach
    void setUpLeague() {
        myLeague = createCompetition();
    }

    public League createCompetition() {
        List<Competitor> competitors = new ArrayList<>();
        competitors.add(new Competitor("Leo"));
        competitors.add(new Competitor("Samir"));
        competitors.add(new Competitor("Khalil"));
        competitors.add(new Competitor("Malek"));
        return new League(competitors, new MockMatch());
    }

    @Test
    public void testTotalPointsAtTheEnd() {
        assertEquals(0, myLeague.getTotalPoint());
        myLeague.play();
        int TotalPointExpected = (myLeague.getCompetitors().size() - 1) * (myLeague.getCompetitors().size());
        assertEquals(Competition.NB_POINTS_WIN_PER * TotalPointExpected, myLeague.getTotalPoint());
    }

    @Test
    public void testCompetitorHasTheExpectedPointsWhenHeWins() {
        assertEquals(0, myLeague.getTotalPoint());
        myLeague.play();
        assertEquals(Competition.NB_POINTS_WIN_PER * myLeague.getCompetitors().size() - 1, myLeague.getCompetitors().get(0).getPoints());
        assertEquals(Competition.NB_POINTS_WIN_PER * myLeague.getCompetitors().size() - 1, myLeague.getCompetitors().get(1).getPoints());
    }

}