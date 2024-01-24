package game.tile;
import game.tile.resources.*;

/**
 * MountainTile class, a class by which we can model an mountain tile.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class MountainTile extends Tile {

	/**
	 * Creats a moutain tile which's defiend by the resource that she produce.
	 * @param x, the coordinate x
	 * @param y, the coordinate y
	 * @param cost, the cost of the tile
	 * @param value, the value
	 */
	public MountainTile(int x, int y, int cost, int value) {
		super(x, y, cost, value);
	}

	/**
	 * Returns the resource produced by this tile.
	 * @return The resource produced by this tile.
	 */
	public Resource getResource () {
		return new RockResource(this.value);
	}

	/**
   * Returns wether this MoutainTile is equal to the object passed on parameter or not.
   * @param o An object, any object to compare with this MoutainTile object.
   * @return True if the object passed on parameter is a MoutainTile object or not.
   */
  public boolean equals(Object o) {
	   if (o instanceof MountainTile) {
		   MountainTile other = (MountainTile) o;
		   if (super.getTileCost() == other.getTileCost()) {
			   return true;
		   }
	  }
	  return false;
  }

	/**
	 * returns the character that represents this moutain tile.
	 *
	 * @return the character that represents this moutain tile.
	 */
	public String getCharacter() {
		return "M";
	}

}
