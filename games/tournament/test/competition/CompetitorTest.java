package competition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompetitorTest {
    private Competitor competitor1;
    private String name;
    private int pointAtTheBeginning, pointsToAdd1, pointsToAdd2;

    @BeforeEach
    public void setUp() {
        name = "kent";
        pointAtTheBeginning = 0;
        pointsToAdd1 = 8;
        pointsToAdd2 = 10;
        this.competitor1 = new Competitor(name);
    }

    @Test
    public void setNameTest() {
        String nameChange = "David";
        assertEquals(name, this.competitor1.getName());
        this.competitor1.setName(nameChange);
        assertEquals(nameChange, this.competitor1.getName());
    }

    @Test
    public void addPointTest() {
        assertEquals(pointAtTheBeginning, this.competitor1.getPoints());
        this.competitor1.addPoints(pointsToAdd1);
        assertEquals(pointAtTheBeginning + pointsToAdd1, this.competitor1.getPoints());
        int totalPoints = pointAtTheBeginning + pointsToAdd1 + pointsToAdd2;
        this.competitor1.addPoints(pointsToAdd2);
        assertEquals(totalPoints, this.competitor1.getPoints());
    }

    @Test
    public void setPointTest() {
        assertEquals(pointAtTheBeginning, this.competitor1.getPoints());
        this.competitor1.setPoints(pointsToAdd1);
        assertEquals(pointsToAdd1, this.competitor1.getPoints());
    }

    @Test
    public void resetPointTest() {
        this.competitor1.addPoints(pointsToAdd1);
        assertEquals(pointsToAdd1, this.competitor1.getPoints());
        this.competitor1.resetPoints();
        assertEquals(pointAtTheBeginning, this.competitor1.getPoints());
    }
}