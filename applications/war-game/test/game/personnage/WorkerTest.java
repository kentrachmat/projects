package game.personnage;

import static org.junit.Assert.*;
import org.junit.*;

import game.tile.*;
import game.exception.*;
import game.player.*;
import game.strategy.*;
import game.board.*;

public class WorkerTest {
    
	// Tiles with which these army tests will be done.
    private Tile desert;
    private Tile forest;
    private Tile mountain;
    private Tile plain;
    
    // Players with whom these army tests will be done.
    private Player player1;
    
    // Personages for these tests.
    private Personnage personnage1;
    private Personnage personnage2;
    private Personnage personnage3;

    // Armies with which these army tests will be done.
	private Worker worker1;
	private Worker worker2;
	private Worker worker3;
	private Worker worker4;

    private Board board1;
    private FarmStrategy strategy1; 
    private FarmStrategy strategy2; 

    @Before
    public void before() {
        this.worker1 = new Worker("ouvrier1");
		this.worker2 = new Worker("ouvrier2");
		this.worker3 = new Worker("ouvrier3");
		this.worker4 = new Worker("ouvrier4");
        
        // initialization of the board.
        this.board1 = new FarmBoard(10,10);

        // initialization of the strategy.
        this.strategy1 = new FarmHumanStrategy("strategy1",this.board1);
        this.strategy2 = new FarmHumanStrategy("strategy2",this.board1);

        this.player1 = new FarmPlayer("Turin",strategy1);
        new FarmPlayer("Alan",strategy2);

        // initialization of the personages.
        this.personnage1 = new Army("Felix",6);
        this.personnage2 = new Worker("Tom");
		this.personnage3 = new Army("Alexis",2);

        this.desert = new DesertTile(1,2,3,5);
        this.forest = new ForestTile(3,2,1,2);
        this.plain = new DesertTile(1,2,1,2);
        this.mountain = new ForestTile(3,2,5,8);
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

	@Test
	public void getWorkerNeedsTestIsOkForAllTiles() {
		this.worker1.setTile(this.desert);
		assertEquals(3,this.worker1.getWorkerNeeds());
        this.worker2.setTile(this.forest);
        assertEquals(1,this.worker2.getWorkerNeeds());
        this.worker3.setTile(this.plain);
        assertEquals(1,this.worker3.getWorkerNeeds());
        this.worker4.setTile(this.mountain);
        assertEquals(5,this.worker4.getWorkerNeeds());
	}	
	
	public void feedPersonnageTestIsOK() throws NotEnoughFeedException{
		this.worker1.setTile(this.desert);
		this.worker1.setPlayer(this.player1);
        this.worker1.addGold(12);
		assertEquals(12,this.player1.getGold());
		this.worker1.feedPersonnage();
		assertEquals(7,this.player1.getGold());
	}  
    
    @Test(expected = NotEnoughFeedException.class)
	public void feedPersonnageTestIsNotOK() throws Exception {
		this.worker1.setTile(this.desert);
        this.worker1.setPlayer(this.player1);
        this.worker1.addGold(10);
        
        try {
            this.worker1.feedPersonnage();
            this.worker1.feedPersonnage();
            this.worker1.feedPersonnage();
		} catch(Exception e) {
            fail();
        }
        assertFalse(1 == this.player1.getGold());
        
	}  
    
    @Test
	public void getAndaddGoldTestIsOk() {
        this.worker1.addGold(12);
        this.worker2.addGold(0);
        this.worker3.addGold(2);
        this.worker3.addGold(1);
        
        assertEquals(12,this.worker1.getGold());
        assertEquals(0,this.worker2.getGold());
        assertEquals(3,this.worker3.getGold());
    }   
    
    @Test
	public void getAndsetPlayerTestIsOK() {  
    	assertNull(this.worker1.getPlayer());
        this.worker1.setPlayer(player1);
        assertEquals(player1,this.worker1.getPlayer());
        
        assertNull(this.worker2.getPlayer());
        this.worker2.setPlayer(this.player1);
        assertEquals(this.player1,this.worker2.getPlayer());
    }
    
    
    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WorkerTest.class);
    }    
}
