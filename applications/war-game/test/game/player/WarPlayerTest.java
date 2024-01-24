package game.player;

import static org.junit.Assert.*;

import org.junit.*;

import game.strategy.*;
import game.tile.*;
import game.personnage.*;
import game.tile.resources.*;
import game.board.*;

public class WarPlayerTest {

	private WarPlayer p1;
	private WarPlayer p2;
	private WarPlayer p3;

	private Board board1;
    private WarStrategy strategy1; 
    private WarStrategy strategy2; 
	private FarmStrategy strategy3; 

	@Before 
	public void before(){
        // initialization of the board.
        this.board1 = new WarBoard(10,10);

        // initialization of the strategy.
        this.strategy1 = new WarHumanStrategy("strategy1",this.board1);
        this.strategy2 = new WarHumanStrategy("strategy2",this.board1);
		this.strategy3 = new FarmHumanStrategy("strategy3",this.board1);
        
		//Inialization of the players.
        this.p1 = new WarPlayer("Kent",strategy1);
        this.p2 = new WarPlayer("Dorian",strategy2);
		this.p3 = new WarPlayer("Aziz",strategy3);
		
		new Army("Corentin",10);
	}

	@Test
	public void getdecreaseFoodTest() {
		assertEquals(10, this.p1.getFood());
		assertEquals(10, this.p2.getFood());

		this.p1.decreaseFood(2);
		this.p2.decreaseFood(6);

		assertEquals(8, this.p1.getFood());
		assertEquals(4, this.p2.getFood());
	}

	@Test
	public void getaddGoldTest() {
		assertEquals(0, this.p1.getGold());
		assertEquals(0, this.p2.getGold());
		assertEquals(0, this.p3.getGold());

		this.p1.addGold(1);
		this.p2.addGold(2);
		this.p3.addGold(3);

		assertEquals(1, this.p1.getGold());
		assertEquals(2, this.p2.getGold());
		assertEquals(3, this.p3.getGold());
	}
	@Test
	public void getaddFoodTest() {
		assertEquals(10, this.p1.getFood());
		assertEquals(10, this.p2.getFood());
		assertEquals(10, this.p3.getFood());

		this.p1.addFood(1);
		this.p2.addFood(2);
		this.p3.addFood(3);

		assertEquals(11, this.p1.getFood());
		assertEquals(12, this.p2.getFood());
		assertEquals(13, this.p3.getFood());
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
		CornResource corn = new CornResource(2);
		assertEquals(this.p1.getResources().size(),1);
		this.p1.addResource(corn);
		assertEquals(this.p1.getResources().size(),2);
	}

	@Test
	public void pointsTest(){
		assertEquals(0,this.p1.getPlayerPoints());
		PlainTile plain = new PlainTile(0, 0, 1, 5);
		this.p1.addTerritory(plain);
		this.p1.addGold(5);
		assertEquals(6,this.p1.getPlayerPoints());
	}

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WarPlayerTest.class);
    }    
}

