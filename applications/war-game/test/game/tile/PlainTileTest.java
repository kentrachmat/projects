package game.tile;

import static org.junit.Assert.*;
import org.junit.*;

import game.player.*;
import game.board.*;
import game.personnage.*;
import game.tile.resources.*;
import game.strategy.*;

public class PlainTileTest {

  // Desert tiles for these tests.
  private Tile plain1;
  private Tile plain2;
  private Tile plain3;
  private Tile mountain;

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
    this.plain1 = new PlainTile(1, 2, 1, 2); // for the farm game
    this.plain2 = new PlainTile(0, 4, 1, 2);
    this.plain3 = new PlainTile(0, 4, 1, 5); // for the war game
    this.mountain = new MountainTile(1, 1, 5, 8);

    // initialization of the personnages.
    this.personnage1 = new Army("Felix",6);
    this.personnage2 = new Worker("Tom");
	

    // initialization of the board.
    this.board1 = new FarmBoard(10,10);

    // initialization of the strategy.
    this.strategy1 = new FarmHumanStrategy("strategy1",this.board1);
    new FarmHumanStrategy("strategy2",this.board1);
    this.strategy3 = new WarHumanStrategy("strategy3",this.board1);

    // initialization of the players.
    this.player1 = new FarmPlayer("Aziz",strategy1);
    this.player2 = new WarPlayer("Kent",strategy3);
  }

	@Test
	public void getPosXTestIsOk() {
		assertEquals(1, this.plain1.getPosX());
    assertFalse(2 == this.plain1.getPosX());
	}

  @Test
  public void getPosYTestIsOk() {
    assertEquals(4, this.plain2.getPosY());
    assertFalse(2 == this.plain2.getPosY());
  }

  @Test
  public void getResourceTestIsOkWhenItReturnsTheRightResource() {
    Resource resource1 =  this.plain1.getResource();
    assertEquals(new CornResource(2), resource1);
  }

  @Test
  public void getResourceTestIsOkThatItReturnsANewResource() {
    Resource resource1 =  this.plain1.getResource();
    Resource resource2 =  this.plain1.getResource();
    Resource resource3 =  this.plain1.getResource();
    assertEquals(resource2, resource1);
    assertNotSame(resource2, resource1);
    assertEquals(resource2, resource1);
    assertNotSame(resource3, resource1);
    assertEquals(resource2, resource3);
    assertNotSame(resource3, resource2);
  }

  @Test
  public void getCharacterTest() {
    assertEquals("P", this.plain1.getCharacter());
    assertFalse("~".equals(this.plain1.getCharacter()));
    assertFalse("M".equals(this.plain1.getCharacter()));
  }

	@Test
	public void equalsTestWhenTheTilesAreSame() {
    Tile tile = this.plain1;
    assertEquals(this.plain1, tile);
    assertSame(this.plain1, tile);
  }

  @Test
  public void equalsTestWhenTheTilesAreNotSame() {
    assertEquals(this.plain1, this.plain2);
    assertNotSame(this.plain1, this.plain2);
  }

  @Test
  public void equalsTestWhenTheTileIsDifferent() {
    assertFalse(this.plain1.equals(this.mountain));
  }

	@Test
	public void setPersonnageTestIsOk() {
		this.plain1.setPersonnage(this.personnage1);
		assertEquals(this.personnage1, this.plain1.getPersonnage());
    this.plain1.setPersonnage(this.personnage2);
    assertEquals(this.personnage2, this.plain1.getPersonnage());
	}

  @Test
  public void getPlayerTestIsOk() {
    this.plain1.setPlayer(this.player1);
    assertEquals(this.player1, this.plain1.getPlayer());
    this.plain1.setPlayer(this.player2);
    assertEquals(this.player2, this.plain1.getPlayer());
    assertFalse(this.player1.equals(this.plain1.getPlayer()));
  }

  @Test
  public void setPlayerTestIsOk() {
    this.plain3.setPlayer(this.player2);
    assertEquals(this.player2, this.plain3.getPlayer());
    this.plain3.setPlayer(this.player2);
    assertEquals(this.player2, this.plain3.getPlayer());
  }

  @Test
  public void getPersonnageTestIsOk() {
    this.plain1.setPersonnage(this.personnage1);
    assertEquals(this.personnage1, this.plain1.getPersonnage());
    this.plain1.setPersonnage(this.personnage2);
    assertEquals(this.personnage2, this.plain1.getPersonnage());
    assertFalse(this.personnage1.equals(this.plain1.getPersonnage()));
  }

	@Test
	public void isEmptyTestWhenTheTileIsEmpty() {
		assertTrue(this.plain2.isEmpty());
	}

  @Test
  public void getValueResourceTestProduceTheRightValueForResourceInFarmGameIsOk() {
    assertEquals(2, this.plain2.getValueResource());
  }

  @Test
  public void getValueResourceTestProduceTheRightValueForResourceInWarGameIsOk() {
    assertEquals(5, this.plain3.getValueResource());
  }

  @Test
  public void getTileCostTestProduceTheRightCostInAFarmGameIsOk() {
    assertEquals(1, this.plain2.getTileCost());
    assertFalse(2 == this.plain2.getTileCost());
  }

  @Test
  public void getTileCostTestProduceTheRightCostInAWarGameIsOk() {
    assertEquals(1, this.plain3.getTileCost());
    assertFalse(4 == this.plain3.getTileCost());
  }

  @Test
  public void isEmptyTestWhenTheTileIsNotEmpty() {
    assertTrue(this.plain2.isEmpty());
    this.plain2.setPersonnage(this.personnage2);
    assertFalse(this.plain2.isEmpty());
    assertEquals(this.plain2.getPersonnage(), personnage2);
  }

  @Test
  public void emptyTileTestWhenTheTileIsAlreadyEmpty() {
    assertTrue(this.plain2.isEmpty());
    this.plain2.emptyTile();
    assertTrue(this.plain2.isEmpty());
  }

  @Test
  public void emptyTileTestWhenTheTileIsNotEmpty() {
    assertTrue(this.plain2.isEmpty());
    this.plain2.setPersonnage(this.personnage1);
    assertFalse(this.plain2.isEmpty());
    this.plain2.emptyTile();
    assertTrue(this.plain2.isEmpty());
  }

  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(PlainTileTest.class);
  }
}
