package game.games;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import game.*;

public class GameTest {

	private Game game1;
	private Game game2;

	private Board board1;
	private Board board2;

	private Player p1;
	private Player p2;
	private Player p3;
	private Player p4;

	@Before
	public void before(){
		Player p1 = new WarPlayer("Kent");
		Player p2 = new FarmPlayer("Kent");
		Player p3 = new FarmPlayer("Aziz");
		Player p4 = new WarPlayer("Aziz");

        this.board1 = new FarmBoard(10, 10);
		this.board2 = new WarBoard(10, 10);
		this.game1 = new FarmGame(6,this.board1);
		this.game2 = new WarGame(8,this.board2);
	}

	@Test
	public void getNumberOfTurnTest() {
		assertEquals(6,this.game1.getNumberOfRounds());
	}

    
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(GameTest.class);
    }    
}
