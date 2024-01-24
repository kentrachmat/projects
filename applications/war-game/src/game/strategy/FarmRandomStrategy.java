package game.strategy;

import java.util.*;
import game.board.Board;
import game.tile.Tile;


/**
 * FarmRandomStrategy class, a class which defiens the strategy of playing randomly in a farm game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class FarmRandomStrategy extends FarmStrategy {

  // Provides random numbers.
  protected static Random alea = new Random();

  /**
   * Creates a farm random strategy which's defined by it's name and the board game.
   *
   * @param name The name of this strategy.
   * @param board The board game.
   */
  public FarmRandomStrategy(String name, Board board) {
		super(name, board);
  }

  /**
   * Returns randomly how many resources to transform.
   *
   * @return 1 to transform all resources and 0 to transform one resource.
   */
   @Override
   public int inputHowMnayResourcesToTransform() {
     int index = alea.nextInt(3);
		 System.out.print("\n   Resources transform chosed."+ "\n      - Enter 0 to transform only the needed resources." +
     "\n      - Enter 1 to transform all your resources."
         + "\n      - Enter 2 to chose the ressources to transform."
         + "\n      Your choice : " + index);
	    return index;
	}

   /**
    * Returns randomly the quantity of the resource to transform.
    *
    * @return the tile randomly chosen.
    */
   public int inputTheQuantity() {
	    return alea.nextInt(2);
   }

  /**
   * Returns randomly if the player deploys a worker, skips the round or convert his resources.
   *
   * @return 0 to deploy a worker and 1 to skip, 2 to convert his resources.
   */
  public int skipOrDeployOrConvert () {
    int index = alea.nextInt(3);
    System.out.print("   I) Chose the action to execute :\n      - Enter 0 to deploy a worker."
    		+ "\n      - Enter 1 to skip this round. "
    		+ "\n      - Enter 2 to convert the resources."+ "\n      Your choice : " + index);
    return index;
  }

  /**
   * Returns the tile where to deploy an army randomly chosen.
   *
   * @return the tile randomly chosen.
   */
	 @Override
  public Tile inputTileStrategy () {
    ArrayList<Tile> tiles = this.board.getAllTiles();
    Tile tile = tiles.get(alea.nextInt(tiles.size()));
    System.out.print("\n   Enter the positions (x, y) of the tile where you want to deploy the worker.\n       Position X : " + tile.getPosY());
    System.out.print("       Position Y : " + tile.getPosX());
    return tile;
  }

}
