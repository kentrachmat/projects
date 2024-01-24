package game.tile;

import static org.junit.Assert.*;
import org.junit.*;

import game.player.*;
import game.board.*;
import game.personnage.*;
import game.tile.resources.*;
import game.strategy.*;

public class DesertTileTest {
 
  // Desert tiles for these tests.
  private Tile desert1;
  private Tile desert2;
  private Tile desert3;
  private Tile mountain;

  // Personnages for these tests.
  private Personnage personnage1;
  private Personnage personnage2;
  
  // Players for these tests.
  private Player player1;
  private Player player2;
  
  private Board board1;
  
  private FarmStrategy strategy1; 
  private FarmStrategy strategy2; 
  private WarStrategy strategy3;

	@Before
  public void before(){
    // initialization of the tiles.
    this.desert1 = new DesertTile(1, 2, 3, 5); // for the farm game
    this.desert2 = new DesertTile(0, 4, 3, 5);
    this.desert3 = new DesertTile(0, 4, 2, 0); // for the war game
    this.mountain = new MountainTile(1, 1, 5, 8);

    // initialization of the personnages.
    this.personnage1 = new Army("Felix",6);
    this.personnage2 = new Worker("Tom");
		new Army("Alexis",2);
    
    // initialization of the board.
    this.board1 = new FarmBoard(10,10);

    // initialization of the strategy.
    this.strategy1 = new FarmHumanStrategy("strategy1",this.board1);
    this.strategy2 = new FarmHumanStrategy("strategy2",this.board1);
    this.strategy3 = new WarHumanStrategy("strategy3",this.board1);

    // initialization of the players.
    this.player1 = new FarmPlayer("Aziz",strategy1);
    this.player2 = new WarPlayer("Kent",strategy3);
    new FarmPlayer("Dorian",strategy2);
  }

	@Test
	public void getPosXTestIsOk() {
		assertEquals(1, this.desert1.getPosX());
    assertFalse(2 == this.desert1.getPosX());
	}

  @Test
  public void getPosYTestIsOk() {
    assertEquals(4, this.desert2.getPosY());
    assertFalse(2 == this.desert2.getPosY());
  }

  @Test
  public void getResourceTestIsOkWhenItReturnsTheRightResource() {
    Resource resource1 =  this.desert1.getResource();
    assertEquals(new SandResource(5), resource1);
  }

  @Test
  public void getResourceTestIsOkThatItReturnsANewResource() {
    Resource resource1 =  this.desert1.getResource();
    Resource resource2 =  this.desert1.getResource();
    Resource resource3 =  this.desert1.getResource();
    assertEquals(resource2, resource1);
    assertNotSame(resource2, resource1);
    assertEquals(resource2, resource1);
    assertNotSame(resource3, resource1);
    assertEquals(resource2, resource3);
    assertNotSame(resource3, resource2);
  }

  @Test
  public void getCharacterTest() {
    assertEquals("D", this.desert1.getCharacter());
    assertFalse("~".equals(this.desert1.getCharacter()));
    assertFalse("M".equals(this.desert1.getCharacter()));
  }

	@Test
	public void equalsTestWhenTheTilesAreSame() {
    Tile tile = this.desert1;
    assertEquals(this.desert1, tile);
    assertSame(this.desert1, tile);
  }

  @Test
  public void equalsTestWhenTheTilesAreNotSame() {
    assertEquals(this.desert1, this.desert2);
    assertNotSame(this.desert1, this.desert2);
  }

  @Test
  public void equalsTestWhenTheTileIsDifferent() {
    assertFalse(this.desert1.equals(this.mountain));
  }

	@Test
	public void setPersonnageTestIsOk() {
		this.desert1.setPersonnage(this.personnage1);
		assertEquals(this.personnage1, this.desert1.getPersonnage());
    this.desert1.setPersonnage(this.personnage2);
    assertEquals(this.personnage2, this.desert1.getPersonnage());
	}

  @Test
  public void getPlayerTestIsOk() {
    this.desert1.setPlayer(this.player1);
    assertEquals(this.player1, this.desert1.getPlayer());
    this.desert1.setPlayer(this.player2);
    assertEquals(this.player2, this.desert1.getPlayer());
    assertFalse(this.player1.equals(this.desert1.getPlayer()));
  }

  @Test
  public void setPlayerTestIsOk() {
    this.desert3.setPlayer(this.player2);
    assertEquals(this.player2, this.desert3.getPlayer());
    this.desert3.setPlayer(this.player2);
    assertEquals(this.player2, this.desert3.getPlayer());
  }

  @Test
  public void getPersonnageTestIsOk() {
    this.desert1.setPersonnage(this.personnage1);
    assertEquals(this.personnage1, this.desert1.getPersonnage());
    this.desert1.setPersonnage(this.personnage2);
    assertEquals(this.personnage2, this.desert1.getPersonnage());
    assertFalse(this.personnage1.equals(this.desert1.getPersonnage()));
  }

	@Test
	public void isEmptyTestWhenTheTileIsEmpty() {
		assertTrue(this.desert2.isEmpty());
	}

  @Test
  public void getValueResourceTestProduceTheRightValueForResourceInFarmGameIsOk() {
    assertEquals(5, this.desert2.getValueResource());
  }

  @Test
  public void getValueResourceTestProduceTheRightValueForResourceInWarGameIsOk() {
    assertEquals(0, this.desert3.getValueResource());
  }

  @Test
  public void getTileCostTestProduceTheRightCostInAFarmGameIsOk() {
    assertEquals(3, this.desert2.getTileCost());
    assertFalse(2 == this.desert2.getTileCost());
  }

  @Test
  public void getTileCostTestProduceTheRightCostInAWarGameIsOk() {
    assertEquals(2, this.desert3.getTileCost());
    assertFalse(3 == this.desert3.getTileCost());
  }

  @Test
  public void isEmptyTestWhenTheTileIsNotEmpty() {
    assertTrue(this.desert2.isEmpty());
    this.desert2.setPersonnage(this.personnage2);
    assertFalse(this.desert2.isEmpty());
    assertEquals(this.desert2.getPersonnage(), personnage2);
  }

  @Test
  public void emptyTileTestWhenTheTileIsAlreadyEmpty() {
    assertTrue(this.desert2.isEmpty());
    this.desert2.emptyTile();
    assertTrue(this.desert2.isEmpty());
  }

  @Test
  public void emptyTileTestWhenTheTileIsNotEmpty() {
    assertTrue(this.desert2.isEmpty());
    this.desert2.setPersonnage(this.personnage1);
    assertFalse(this.desert2.isEmpty());
    this.desert2.emptyTile();
    assertTrue(this.desert2.isEmpty());
  }

  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(DesertTileTest.class);
  }
}
