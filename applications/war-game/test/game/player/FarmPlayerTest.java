package game.player;

import static org.junit.Assert.*;


import org.junit.*;

import game.strategy.*;
import game.tile.*;
import game.tile.resources.*;
import game.board.*;
import game.personnage.Personnage;
import game.personnage.Worker;

public class FarmPlayerTest {

	private FarmPlayer p1;
	private FarmPlayer p2;
	private FarmPlayer p3;

	private Board board1;
    private FarmRandomStrategy strategy1; 
    private FarmRandomStrategy strategy2; 
	private FarmStrategy strategy3; 

	@Before 
	public void before(){
        // initialization of the board.
        this.board1 = new WarBoard(10,10);

        // initialization of the strategy.
        this.strategy1 = new FarmRandomStrategy("strategy1",this.board1);
        this.strategy2 = new FarmRandomStrategy("strategy2",this.board1);
		this.strategy3 = new FarmHumanStrategy("strategy3",this.board1);
        
		// initialization of the players.
        this.p1 = new FarmPlayer("Kent",strategy1);
        this.p2 = new FarmPlayer("Dorian",strategy2);
		this.p3 = new FarmPlayer("Aziz",strategy3);
	}


	@Test
	public void getaddGoldTest() {
		assertEquals(15, this.p1.getGold());
		assertEquals(15, this.p2.getGold());
		assertEquals(15, this.p3.getGold());

		this.p1.addGold(1);
		this.p2.addGold(2);
		this.p3.addGold(3);

		assertEquals(16, this.p1.getGold());
		assertEquals(17, this.p2.getGold());
		assertEquals(18, this.p3.getGold());
	}

	@Test
	public void getNameTest() {
		assertEquals("Kent", this.p1.getName());
	}
	
	@Test
	public void getGoldTest() {
		assertEquals(15, this.p1.getGold());
	}
	
	@Test
	public void addGoldTest() {
		assertEquals(15, this.p1.getGold());
		this.p1.addGold(2);
		assertEquals(17, this.p1.getGold());
	}
	
	@Test
	public void decreaseGoldTestIsOK() {
		assertEquals(15, this.p1.getGold());
		this.p1.decreaseGold(12);
		assertEquals(3, this.p1.getGold());
	}
	
	@Test
	public void getStrategyTestIsOk() {
		assertTrue(this.strategy1.equals(this.p1.getStrategy()));
		assertFalse(this.strategy1.equals(this.p2.getStrategy()));
	}
	
	@Test
	public void addTerritoryTest() {
		assertEquals(this.p1.getTerritories().size(),0);
		DesertTile desert = new DesertTile(1,2,3,4);
		this.p1.addTerritory(desert);
		assertEquals(this.p1.getTerritories().size(),1);
	}

	@Test
	public void loseTerritoryTest() {
		assertEquals(this.p1.getTerritories().size(),0);
		DesertTile desert = new DesertTile(1,2,3,4);
		this.p1.addTerritory(desert);
		assertEquals(this.p1.getTerritories().size(),1);
		this.p1.loseTerritory(desert);
		assertEquals(this.p1.getTerritories().size(),0);
	}

	@Test
	public void addResourceTest(){
		assertEquals(this.p1.getResources().size(),0);
		RockResource rock = new RockResource(2);
		this.p1.addResource(rock);
		assertEquals(this.p1.getResources().size(),1);
	}

	@Test
	public void pointsTest(){
		assertEquals(15,this.p1.getPlayerPoints());
		this.p1.addGold(5);
		assertEquals(20,this.p1.getPlayerPoints());
	}
	
	@Test
	public void getTerritoriesTestIsOk () {
		DesertTile tile = new DesertTile(4, 6, 3, 5);
		MountainTile tile1 = new MountainTile(1, 8, 5, 8);
		MountainTile tile3 = new MountainTile(1, 8, 5, 8);
		this.p1.addTerritory(tile3);
		this.p1.addTerritory(tile);
		assertTrue(this.p1.getTerritories().get(0).equals(tile3));
		assertTrue(this.p1.getTerritories().get(1).equals(tile));
		assertFalse(this.p1.getTerritories().get(1).equals(tile1));
	}
	
	@Test
	public void getResourcesTestIsOk () {
		Resource r = new CornResource(2);
		Resource r1 = new CornResource(2);
		Resource r2 = new SandResource(5);
		this.p1.addResource(r);
		this.p1.addResource(r1);
		assertTrue(this.p1.getResources().get(0).equals(r));
		assertTrue(this.p1.getResources().get(1).equals(r1));
		assertFalse(this.p1.getResources().get(1).equals(r2));
	}
	
	@Test
	public void getResourcesForDisplayTestIsOk () {
		Resource r = new CornResource(2);
		Resource r1 = new SandResource(5);
		Resource r2 = new SandResource(5);
		Resource r3 = new SandResource(5);
		
		this.p1.addResource(r);
		this.p1.addResource(r1);
		this.p1.addResource(r2);
		this.p1.addResource(r3);
		
		assertTrue(this.p1.getResourcesForDisplay().get("Corn") == 1);
		assertTrue(this.p1.getResourcesForDisplay().get("Sand") == 3);
	}
	
	@Test
	public void transformAllresourcesTestIsOk () {
		this.p1.addResource(new CornResource(2));
		this.p1.addResource(new RockResource(8));
		this.p1.transformAllResources();
		assertEquals(25, this.p1.getGold());
	}
	
	@Test
	public void transformChosenResourceTestIsOk () {
		this.p1.addResource(new CornResource(2));
		this.p1.addResource(new RockResource(8));
		this.p1.addResource(new RockResource(8));
		this.p1. transformChosenResources("Rock", 2);
		assertEquals(31, this.p1.getGold());
	}
	
	@Test
	public void transformNeededResourceTestIsOk () {
		this.p1.addResource(new RockResource(8));
		this.p1. transformNeededResources(this.p1.getGold());
		assertEquals(15, this.p1.getGold());
	}
	
	@Test
	public void transformOneResourceTestIsOk () {
		this.p1.addResource(new RockResource(8));
		this.p1.addResource(new CornResource(2));
		this.p1.transformOneResource(new RockResource(8));
		assertEquals(23, this.p1.getGold());
		this.p1.transformOneResource(new CornResource(2));
		assertEquals(25, this.p1.getGold());
	}
	
	@Test
	public void getPersonnagesNeedsTestIsOk () {
		DesertTile tile = new DesertTile(4, 6, 3, 5);
		MountainTile tile3 = new MountainTile(1, 8, 5, 8);
		
		this.p1.addTerritory(tile);
		this.p1.addTerritory(tile3);
		
		tile.setPlayer(this.p1);
		tile3.setPlayer(this.p1);
		
		Personnage w = new Worker("aziz");
		w.setPlayer(this.p1);
		Personnage w1 = new Worker("aziz");
		w1.setPlayer(this.p1);
		
		tile.setPersonnage(w);
		tile3.setPersonnage(w1);
		
		assertEquals(this.p1.getPersonnagesNeeds(), 13);

	}

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(FarmPlayerTest.class);
    }    
}

