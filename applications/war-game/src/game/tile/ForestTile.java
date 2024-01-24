package game.tile;
import game.tile.resources.*;

/**
 * ForestTile Class, a class by which we can model a forest tile on a board game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class ForestTile extends Tile {

	/**
	 * Creates a desert tile which's defined by the resource that she produce.
	 * @param x, the coordinate x
	 * @param y, the coordinate y
	 * @param cost, the cost of the tile
	 * @param value, the value
	 */
	public ForestTile(int x, int y, int cost, int value) {
		super(x, y, cost, value);
	}

	/**
	 * Returns the resource produced by this tile.
	 * @param value the value of the resource
	 * @return The resource produced by this tile.
	 */
	public Resource getResource (int value) {
		return new WoodResource(value);
	}

	/**
	 * Returns whether this ForestTile is equal to the object passed on parameter or not.
	 * @param o An object, any object to compare with this ForestTile object.
	 * @return True if the object passed on parameter is a ForestTile object or not.
	 */
	public boolean equals(Object o) {
		 if (o instanceof ForestTile) {
			 ForestTile other = (ForestTile) o;
			 if (super.getTileCost() == other.getTileCost()) {
				 return true;
			 }
		}
		return false;
	}

	/**
	 * Returns the resource produced by this forest tile.
	 * @return The resource produced by this forest tile.
	 */
	public Resource getResource () {
		return new WoodResource(this.value);
	}


	/**
	 * returns the character that represents this forest tile.
	 *
	 * @return the character that represents this forest tile.
	 */
	public String getCharacter() {
		return "F";
	}

}
