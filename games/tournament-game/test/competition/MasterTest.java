package competition;


import competition.exception.InvalidNumberOfGroupsException;
import competition.strategy.FirstStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MasterTest extends CompetitionTest {

    protected Master myMaster;
    protected ArrayList<Competitor> competitors;
    protected int nbGroup;
    protected Competitor c1;
    protected Competitor c2;
    protected Competitor c3;
    protected Competitor c4;

    @BeforeEach
    void setUpMaster() {
        myMaster = createCompetition();
    }

    public Master createCompetition() {
        nbGroup = 2;
        c1 = new Competitor("Arthur");
        c2 = new Competitor("Julien");
        c3 = new Competitor("Louise");
        c4 = new Competitor("Marine");
        competitors = new ArrayList<>();
        competitors.add(c1);
        competitors.add(c2);
        competitors.add(c3);
        competitors.add(c4);
        return new Master(competitors, new MockMatch(), nbGroup, new FirstStrategy());
    }

    @Test
    public void testCheckConditionsWhenNotEnoughGroups() {
        int nbGroup = 0;
        ArrayList<Competitor> competitors = new ArrayList<>();
        Competitor c1 = new Competitor("Arthur");
        competitors.add(c1);
        Master master = new Master(competitors, new MockMatch(), nbGroup, new FirstStrategy());
        assertThrows(InvalidNumberOfGroupsException.class, () -> master.checkConditions(master.getCompetitors()));
    }

    @Test
    public void testPlayMasterGiveOnlyOneWinner() {
        myMaster.play();
        Map<Competitor, Integer> results;
        results = myMaster.ranking();
        Iterator<Competitor> iterator = results.keySet().iterator();
        if (iterator.next().getPoints() <= iterator.next().getPoints()) {
            fail();
        }
    }

    @Test
    public void testTotalPointsAtTheEndOfGroupStage() {
        assertEquals(0, myMaster.getTotalPoint());
        myMaster.playLeagueFormatStage(myMaster.createGroups(competitors));
        int TotalPointExpected = 4;
        assertEquals(Competition.NB_POINTS_WIN_PER * TotalPointExpected, myMaster.getTotalPoint());
    }

    @Test
    public void testStrategyReallyUsed() {
        List<ArrayList<Competitor>> groups = myMaster.createGroups(competitors);
        List<Map<Competitor, Integer>> ranksByGroup = myMaster.playLeagueFormatStage(groups);
        assertEquals(myMaster.getStrategy().chooseCompetitors(ranksByGroup), myMaster.competitorsSelection(ranksByGroup));
    }

    @Test
    public void testSequenceOfGroupStageAndStrategyAndKnockoutStage() {
        assertEquals(4, competitors.size());
        List<ArrayList<Competitor>> groups = myMaster.createGroups(competitors);
        assertEquals(2, groups.size());
        List<Map<Competitor, Integer>> ranksByGroup = myMaster.playLeagueFormatStage(groups);
        assertEquals(4, competitors.get(0).getPoints() + competitors.get(1).getPoints() + competitors.get(2).getPoints() + competitors.get(3).getPoints());
        List<Competitor> participants = myMaster.competitorsSelection(ranksByGroup);
        myMaster.resetPoints();
        assertEquals(2, participants.size());
        myMaster.playKnockoutStage(participants);
        assertEquals(1, participants.get(0).getPoints() + participants.get(1).getPoints());
    }

    @Test
    public void testCreateGroups() {
        int nbGroup = 3;
        int totalCompetitor = 7;
        ArrayList<Competitor> competitors = new ArrayList<>();
        Competitor c1 = new Competitor("Arthur");
        Competitor c2 = new Competitor("Julie");
        Competitor c3 = new Competitor("Maxime");
        Competitor c4 = new Competitor("Lucie");
        Competitor c5 = new Competitor("Lola");
        Competitor c6 = new Competitor("Fabien");
        Competitor c7 = new Competitor("Manon");
        competitors.add(c1);
        competitors.add(c2);
        competitors.add(c3);
        competitors.add(c4);
        competitors.add(c5);
        competitors.add(c6);
        competitors.add(c7);
        Master master = new Master(competitors, new MockMatch(), nbGroup, new FirstStrategy());
        List<ArrayList<Competitor>> groups = master.createGroups(competitors);
        assertEquals(nbGroup, groups.size());
        assertEquals(3, groups.get(0).size());
        assertEquals(2, groups.get(1).size());
        assertEquals(2, groups.get(2).size());
        assertEquals(totalCompetitor, groups.get(0).size() + groups.get(1).size() + groups.get(2).size());
    }
}