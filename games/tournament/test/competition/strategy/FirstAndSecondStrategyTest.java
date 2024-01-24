package competition.strategy;

import competition.Competitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FirstAndSecondStrategyTest {
    protected SelectionStrategy strategy;
    protected ArrayList<Map<Competitor, Integer>> groups;
    protected Map<Competitor, Integer> group1;
    protected Map<Competitor, Integer> group2;
    protected Competitor c1;
    protected Competitor c2;
    protected Competitor c3;
    protected Competitor c4;
    protected Competitor c5;
    protected Competitor c6;

    @BeforeEach
    void setUp() {
        groups = new ArrayList<>();
        group1 = new LinkedHashMap<>();
        group2 = new LinkedHashMap<>();
        c1 = new Competitor("Julien");
        c2 = new Competitor("Arthur");
        c3 = new Competitor("Julie");
        c4 = new Competitor("Marine");
        c5 = new Competitor("Jean");
        c6 = new Competitor("Clara");
        strategy = new FirstAndSecondStrategy();
    }

    @Test
    public void testFirstAndSecondStrategySelectsOnlyTheFirstTwoCompetitorsOfEachGroup() {
        int groupsSize = 2;
        int sizeGroup1 = 3;
        int sizeGroup2 = 2;
        group1.put(c1, 5);
        group1.put(c2, 5);
        group1.put(c3, 3);
        group2.put(c4, 6);
        group2.put(c5, 6);
        assertEquals(sizeGroup1, group1.size());
        assertEquals(sizeGroup2, group2.size());
        groups.add(group1);
        groups.add(group2);
        assertEquals(groupsSize, groups.size());
        List<Competitor> expected = new ArrayList<>();
        expected.add(c1);
        expected.add(c2);
        expected.add(c4);
        expected.add(c5);
        List<Competitor> result = strategy.chooseCompetitors(groups);
        assertEquals(groupsSize * 2, result.size());
        assertEquals(expected, result);
    }

    @Test
    public void testFirstAndSecondSelectsTheFirstCompetitorsWhenGroupHaveOnlyOneCompetitor() {
        int groupsSize = 1;
        int sizeGroup1 = 1;
        group1.clear();
        group1.put(c1, 5);
        assertEquals(sizeGroup1, group1.size());
        groups.clear();
        groups.add(group1);
        assertEquals(groupsSize, groups.size());
        List<Competitor> result = strategy.chooseCompetitors(groups);
        List<Competitor> expected = new ArrayList<>();
        expected.add(c1);
        assertEquals(groupsSize, result.size());
        assertEquals(expected, result);
    }


}