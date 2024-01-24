package game.action;

import game.board.*;
import game.exception.*;
import game.player.*;
import game.personnage.*;
import game.tile.*;
import java.util.*;


/**
 * WarActionDeploy class, a class by which we can model the action of deploying an army during a game.
 */
public class WarActionDeploy implements Action {

  // The board where this action will be made.
  private Board board;

  // The player that make this action.
  private Player player;

  // This tile vertical coordinate Y.
  private int y;

  // This tile horizontal coordinate X.
  private int x;

  // The occupant of this tile.
  private Army p;



  /**
   * Creates an action that player can make during a game.
   *
   * @param player the player concerns
   * @param board the board concerns
   * @param x the abscissa
   * @param y the ordinate
   * @param personnage the army
   */
  public WarActionDeploy (Player player, Board board, int x, int y, Army personnage) {
    this.player = player;
    this.board = board;
    this.p = personnage;
    this.x = x;
    this.y = y;
  }

  /**
   * A war deploy action that a player can execute on a game.
   */
  public void execute () {
    this.deployArmy();
  }

  /**
   * Returns whether this personage was deployed successfully or not.
   *
   * @param personnage the personage to deploy on the tile passed on parameter.
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @return True if this army was deployed successfully, false otherwise.
   */
  private boolean deployArmy () {
    ArrayList<Tile> neighbours = this.board.getTiles(this.x, this.y);
    try {
      this.board.getTile(this.x, this.y).setPlayer(this.player);
	  	this.board.getTile(this.x, this.y).setPersonnage(this.p);
	  	this.player.addTerritory(this.board.getTile(this.x, this.y));
	    this.board.getTile(this.x, this.y).getPersonnage().addGold(1);
	    this.board.getTile(this.x, this.y).getPersonnage().setPlayer(this.player);
	    this.board.getTile(this.x, this.y).getPersonnage().setTile(this.board.getTile(this.x, this.y));
	    this.board.occupeTile();
  	} catch (UnknownTileBoardException e) {
  		e.printStackTrace();
  	}

    for (Tile voisin : neighbours) {
      this.checkAllies (voisin);
      this.checkEnemy (voisin);
    }
    return true;
  }

  /**
   * Returns whether this personage was deployed successfully or not.
   *
   * @param voisin, the tile where to deploy this army.
   * @return True if this personage was deployed successfully, false otherwise.
   */
  private void checkEnemy (Tile voisin) {
	  if (!voisin.isEmpty() && !voisin.getPlayer().equals(this.player)) {
	    try {
	    	int taille = ((Army) this.board.getTile(this.x, this.y).getPersonnage()).getEnemyArmyStrength((Army) voisin.getPersonnage());
        int taileEnnemy = ((Army)voisin.getPersonnage()).getArmySize();
  			if ((taille < ((Army) this.board.getTile(this.x, this.y).getPersonnage()).getArmySize()) ) {
  			  if ((taileEnnemy / 2) < 1) {
            voisin.getPlayer().loseTerritory(voisin);
            voisin.setPlayer(this.player);
            this.player.addTerritory(voisin);
  			    voisin.getPersonnage().setPlayer(this.player);
  			    ((Army)this.board.getTile(this.x, this.y).getPersonnage()).addGold(2);
  			  } else {
            try {
              ((Army) voisin.getPersonnage()).takeAwayAWarrior(taileEnnemy - (taileEnnemy / 2));
            } catch (EmptyArmyException e) {
              voisin.getPersonnage().setTile(null);
              voisin.getPersonnage().setPlayer(null);
              voisin.emptyTile();
              this.board.EmptyTile();
              this.player.addGold(1);
            }
  			  }
  			}
  		} catch (UnknownTileBoardException e) {
  			e.printStackTrace();
  		}
	  }
  }

  /**
   * Returns whether this personage was deployed successfully or not.
   *
   * @param voisin, the tile where to deploy this army.
   * @return True if this personage was deployed successfully, false otherwise.
   * @exception UnknownTileBoardException the exception is raised if the tile is unknown
   */
  private void checkAllies (Tile voisin) {
	  if (!voisin.isEmpty() && voisin.getPlayer().equals(this.player)) {
      int taille = ((Army) voisin.getPersonnage()).getArmySize();
      try {
    		if (taille < ((Army) this.board.getTile(this.x, this.y).getPersonnage()).getArmySize()) {
    		  ((Army) voisin.getPersonnage()).joinArmy(1);
    		  this.board.getTile(x, y).getPersonnage().addGold(1);
    		}
  	  } catch (UnknownTileBoardException e) {
    		e.printStackTrace();
    	}
    }
  }

}
