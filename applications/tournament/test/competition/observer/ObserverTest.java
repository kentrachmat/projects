package competition.observer;

import competition.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObserverTest {
    protected Competition competition1;
    protected Competition competition2;
    protected Match match;
    protected Competitor c1 = new Competitor("Leo");
    protected Competitor c2 = new Competitor("Samir");
    protected Competitor c3 = new Competitor("Khalil");
    protected Competitor c4 = new Competitor("Malek");


    @BeforeEach
    void setUp() {
        List<Competitor> competitors = new ArrayList<>();
        MockObserver observer = new MockObserver();
        competitors.add(c1);
        competitors.add(c2);
        competitors.add(c3);
        competitors.add(c4);
        match = new MockMatch();
        competition1 = new League(competitors, match);
        competition2 = new Tournament(competitors, match);
        match.addObserver(observer);
        competition1.play();
    }

    @Test
    void testBookMaker() {
        assertEquals(c1,match.notifyObservers(c1,c2,c1));
        //assertEquals(match.getObservers().getOdds().keySet().iterator().next().getName(),"Leo");
        // pas sûr
    }
}