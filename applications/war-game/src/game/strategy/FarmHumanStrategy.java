package game.strategy;

import game.board.Board;
import game.exception.UnknownTileBoardException;
import game.tile.OceanTile;
import game.tile.Tile;


/**
 * FarmHumanStrategy class, a class which defiens the strategy of playing randomly in a farm game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class FarmHumanStrategy extends FarmStrategy {

  /**
   * Creates a farm human strategy which's defined by it's name and the board game.
   *
   * @param name The name of this strategy.
   * @param board The board game.
   */
  public FarmHumanStrategy(String name, Board board) {
		super(name, board);
  }

  /**
   * Returns how many resources to transform.
   *
   * @return 1 to transform all resources and 0 to transform one resource.
   */
   @Override
   public int inputHowMnayResourcesToTransform() {
     System.out.print("\n   Resources transform chosed."+ "\n      - Enter 0 to transform only the needed resources." +
     "\n      - Enter 1 to transform all your resources."
         + "\n      - Enter 2 to chose the ressources to transform."
         + "\n      Your choice : ");
	    return Strategy.readPlayerChoice(1);
	}

   /**
    * Returns the quantity of the resource to transform.
    *
    * @return the tile randomly chosen.
    */
   public int inputTheQuantity() {
	   return Strategy.readPlayerChoice(0);
   }

  /**
   * Returns if the player deploys a worker, skips the round or convert his resources.
   *
   * @return 0 to deploy a worker and 1 to skip, 2 to convert his resources.
   */
  public int skipOrDeployOrConvert () {
    System.out.print("   I) Chose the action to execute :\n      - Enter 0 to deploy a worker."
    		+ "\n      - Enter 1 to skip this round. "
    		+ "\n      - Enter 2 to convert the resources."+ "\n      Your choice : ");
    return Strategy.readPlayerChoice(1);
  }

  /**
   * Returns the tile where to deploy a worker.
   *
   * @return the tile chosed by the player.
   */
  public Tile inputTileStrategy () {
		System.out.print("\n   Enter the positions (x, y) of the tile where you want to deploy the worker.\n       Position X : ");
		int y = Strategy.readPlayerChoice(0);
		System.out.print("       Position Y : ");
		int x = Strategy.readPlayerChoice(0);
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
