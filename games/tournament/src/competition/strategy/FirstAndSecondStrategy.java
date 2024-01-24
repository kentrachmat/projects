package competition.strategy;

import competition.Competitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A class to design a FirstAndSecondStrategy from the interface Strategy.
 * A FirstAndSecondStrategy is a Strategy where the first two of each group are selected.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class FirstAndSecondStrategy implements SelectionStrategy {


    /**
     * select the first two of each group
     * if the group contains only one competitor, the method will select this competitor
     *
     * @param ranks an ArrayList of Maps (results of each group)
     * @return an ArrayList of selected Competitors
     */
    public List<Competitor> chooseCompetitors(List<Map<Competitor, Integer>> ranks) {
        ArrayList<Competitor> competitors = new ArrayList<>();
        Iterator<Map.Entry<Competitor, Integer>> it;
        Map.Entry<Competitor, Integer> entry;
        for (Map<Competitor, Integer> group : ranks) {
            it = group.entrySet().iterator();
            entry = it.next();
            competitors.add(entry.getKey());
            if (group.size() >= 2) {
                entry = it.next();
                competitors.add(entry.getKey());
            }
        }
        return competitors;
    }

}