package competition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomMatchTest {

    private RandomMatch match;
    private Competitor c1;
    private Competitor c2;
    private Competitor winner;


    //RandomMatch specific test
    @BeforeEach
    void setUp() {
        this.c1 = new Competitor("Julien");
        this.c2 = new Competitor("Arthur");
        this.match = new RandomMatch();
        this.winner = null;
    }

    @Test
    public void testPlayMatchReturnACorrectCompetitor() {
        this.winner = this.match.playMatch(c1, c2);
        if (this.winner != c1 && this.winner != c2) {
            fail();
        }
    }

    @Test
    public void theWinnerIsC1IfTheGeneratedNumberIs0() {
        assertNull(this.winner);
        this.winner = this.match.designatesTheWinner(c1, c2, 0);
        assertSame(this.winner, c1);
    }

    @Test
    public void theWinnerIsC2IfTheGeneratedNumberIs1() {
        assertNull(this.winner);
        this.winner = this.match.designatesTheWinner(c1, c2, 1);
        assertSame(this.winner, c2);
    }

}