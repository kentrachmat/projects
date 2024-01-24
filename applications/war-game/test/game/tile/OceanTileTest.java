package game.tile;

import static org.junit.Assert.*;
import org.junit.*;

import game.player.*;
import game.board.*;
import game.personnage.*;
import game.strategy.*;

public class OceanTileTest {

	// Ocean tiles for these tests.
	private Tile ocean1;
	private Tile ocean2;

	// Players for these tests.
	private Player player1;
	private Player player2;

	// Personnages for these tests.
	private Personnage p1;
	private Board board1;
	private FarmStrategy strategy1; 
	private WarStrategy strategy2;

	@Before
    public void before(){
		// initialization of the tiles.
		this.ocean1 = new OceanTile(1,1);
		this.ocean2 = new OceanTile(2,2);

		// initialization of the personnages.
		this.p1 = new Army("Felix",6);
		new Worker("Tom");
		new Army("Alexis",2);

		// initialization of the strategy.
		this.strategy1 = new FarmHumanStrategy("strategy1",this.board1);
		this.strategy2 = new WarHumanStrategy("strategy2",this.board1);

		// initialization of the board.
		this.board1 = new FarmBoard(10,10);

	    // initialization of the players.
		this.player1 = new FarmPlayer("Aziz",strategy1);
		this.player2 = new WarPlayer("Kent",strategy2);
    }

	@Test
	public void getPosXTestIsOk() {
		assertEquals(1, this.ocean1.getPosX());
    	assertFalse(2 == this.ocean1.getPosX());
	}

	@Test
	public void getPosYTestIsOk() {
		assertEquals(2, this.ocean2.getPosY());
		assertFalse(1 == this.ocean2.getPosY());
	}

	@Test
	public void getResourceTest() {
		assertNull(null, this.ocean1.getResource());
	}

	@Test
	public void equalsTest() {
		assertEquals(this.ocean1, new OceanTile(1,1));
    }

	@Test
	public void getCharacterTest() {
		assertEquals("~", this.ocean1.getCharacter());
	}

	@Test
	public void setgetPersonnageTest() {
		this.ocean1.setPersonnage(this.p1);
		assertEquals(this.p1, this.ocean1.getPersonnage());
	}

	@Test
	public void isEmptyTest() {
		assertTrue(this.ocean1.isEmpty());
	}
    
	@Test
	public void getTileCostTest() {
	  assertEquals(0, this.ocean1.getTileCost());
	  assertFalse(1 == this.ocean1.getTileCost());
	}

	@Test
	public void getValueResourceTest(){
		assertEquals(0, this.ocean1.getValueResource());
		assertFalse(1 == this.ocean1.getValueResource());
	}

	@Test
	public void emptyTileTest() {
	  assertTrue(this.ocean1.isEmpty());
	  this.ocean1.setPersonnage(this.p1);
	  assertFalse(this.ocean1.isEmpty());
	  this.ocean1.emptyTile();
	  assertTrue(this.ocean1.isEmpty());
	}

	@Test
	public void getPlayerTestIsOk() {
	  this.ocean1.setPlayer(this.player1);
	  assertEquals(this.player1, this.ocean1.getPlayer());
	  this.ocean1.setPlayer(this.player2);
	  assertEquals(this.player2, this.ocean1.getPlayer());
	  assertFalse(this.player1.equals(this.ocean1.getPlayer()));
	}
  
	@Test
	public void setPlayerTestIsOk() {
	  this.ocean2.setPlayer(this.player2);
	  assertEquals(this.player2, this.ocean2.getPlayer());
	  this.ocean2.setPlayer(this.player2);
	  assertEquals(this.player2, this.ocean2.getPlayer());
	}
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(OceanTileTest.class);
    }    
}

