
package game.tile;
import game.tile.resources.*;

/**
 * OceanTile class, a class by which we can model an ocean tile.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class OceanTile extends Tile {

  // Ocean tile.
	public static final OceanTile OCEAN = new OceanTile(0, 0);

	/**
	 * Creats a desert tile which's defiend by the resource that she produce.
	 * @param x, the coordinate x
	 * @param y, the coordinate y
	 */
	public OceanTile(int x, int y) {
		super(x, y, 0, 0);
	}

	/**
	 * Returns wether this OceanTile is equal to the object passed on parameter or not.
	 * @param o An object, any object to compare with this OceanTile object.
	 * @return True if the object passed on parameter is a OceanTile object or not.
	 */
	public boolean equals(Object o) {
		 if (o instanceof OceanTile) {
			 OceanTile other = (OceanTile) o;
			 if (super.getTileCost() == other.getTileCost()) {
				 return true;
			 }
		}
		return false;
	}

	public Resource getResource() {
		return null;
	}

	/**
	 * returns the character that represents this ocean tile.
	 *
	 * @return the character that represents this ocean tile.
	 */
	public String getCharacter() {
		return "~";
	}
}
