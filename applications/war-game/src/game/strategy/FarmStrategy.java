package game.strategy;

import game.board.Board;


/**
 * FarmStrategy class, a class which defiens the strategy of playing in a farm game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class FarmStrategy extends Strategy {

  /**
   * Creates a farm strategy which's defined by it's name and the board game.
   *
   * @param name The name of this strategy.
   * @param board The board game.
   */
  public FarmStrategy(String name, Board board) {
    super(name, board);
  }

  /**
   * Returns if the player deploys a worker, skips the round or convert his resources.
   *
   * @return 0 to deploy a worker and 1 to skip, 2 to convert his resources.
   */
  public abstract int skipOrDeployOrConvert();

  /**
   * Returns the quantity of the resource to transform.
   *
   * @return the quantity of the resource to transform.
   */
  public abstract int inputTheQuantity();

}
