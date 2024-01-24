package game.tile;

import static org.junit.Assert.*;
import org.junit.*;

import game.player.*;
import game.board.*;
import game.personnage.*;
import game.tile.resources.*;
import game.strategy.*;

public class MountainTileTest {

	// Mountain tiles for these tests.
  private Tile mountain1;
  private Tile mountain2;
  private Tile mountain3;
  private Tile forest;

  // Personnages for these tests.
	private Personnage personnage1;
	private Personnage personnage2;

  // Players for these tests.
	private Player player1;
	private Player player2;

  private Board board1;
  private FarmStrategy strategy1; 
  private WarStrategy strategy3;

	@Before
  public void before(){
    // initialization of the tiles.
    this.mountain1 = new MountainTile(1, 2, 5, 8); // for the farm game
    this.mountain2 = new MountainTile(0, 4, 5, 8); // for the farm game
    this.mountain3 = new MountainTile(0, 4, 1, 0); // for the war game
    this.forest = new ForestTile(1, 1, 1, 2);

    // initialization of the personnages.
    this.personnage1 = new Army("Felix",6);
    this.personnage2 = new Worker("Tom");

    // initialization of the board.
    this.board1 = new FarmBoard(10,10);

    // initialization of the strategy.
    this.strategy1 = new FarmHumanStrategy("strategy1",this.board1);
    this.strategy3 = new WarHumanStrategy("strategy3",this.board1);

    // initialization of the players.
    this.player1 = new FarmPlayer("Aziz",strategy1);
    this.player2 = new WarPlayer("Kent",strategy3);
  }

	@Test
	public void getPosXTestIsOk() {
		assertEquals(1, this.mountain1.getPosX());
    assertFalse(2 == this.mountain1.getPosX());
	}

  @Test
  public void getPosYTestIsOk() {
    assertEquals(4, this.mountain2.getPosY());
    assertFalse(2 == this.mountain2.getPosY());
  }

  @Test
  public void getResourceTestIsOkWhenItReturnsTheRightResource() {
    Resource resource1 =  this.mountain1.getResource();
    assertEquals(new RockResource(8), resource1);
  }

  @Test
  public void getResourceTestIsOkThatItReturnsANewResource() {
    Resource resource1 =  this.mountain1.getResource();
    Resource resource2 =  this.mountain1.getResource();
    Resource resource3 =  this.mountain1.getResource();
    assertEquals(resource2, resource1);
    assertNotSame(resource2, resource1);
    assertEquals(resource2, resource1);
    assertNotSame(resource3, resource1);
    assertEquals(resource2, resource3);
    assertNotSame(resource3, resource2);
  }

  @Test
  public void getCharacterTest() {
    assertEquals("M", this.mountain1.getCharacter());
    assertFalse("~".equals(this.mountain1.getCharacter()));
    assertFalse("P".equals(this.mountain1.getCharacter()));
  }

	@Test
	public void equalsTestWhenTheTilesAreSame() {
    Tile tile = this.mountain1;
    assertEquals(this.mountain1, tile);
    assertSame(this.mountain1, tile);
  }

  @Test
  public void equalsTestWhenTheTilesAreNotSame() {
    assertEquals(this.mountain1, this.mountain2);
    assertNotSame(this.mountain1, this.mountain2);
  }

  @Test
  public void equalsTestWhenTheTileIsDifferent() {
    assertFalse(this.mountain1.equals(this.forest));
  }

	@Test
	public void setPersonnageTestIsOk() {
		this.mountain1.setPersonnage(this.personnage1);
		assertEquals(this.personnage1, this.mountain1.getPersonnage());
    this.mountain1.setPersonnage(this.personnage2);
    assertEquals(this.personnage2, this.mountain1.getPersonnage());
	}

  @Test
  public void getPlayerTestIsOk() {
    this.mountain1.setPlayer(this.player1);
    assertEquals(this.player1, this.mountain1.getPlayer());
    this.mountain1.setPlayer(this.player2);
    assertEquals(this.player2, this.mountain1.getPlayer());
    assertFalse(this.player1.equals(this.mountain1.getPlayer()));
  }

  @Test
  public void setPlayerTestIsOk() {
    this.mountain3.setPlayer(this.player2);
    assertEquals(this.player2, this.mountain3.getPlayer());
    this.mountain3.setPlayer(this.player2);
    assertEquals(this.player2, this.mountain3.getPlayer());
  }

  @Test
  public void getPersonnageTestIsOk() {
    this.mountain1.setPersonnage(this.personnage1);
    assertEquals(this.personnage1, this.mountain1.getPersonnage());
    this.mountain1.setPersonnage(this.personnage2);
    assertEquals(this.personnage2, this.mountain1.getPersonnage());
    assertFalse(this.personnage1.equals(this.mountain1.getPersonnage()));
  }

	@Test
	public void isEmptyTestWhenTheTileIsEmpty() {
		assertTrue(this.mountain2.isEmpty());
	}

  @Test
  public void getValueResourceTestProduceTheRightValueForResourceInFarmGameIsOk() {
    assertEquals(8, this.mountain2.getValueResource());
  }

  @Test
  public void getValueResourceTestProduceTheRightValueForResourceInWarGameIsOk() {
    assertEquals(0, this.mountain3.getValueResource());
  }

  @Test
  public void getTileCostTestProduceTheRightCostInAFarmGameIsOk() {
    assertEquals(5, this.mountain2.getTileCost());
    assertFalse(1 == this.mountain2.getTileCost());
  }

  @Test
  public void getTileCostTestProduceTheRightCostInAWarGameIsOk() {
    assertEquals(1, this.mountain3.getTileCost());
    assertFalse(5 == this.mountain3.getTileCost());
  }

  @Test
  public void isEmptyTestWhenTheTileIsNotEmpty() {
    assertTrue(this.mountain2.isEmpty());
    this.mountain2.setPersonnage(this.personnage2);
    assertFalse(this.mountain2.isEmpty());
    assertEquals(this.mountain2.getPersonnage(), personnage2);
  }

  @Test
  public void emptyTileTestWhenTheTileIsAlreadyEmpty() {
    assertTrue(this.mountain2.isEmpty());
    this.mountain2.emptyTile();
    assertTrue(this.mountain2.isEmpty());
  }

  @Test
  public void emptyTileTestWhenTheTileIsNotEmpty() {
    assertTrue(this.mountain2.isEmpty());
    this.mountain2.setPersonnage(this.personnage1);
    assertFalse(this.mountain2.isEmpty());
    this.mountain2.emptyTile();
    assertTrue(this.mountain2.isEmpty());
  }

  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(MountainTileTest.class);
  }
}
