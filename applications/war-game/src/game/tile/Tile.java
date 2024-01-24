package game.tile;
import game.tile.resources.*;
import game.personnage.*;
import game.player.*;

/**
 * Tile class, a class by which we can model a tile (set a personage on this tile, harvest the resource that this tile produce except for an ocean tile).
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class Tile {

	// This tile horizontal coordinate X.
	protected int posX;

	// This tile vertical coordinate Y.
	protected int posY;

	// The value of the resource that this tile produce.
	protected int value;

	// The occupant of this tile.
	protected Personnage occupant;

	// This tile cost.
	protected int cost;

	// This players who plays this tile.
	protected Player player;


	/**
	 * Creates a tile which's defiend by it's coordinates X and Y, also with the resource that this tile produce.
     *
	 * @param x, this tile horizontal coordinate.
	 * @param y, this tile vertical coordinate.
	 * @param cost, the cost of this tile
	 * @param value, the value concerning the tile
	 */
	public Tile (int x, int y, int cost, int value) {
		this.posX = x;
		this.posY = y;
		this.cost = cost;
		this.value = value;
		this.occupant = null ;
	}

	/**
	 * Returns this tile coordinate X.
   *
	 * @return This tile coordinate X.
	 */
	public int getPosX () {
    return this.posX;
	}

	/**
	 * Returns this tile coordinate Y.
	 *
	 * @return This tile coordinate Y.
	 */
	public int getPosY () {
    return this.posY;
	}


	/**
	 * Returns the cost for which the worker on this tile must be paid.
   *
	 * @return  The cost of this tile.
	 */
  public int getTileCost () {
    return this.cost;
  }

	/**
	 * Returns the value of the resource produced by this tile.
	 *
	 * @return the value The resource produced by this tile.
	 */
	public int getValueResource () {
		return this.value;
	}

	/**
	 * Returns the resource produced by this tile.
	 *
	 * @return The resource produced by this tile.
	 */
	public abstract Resource getResource ();

	/**
	 * returns the character that represents this tile.
	 *
	 * @return the character that represents this tile.
	 */
	public abstract String getCharacter();

	/**
	 * Returns the occupant of this tile.
   *
	 * @return the occupant of this tile.
	 */
  public Personnage getPersonnage () {
    return this.occupant;
  }

	/**
	 * Sets a personnage on this tile.
   *
	 * @param personnage, the personnage that will occupes this tile.
	 */
  public void setPersonnage (Personnage personnage) {
		this.occupant = personnage;
  }

	/**
	 * Empty this tile of any occupation of personge.
	 */
	public void emptyTile () {
		this.occupant = null;
		this.player = null;
	}

	/**
	 * Returns the player who plays this tile.
	 *
	 * @return the player who plays this tile.
	 */
	public Player getPlayer () {
		return this.player;
	}

	/**
	 * Sets a player on this tile.
  	 *
	 * @param player, the player that will pay this tile.
	 */
	public void setPlayer (Player player) {
		this.player = player;
	}

	/**
	 * Returns whether this tile is is empty or not.
   *
	 * @return true, if this tile is empty, false otherwise.
	 */
	public boolean isEmpty () {
		return this.occupant == null;
	}

}
