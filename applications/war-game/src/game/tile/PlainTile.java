package game.tile;
import game.tile.resources.*;

/**
 * PlainTile class, a class by which we can model a plain tile.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class PlainTile extends Tile {

	/**
   * Creats a desert tile which's defiend by the resource that she produce.
	 * @param x, the coordinate x
	 * @param y, the coordinate y
	 * @param cost, the cost of the tile
	 * @param value, the value
	 */
	public PlainTile(int y, int x, int cost, int value) {
		super(y, x, cost, value);
	}

	/**
	 * Returns the resource produced by this tile.
	 * @return The resource produced by this tile.
	 */
	public Resource getResource () {
		return new CornResource(this.value);
	}

	/**
	 * Returns wether this PlainTile is equal to the object passed on parameter or not.
	 * @param o An object, any object to compare with this PlainTile object.
	 * @return True if the object passed on parameter is a PlainTile object or not.
	 */
	public boolean equals(Object o) {
		if (o instanceof PlainTile) {
			PlainTile other = (PlainTile) o;
			if (super.getTileCost() == other.getTileCost()) {
				return true;
		 }
	 }
		return false;
	}

	/**
	 * returns the character that represents this plain tile.
	 *
	 * @return the character that represents this plain tile.
	 */
	public String getCharacter() {
		return "P";
	}

}
