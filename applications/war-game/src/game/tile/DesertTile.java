package game.tile;
import game.tile.resources.*;

/**
 * DesertTile class, a class by which we can model a desert tile in a board game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class DesertTile extends Tile {

	/**
	 * Creates a desert tile which's defined by the resource that she produce.
	 *
	 * @param x, the coordinate x
	 * @param y, the coordinate y
	 * @param cost, the cost of the tile
	 * @param value, the value
	 */
	public DesertTile(int x, int y, int cost, int value) {
		super(x, y, cost, value);
	}

	/**
	 * Returns the resource produced by this tile.
	 *
	 * @return The resource produced by this tile.
	 */
	public Resource getResource () {
		return new SandResource(this.value);
	}

	/**
	 * Returns whether this DesertTile is equal to the object passed on parameter or not.
   *
	 * @param o An object, any object to compare with this DesertTile object.
	 * @return True if the object passed on parameter is a DesertTile object or not.
	 */
	public boolean equals(Object o) {
		 if (o instanceof DesertTile) {
			 DesertTile other = (DesertTile) o;
			 if (super.getTileCost() == other.getTileCost() && super.getValueResource() == other.getValueResource()) {
				 return true;
			 }
		}
		return false;
	}

	/**
	 * returns the character that represents this desert tile.
	 *
	 * @return the character that represents this desert tile.
	 */
	public String getCharacter() {
		return "D";
	}

}
