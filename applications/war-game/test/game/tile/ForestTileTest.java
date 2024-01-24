package game.tile;

import static org.junit.Assert.*;
import org.junit.*;

import game.player.*;
import game.board.*;
import game.personnage.*;
import game.tile.resources.*;
import game.strategy.*;

public class ForestTileTest {

  // Forest tiles for these tests.
  private Tile forest1;
  private Tile forest2;
  private Tile forest3;
  private Tile plain;

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
    this.forest1 = new ForestTile(1, 2, 1, 2); // for the farm game
    this.forest2 = new ForestTile(0, 4, 1, 2); // for the farm game
    this.forest3 = new ForestTile(0, 4, 1, 1); // for the war game
    this.plain = new PlainTile(1, 1, 5, 8);

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
		assertEquals(1, this.forest1.getPosX());
    assertFalse(2 == this.forest1.getPosX());
	}

  @Test
  public void getPosYTestIsOk() {
    assertEquals(4, this.forest2.getPosY());
    assertFalse(2 == this.forest2.getPosY());
  }

  @Test
  public void getResourceTestIsOkWhenItReturnsTheRightResource() {
    Resource resource1 =  this.forest1.getResource();
    assertEquals(new WoodResource(2), resource1);
  }

  @Test
  public void getResourceTestIsOkThatItReturnsANewResource() {
    Resource resource1 =  this.forest1.getResource();
    Resource resource2 =  this.forest1.getResource();
    Resource resource3 =  this.forest1.getResource();
    assertEquals(resource2, resource1);
    assertNotSame(resource2, resource1);
    assertEquals(resource2, resource1);
    assertNotSame(resource3, resource1);
    assertEquals(resource2, resource3);
    assertNotSame(resource3, resource2);
  }

  @Test
  public void getCharacterTest() {
    assertEquals("F", this.forest1.getCharacter());
    assertFalse("~".equals(this.forest1.getCharacter()));
    assertFalse("P".equals(this.forest1.getCharacter()));
  }

	@Test
	public void equalsTestWhenTheTilesAreSame() {
    Tile tile = this.forest1;
    assertEquals(this.forest1, tile);
    assertSame(this.forest1, tile);
  }

  @Test
  public void equalsTestWhenTheTilesAreNotSame() {
    assertEquals(this.forest1, this.forest2);
    assertNotSame(this.forest1, this.forest2);
  }

  @Test
  public void equalsTestWhenTheTileIsDifferent() {
    assertFalse(this.forest1.equals(this.plain));
  }

	@Test
	public void setPersonnageTestIsOk() {
		this.forest1.setPersonnage(this.personnage1);
		assertEquals(this.personnage1, this.forest1.getPersonnage());
    this.forest1.setPersonnage(this.personnage2);
    assertEquals(this.personnage2, this.forest1.getPersonnage());
	}

  @Test
  public void getPlayerTestIsOk() {
    this.forest1.setPlayer(this.player1);
    assertEquals(this.player1, this.forest1.getPlayer());
    this.forest1.setPlayer(this.player2);
    assertEquals(this.player2, this.forest1.getPlayer());
    assertFalse(this.player1.equals(this.forest1.getPlayer()));
  }

  @Test
  public void setPlayerTestIsOk() {
    this.forest3.setPlayer(this.player2);
    assertEquals(this.player2, this.forest3.getPlayer());
    this.forest3.setPlayer(this.player2);
    assertEquals(this.player2, this.forest3.getPlayer());
  }

  @Test
  public void getPersonnageTestIsOk() {
    this.forest1.setPersonnage(this.personnage1);
    assertEquals(this.personnage1, this.forest1.getPersonnage());
    this.forest1.setPersonnage(this.personnage2);
    assertEquals(this.personnage2, this.forest1.getPersonnage());
    assertFalse(this.personnage1.equals(this.forest1.getPersonnage()));
  }

	@Test
	public void isEmptyTestWhenTheTileIsEmpty() {
		assertTrue(this.forest2.isEmpty());
	}

  @Test
  public void getValueResourceTestProduceTheRightValueForResourceInFarmGameIsOk() {
    assertEquals(2, this.forest2.getValueResource());
  }

  @Test
  public void getValueResourceTestProduceTheRightValueForResourceInWarGameIsOk() {
    assertEquals(1, this.forest3.getValueResource());
  }

  @Test
  public void getTileCostTestProduceTheRightCostInAFarmGameIsOk() {
    assertEquals(1, this.forest2.getTileCost());
    assertFalse(2 == this.forest2.getTileCost());
  }

  @Test
  public void getTileCostTestProduceTheRightCostInAWarGameIsOk() {
    assertEquals(1, this.forest3.getTileCost());
    assertFalse(5 == this.forest3.getTileCost());
  }

  @Test
  public void isEmptyTestWhenTheTileIsNotEmpty() {
    assertTrue(this.forest2.isEmpty());
    this.forest2.setPersonnage(this.personnage2);
    assertFalse(this.forest2.isEmpty());
    assertEquals(this.forest2.getPersonnage(), personnage2);
  }

  @Test
  public void emptyTileTestWhenTheTileIsAlreadyEmpty() {
    assertTrue(this.forest2.isEmpty());
    this.forest2.emptyTile();
    assertTrue(this.forest2.isEmpty());
  }

  @Test
  public void emptyTileTestWhenTheTileIsNotEmpty() {
    assertTrue(this.forest2.isEmpty());
    this.forest2.setPersonnage(this.personnage1);
    assertFalse(this.forest2.isEmpty());
    this.forest2.emptyTile();
    assertTrue(this.forest2.isEmpty());
  }

  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(ForestTileTest.class);
  }
}
