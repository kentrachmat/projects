package game.board;

import game.tile.*;


/**
 * FarmBoard Class, a class by which we can model a farm game board.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class FarmBoard extends Board {

  /**
   * Creates a farm board game which's defined by it's width, height and it's tiles.
   *
   * @param height The height of this board to create.
   * @param width The width of this board to create.
   */
  public FarmBoard (int width, int height) {
    super(width, height);
  }

  /**
   * Returns a new tile which's defined by it's coordinates (x,y) and the cost of the tile specific to this war board that passed on parameter.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @return the new tile
   */
  public Tile newTile (int x, int y) {
    Tile tile;
    int index = alea.nextInt(4);
    if (index == 0) {
      tile = new DesertTile(x, y, 3, 5);
    } else if (index == 1) {
      tile = new PlainTile(x, y, 1, 2);
    } else if (index == 2) {
      tile = new ForestTile(x, y, 1, 2);
    } else {
      tile = new MountainTile(x, y, 5, 8);
    }
    return tile;
  }

 /**
   * Displays a representation of this Farm board.
   */
  @Override
  public void display() {
    System.out.println("\n\n                             Farm game ");
    super.display();
  }

}
