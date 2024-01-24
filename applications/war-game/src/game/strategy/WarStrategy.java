package game.strategy;

import game.board.Board;


/**
 * WarStrategy class, a class which defiens the strategy of playing in a War game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class WarStrategy extends Strategy {

  /**
   * Creates a war strategy which's defined by it's name and the board game.
   *
   * @param name The name of this strategy.
   * @param board The board game.
   */
  public WarStrategy(String name, Board board) {
    super(name, board);
  }

  /**
   * Returns the size of an army.
   *
   * @return The size of an army.
   */
  public abstract int inputArmySize();

  /**
   * Returns if the player deploys an army or skips the round.
   *
   * @return 0 to deploy a worker and 1 to skip.
   */
  public abstract int skipOrDeploy();

}
