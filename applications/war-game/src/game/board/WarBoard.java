package game.board;

import game.exception.UnknownTileBoardException;
import game.personnage.Army;
import game.tile.*;


/**
 * WarBoard Class, a class by which's we can model a war game board.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class WarBoard extends Board {

  /**
   * Creates a war board game which defined by it's width, height and it's tiles.
   *
   * @param height The height of this board to create.
   * @param width The width of this board to create.
   */
  public WarBoard (int width, int height) {
    super(width, height);
  }

  /**
   * Returns a new tile which's defined by it's coordinates (x,y) and the cost of the tile specific to this war board that passed on parameter.
   *
   * @param x The tile abscissa of the tile.
   * @param y The tile ordinate of the tile.
   * @return the new tile defined by it's coordinates (x,y) and it's cost.
   */
  public Tile newTile (int x, int y) {
    Tile tile;
    int index = alea.nextInt(4);
    if (index == 0) {
      tile = new DesertTile(x, y, 2, 0);
    } else if (index == 1) {
      tile = new PlainTile(x, y, 1, 5);
    } else if (index == 2) {
      tile = new ForestTile(x, y, 1, 1);
    } else {
      tile = new MountainTile(x, y, 1, 0);
    }
    return tile;
  }

  /**
   * Returns whether the army passed on parameter can be deployed on this board tile.
   *
   * @param x The tile abscissa of the tile.
   * @param y The tile ordinate of the tile.
   * @param army The army to deploy.
   * @return the new tile defined by it's coordinates (x,y) and it's cost.
   */
  public boolean canDeployArmy (int x, int y, Army army) {
    try {
  		if (this.getTile(x, y).isEmpty() && this.getTile(x, y).getValueResource() == 0 && army.getArmySize() > 3) {
  		  return false;
  		} else if (!this.getTile(x, y).isEmpty()) {
        return false;
      }
  	} catch (UnknownTileBoardException e) {
  		return false;
  	}
    return true;
  }

  /**
   * Displays a representation of this war board.
   */
  @Override
  public void display() {
    System.out.println("\n\n                                           War game board \n\n");
    super.display();
  }

}
