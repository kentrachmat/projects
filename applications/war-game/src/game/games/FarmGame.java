package game.games;

import game.action.*;
import game.board.*;
import game.player.*;
import game.strategy.*;
import game.tile.*;
import game.personnage.*;

/**
 * FarmGame class, a class by which we can model a farm game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class FarmGame extends Game {

	/**
   * Creates a farm game which's defined by it's number of rounds and it's players.
   *
   * @param board the corresponding board
   */
  public FarmGame(FarmBoard board){
    super(6, board);
  }

  /**
   * Casts the player passed on parameter to a FarmPlayer.
   *
   * @param player A player.
   * @return A player casted to FarmPlayer.
   */
  private FarmPlayer castPlayer (Player player) {
    return (FarmPlayer)player;
  }

  @Override
  public void playOneRound (Player player) {
    this.executeActions(this.actionChoosed(player), player);
  }

  /**
   * Executes the player action of this war game that passed on parameters.
   *
   * @param action The player action to execute.
   * @param player The player who plays the action.
   */
  private void executeActions (Action action, Player joueur) {
    FarmPlayer player = this.castPlayer(joueur);
    super.roundSteps(action, player, player.getGold());
    this.feedWorkers(player);
  }

  /**
   * Returns the worker that have been created by the player passed on parameter.
   *
   * @param player A player.
   * @return the worker that have been created by the player.
   */
  private Worker inputWorker (FarmPlayer player) {
    System.out.print("\n   " + player.getName() + " Choosed to deploy a worker !");
    return player.newWorker();
  }

  /**
   * Returns the action that have been choosed by the player.
   *
   * @param player The player who choosed the action.
   * @return action The player action to execute.
   */
   @Override
   public Action actionChoosed(Player player) {
    FarmPlayer joueur = this.castPlayer(player);
    int index = ((FarmStrategy)joueur.getStrategy()).skipOrDeployOrConvert();
    Action action = new SkipAction(joueur);
    if (index == 0) {
      Worker worker = this.inputWorker(joueur);
      Tile tile = ((FarmStrategy) joueur.getStrategy()).inputTileStrategy();
      if (tile != null && tile.isEmpty()) {
        action = new FarmActionDeploy(joueur, this.board, tile.getPosX(), tile.getPosY(), worker);
      }
    } else if (index == 1) {
      action = new SkipAction(joueur);
    } else if (index == 2) {
      action = new ConvertResourceAction(joueur);
    }
    return action;
  }

  /**
   * Feeds the player armies of this war game.
   *
   * @param player the player whose armies are going to be fed.
   */
  public void feedWorkers (FarmPlayer player) {
    super.feedPersonnages(player, 0);
    if (player.getTerritories().size() == 0) {
      System.out.println("   III) No worker to feed.");
    } else {
      System.out.println("   III) Workers have been fed.");
    }
  }

	@Override
	public void addPlayer(String name, Strategy strategy) {
		if ( strategy != null) {
			this.players.add(new FarmPlayer(name, strategy));
		} else {
      this.players.add(new FarmPlayer(name, new FarmRandomStrategy("strategy", this.board)));
    }
	}
}
