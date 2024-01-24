package game.action;

import game.player.*;


/**
 * SkipAction class, a class by which we can model the action of skipping a round during a game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class SkipAction implements Action {

  // The player that make this skip action.
  private Player player;

  /**
   * Creates a skip action that a player can play during a game.
   *
   * @param player, The player that plays this action.
   */
  public SkipAction (Player player) {
    this.player = player;
  }

  /**
   * Skip a round in game.
   */
  public void execute () {
    this.display();
  }

  /**
   * Displays the representation of this skip action.
   */
  public void display () {
    System.out.println("   " + this.player.getName() + " skipped the round !\n");
  }
}
