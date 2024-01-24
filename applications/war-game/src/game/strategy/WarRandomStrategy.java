package game.strategy;

import java.util.*;
import game.board.Board;
import game.tile.*;


/**
 * WarRandomStrategy class, a class which defines the strategy of playing randomly in a war game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class WarRandomStrategy extends WarStrategy {

  // Provides random numbers.
  protected static Random alea = new Random();

  /**
   * Creates a war random strategy which's defined by it's name and the board game.
   *
   * @param name The name of this strategy.
   * @param board The board game.
   */
  public WarRandomStrategy(String name, Board board) {
    super(name, board);
  }

  /**
   * Returns randomly how many resources to transform.
   *
   * @return 1 to transform all resources and 0 to transform one resource.
   */
  public int inputHowMnayResourcesToTransform () {
    int index = alea.nextInt(2);
	  System.out.print("   Resources convertion :\n      - Enter 0 to transform the needed resources.\n      - Enter 1 to transform all your resources.\n      Your choice : " + index);
    return index;
  }


  /**
   * Returns randomly if the player deploys an army or he skips the round.
   *
   * @return 0 to deploy an army and 1 to skip.
   */
   @Override
  public int skipOrDeploy () {
    int index =  alea.nextInt(2);
    System.out.print("   I ) Chose the action to execute.\n" +
      "      - Enter 0 to deploy an army.\n" +
      "      - Enter 1 to skip this round. \n      Your choice : " + index + "\n\n" );
    return alea.nextInt(2);
  }

  /**
   * Returns randomly the size of an army.
   *
   * @return The size of an army.
   */
   @Override
  public int inputArmySize () {
    int index = alea.nextInt(5) + 1;
    System.out.print("\n   Choosed to deploy an army.\n      - Enter the size of the army : " + index);
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
    System.out.print("\n      - Enter the positions (x, y) of the tile where you want to deploy the army.\n        Position X : " + tile.getPosX());
    System.out.print("\n        Position Y : " + tile.getPosY());
    if (!tile.isEmpty()) {
      System.out.println("\n   Unfortunately tile is already occuped !");
      tile = null;
    } else if (tile.equals(new OceanTile(0,0))) {
      System.out.println("\n   Unfortunately a personage can't be deployed on ocean tile !");
      tile = null;
    }
    return tile;
  }


}
