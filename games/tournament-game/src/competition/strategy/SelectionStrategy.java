package competition.strategy;

import competition.Competitor;

import java.util.List;
import java.util.Map;

/**
 * An interface to design all types of strategies.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public interface SelectionStrategy {

    /**
     * Selects some competitor
     *
     * @param ranks an ArrayList of Maps (results of each group)
     * @return an ArrayList of selected Competitors
     */
    List<Competitor> chooseCompetitors(List<Map<Competitor, Integer>> ranks);
}