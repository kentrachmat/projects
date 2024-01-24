package game.strategy;

import game.board.Board;
import game.exception.UnknownTileBoardException;
import game.tile.*;


/**
 * WarHumanStrategy class, a class that defines a strategy by which the player can plays.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class WarHumanStrategy extends WarStrategy {

  /**
   * Creates a war human strategy which's defined by it's name and the board game.
   *
   * @param name The name of this strategy.
   * @param board The board game.
   */
  public WarHumanStrategy(String name, Board board) {
    super(name, board);
  }

  /**
   * Returns how many resources to transform entered by a player.
   *
   * @return 1 to transform all resources and 0 to transform one resource.
   */
  public int inputHowMnayResourcesToTransform () {
	  System.out.print("   Resources convertion :\n      - Enter 0 to transform the needed resources.\n      - Enter 1 to transform all your resources.\n      Your choice : ");
    return Strategy.readPlayerChoice(1);
  }

  /**
   * Returns if the player deploys an army or he skips the round.
   *
   * @return 0 to deploy an army and 1 to skip.
   */
   @Override
  public int skipOrDeploy () {
    System.out.print("   I ) Chose the action to execute.\n" +
      "      - Enter 0 to deploy an army.\n" +
      "      - Enter 1 to skip this round. \n      Your choice : ");
    return Strategy.readPlayerChoice(0);
  }

  /**
   * Returns the size of an army entered by a player.
   *
   * @return The size of an army.
   */
   @Override
  public int inputArmySize () {
    System.out.print("\n   Choosed to deploy an army.\n      - Enter the size of the army : ");
    return Strategy.readPlayerChoice(3);
  }

  /**
	 * Returns the tile entered by the player where to deploy an army.
	 *
	 * @return the tile inputed
	 */
   @Override
  public Tile inputTileStrategy () {
		System.out.print("      - Enter the positions (x, y) of the tile where you want to deploy the army.\n        Position X : ");
		int x = Strategy.readPlayerChoice(0);
		System.out.print("        Position Y : ");
		int y = Strategy.readPlayerChoice(0);
    Tile tile = null;
    try {
			tile = this.board.getTile(x, y);
			if (!tile.isEmpty()) {
				System.out.println("\n   Unfortunately tile is already occuped !");
				tile = null;
			} else if (tile.equals(new OceanTile(0,0))) {
				System.out.println("\n   Unfortunately a personage can't be deployed on ocean tile !");
				tile = null;
			}
		} catch (UnknownTileBoardException e) {
			System.out.println("\n   Unfortunately tile does not exist !");
			tile = null;
		}
		return tile;
  }

}
