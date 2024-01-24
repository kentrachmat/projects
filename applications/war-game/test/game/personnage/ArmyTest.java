package game.personnage;

import static org.junit.Assert.*;
import org.junit.*;

import game.tile.*;
import game.exception.*;
import game.player.*;
import game.board.*;
import game.strategy.*;

public class ArmyTest {
    
    // Personages for these tests.
    private Personnage personnage1;
    private Personnage personnage2;
    private Personnage personnage3;

	// Tiles with which these army tests will be done.
    private Tile desert;
    private Tile forest;
    private Tile mountain;
    private Tile plain;
    // Players with whom these army tests will be done.
    private Player player1;
    private Player player2;
    
    // Armies with which these army tests will be done.
	private Army army1;
	private Army army2;
	private Army army3;
	private Army army4;

    private Board board1;
    private WarStrategy strategy1; 
    private WarStrategy strategy2; 

    @Before
    public void before() {
    	//Inialization of the armies.
        this.army1 = new Army("Battaillon1", 5);
		this.army2 = new Army("Battaillon2", 3);
		this.army3 = new Army("Battaillon3", 4); 
		this.army4 = new Army("Battaillon4", 1);
        
        //Inialization of the tiles.
        this.desert = new DesertTile(2,3,2,0);
        this.forest = new ForestTile(1,1,1,1);
        new OceanTile(5,4);
        this.mountain = new MountainTile(5,4, 1, 0);
        this.plain = new MountainTile(5,4, 1, 5);

        // initialization of the board.
        this.board1 = new WarBoard(10,10);

        // initialization of the personnages.
        this.personnage1 = new Army("Felix",6);
        this.personnage2 = new Worker("Tom");
        this.personnage3 = new Army("Alexis",2);

        // initialization of the strategy.
        this.strategy1 = new WarHumanStrategy("strategy1",this.board1);
        this.strategy2 = new WarHumanStrategy("strategy2",this.board1);
		
        //Inialization of the players.
        this.player1 = new WarPlayer("Kent",strategy1);
        this.player2 = new WarPlayer("Dorian",strategy2);
    }

    @Test
    public void setandgetTileTest(){
        this.personnage1.setTile(this.desert);
        assertEquals(this.desert, this.personnage1.getTile());
        this.personnage2.setTile(this.forest);
        assertEquals(this.forest, this.personnage2.getTile());
    }

    @Test
    public void getNameTest(){
        assertEquals("Felix",this.personnage1.getName());
        assertEquals("Tom",this.personnage2.getName());
        assertEquals("Alexis",this.personnage3.getName());
    }

	public void takeAwayAWarriorIsUpperToZero() throws EmptyArmyException {
		this.army1.takeAwayAWarrior(1);
		assertEquals(4,this.army1.getArmySize());	
		this.army1.takeAwayAWarrior(2);
		assertEquals(2,this.army1.getArmySize());
		this.army1.takeAwayAWarrior(1);
        assertEquals(1,this.army1.getArmySize());
	}

    @Test(expected = EmptyArmyException.class)
	public void takeAwayAWarriorTestError() throws EmptyArmyException {
		this.army1.takeAwayAWarrior(1);
		assertEquals(4,this.army1.getArmySize());	
		this.army1.takeAwayAWarrior(2);
		assertEquals(2,this.army1.getArmySize());
		this.army1.takeAwayAWarrior(1);
        try{
        assertEquals(1,this.army1.getArmySize());
        this.army1.takeAwayAWarrior(2);
        } catch(EmptyArmyException e){
            fail();
        }
        assertEquals(1,this.army1.getArmySize());
	}

    @Test
	public void getArmySizeTestWhenOk() {
        assertEquals(5,this.army1.getArmySize());
        assertEquals(3,this.army2.getArmySize());
        assertEquals(4,this.army3.getArmySize());
	}
    
    @Test
	public void getArmySizeTestWhenNotOk() {
        assertFalse(3 == this.army1.getArmySize());
        assertFalse(6 == this.army2.getArmySize());
        assertFalse(8 == this.army3.getArmySize());
	}

    @Test
	public void feedPersonnageTest() throws NotEnoughFeedException {
    	this.army2.setPlayer(player1);
    	this.army2.setTile(desert);
    	
    	assertEquals(10, ((WarPlayer) player1).getFood());
		this.army2.feedPersonnage();
		assertEquals(4, ((WarPlayer) player1).getFood());
	}

    @Test(expected = NotEnoughFeedException.class)
	public void feedPersonnageTestError() throws NotEnoughFeedException {
    	this.army2.setPlayer(player1);
    	this.army2.setTile(desert);
    	
    	assertEquals(10, ((WarPlayer) player1).getFood());
		this.army2.feedPersonnage();
		assertEquals(4, ((WarPlayer) player1).getFood());
		
        try{
        this.army2.feedPersonnage();
		assertEquals(4, ((WarPlayer) player1).getFood());
        } catch(NotEnoughFeedException e){
            fail();
        }
	}
    
    @Test
	public void getArmyNeedsTestIsOkForAllTiles() {
    	this.army2.setTile(this.desert);
    	assertEquals(6,this.army2.getArmyNeeds());
    	this.army3.setTile(this.forest);
    	assertEquals(4,this.army3.getArmyNeeds());
    	this.army1.setTile(this.plain);
    	assertEquals(5,this.army1.getArmyNeeds());
    	this.army4.setTile(this.mountain);
    	assertEquals(1,this.army4.getArmyNeeds());
	}

    @Test
	public void getEnemyArmyStrengthWhenOk() {
    	this.army3.setTile(mountain);
    	this.army3.setPlayer(player1);
    	this.army2.setTile(desert);
    	this.army2.setPlayer(player2);
    	assertEquals(5,this.army2.getEnemyArmyStrength(this.army3));
	}
    
    @Test
	public void getEnemyArmyStrengthWhenOkAndSizeArmyDontChange() {
    	this.army3.setTile(mountain);
    	this.army3.setPlayer(player1);
    	this.army2.setTile(desert);
    	this.army2.setPlayer(player2);
    	assertEquals(5,this.army2.getEnemyArmyStrength(this.army3));
    	assertEquals(4,this.army3.getArmySize());
	}

    @Test
	public void joinArmyTestWhenTileIsMountainOrDesertTileWhenOk() {
		this.army2.setTile(desert);
		assertEquals(0, desert.getValueResource());
		assertEquals(0, mountain.getValueResource());
		assertEquals(3,this.army2.getArmySize());
		
        this.army4.setTile(mountain);
        assertEquals(1,this.army4.getArmySize());
        
        this.army2.joinArmy(4);
        assertEquals(3,this.army2.getArmySize());
        this.army2.joinArmy(4);
        assertEquals(3,this.army2.getArmySize());
        
        this.army4.joinArmy(1);
        assertEquals(2,this.army4.getArmySize());
        
        this.army4.joinArmy(1);
        assertEquals(3,this.army4.getArmySize());
     
	} 
    
    @Test
	public void joinArmyTestWhenTileIsPlainOrForestTileIsOk() {
		this.army1.setTile(forest);
		assertEquals(5,this.army1.getArmySize());        
        this.army1.joinArmy(1);
        assertEquals(5,this.army1.getArmySize());
        this.army1.joinArmy(4);
        assertEquals(5,this.army1.getArmySize());
        
        this.army4.setTile(plain);
        assertEquals(1,this.army4.getArmySize());
        this.army4.joinArmy(1);
        assertEquals(2,this.army4.getArmySize());
        this.army4.joinArmy(1);
        assertEquals(3,this.army4.getArmySize());
        this.army4.joinArmy(1);
        assertEquals(4,this.army4.getArmySize());
        this.army4.joinArmy(1);
        assertEquals(5,this.army4.getArmySize());
        this.army4.joinArmy(1);
        assertEquals(5,this.army4.getArmySize());
     
	} 
    
    @Test
	public void getaddGoldTestWhenOk() {
        this.army1.addGold(31);
        this.army2.addGold(43);
        this.army3.addGold(22);
        this.army3.addGold(1);
        
        assertEquals(31,this.army1.getGold());
        assertEquals(43,this.army2.getGold());
        assertEquals(23,this.army3.getGold());
    }   
    
    @Test
	public void getAndAddGoldTestWhenNotOk() {
        this.army1.addGold(31);
        assertFalse(5 == this.army1.getGold());
        
        this.army2.addGold(43);
        assertFalse(0 == this.army2.getGold());
        
        this.army3.addGold(22);
        this.army3.addGold(1);
        assertEquals(23,this.army3.getGold());
    }  
    
    @Test
	public void setAndGetPlayerTestIsOk() {
    	assertNull(this.army1.getPlayer());
        this.army1.setPlayer(player1);
        assertSame(this.player1,this.army1.getPlayer());
        
        this.army1.setPlayer(player2);
        assertEquals(this.player2,this.army1.getPlayer());

    }
    
    @Test
	public void isEnemyTestWhenTheArmyOpponentIsNotEnemy() {
        this.army1.setPlayer(player1);
        this.army2.setPlayer(player2);
        assertTrue(this.army1.isEnemy(this.army2));
    }
    
    @Test
	public void isEnemyTestWhenTheArmyOpponentIsEnemy() {
        this.army1.setPlayer(player1);
        this.army2.setPlayer(player1);
        assertFalse(this.army1.isEnemy(this.army2));
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(ArmyTest.class);
    }
}
