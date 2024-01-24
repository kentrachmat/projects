package competition.strategy;

import competition.Competitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class to design a FirstStrategy from the interface Strategy.
 * A FirstStrategy is a Strategy where the first of each group are selected.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 **/
public class FirstStrategy implements SelectionStrategy {

    /**
     * select the first of each group
     *
     * @param ranks an ArrayList of Maps (results of each group)
     * @return an ArrayList of selected Competitors
     */
    public List<Competitor> chooseCompetitors(List<Map<Competitor, Integer>> ranks) {
        ArrayList<Competitor> competitors = new ArrayList<>();
        for (Map<Competitor, Integer> group : ranks) {
            competitors.add(group.keySet().iterator().next());
        }
        return competitors;
    }
}