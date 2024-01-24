package game.action;

import game.board.*;
import game.exception.UnknownTileBoardException;
import game.player.*;
import game.personnage.*;


/**
 * FarmActionDeploy class, a class by which we can model the action by which that a farm player can deploy a worker.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class FarmActionDeploy implements Action {

  // The board where this action will be made.
  private Board board;

  // The player that make this action.
  private Player player;

  // This tile vertical coordinate Y.
  private int y;

  // This tile horizontal coordinate X.
  private int x;

  // The occupant of this tile.
  private Worker p;


  /**
   * Creates a farm action deploy that a player can play during a game.
   *
   * @param player The player that play this action.
   * @param board The board game where this action will be played.
   * @param x, The coordinate x of the tile where this action will be played on.
   * @param y, The coordinate y of the tile where this action will be played on.
   * @param personnage, The personage to deploy.
   */
  public FarmActionDeploy (Player player, Board board, int x, int y, Worker personnage) {
	  this.player = player;
    this.board = board;
    this.p = personnage;
    this.x = x;
    this.y = y;
  }

  /**
   * Executes this farm action deploy by a player on a farm game.
   */
  public void execute () {
    this.deployWorker();
    this.display();
  }

  /**
   * Deploys the worker.
   */
  private void deployWorker () {
    try {
      this.board.getTile(this.x, this.y).setPlayer(this.player);
      this.board.getTile(this.x, this.y).setPersonnage(this.p);
      this.board.occupeTile();
      this.p.setTile(this.board.getTile(this.x, this.y));
      this.p.setPlayer(this.player);
      this.player.addTerritory(this.board.getTile(this.x, this.y));
    } catch (UnknownTileBoardException e) {
      System.out.println("   Tile doesn't exist !");
    }
  }

  /**
   * Displays the representation of this skip farm action skip.
   */
  private void display () {
    System.out.println("  " + this.player.getName() + " deployed a worker on the tile (" + this.x + ", " + this.y + ")");
  }

}
