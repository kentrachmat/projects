package game.games;

import game.player.*;
import game.tile.*;
import game.action.*;
import game.board.WarBoard;
import game.exception.*;
import game.personnage.Army;
import game.strategy.*;

/**
 * WarGame Class, a class by which we can model a war game on this game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class WarGame extends Game {

  /**
   * Creates a war game which's defined by it's number of rounds and it's players.
   *
   * @param rounds The number of rounds of this war game.
   * @param board The board of this war game.
   */
  public WarGame(int rounds, WarBoard board){
    super(rounds, board);
  }

  /**
   * Casts the player passed on parameter to a WarPlayer.
   *
   * @param player A player.
   * @return A player casted to WarPlayer.
   */
  private WarPlayer castPlayer (Player player) {
    return (WarPlayer)player;
  }

  /**
   * Plays one round on this war game for the player that have been passed on parameter.
   *
   * @param player The player who plays this round.
   */
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
    WarPlayer player = this.castPlayer(joueur);
    super.roundSteps(action, player, player.getFood());
    this.transformResources(player);
    this.feedArmies(player);
  }

  /**
   * Returns the army that have been created by the player passed on parameter.
   *
   * @param player A player.
   * @return the army that have been created by the player.
   */
  private Army inputArmy (WarPlayer player) {
    Army army = null;
    try {
      army = player.newArmy(((WarStrategy)player.getStrategy()).inputArmySize());
    } catch(NotEnoughWarriorsException e) {
      System.out.println("   Not enough warriors to ceate this army");
    } catch(EmptyArmyException e) {
      System.out.println("   Unfortunately you have 0 warriors, you can not create an army.");
    }
    return army;
  }

/**
 * Returns the action that have been choosed by the player.
 *
 * @param player The player who choosed the action.
 * @return action The player action to execute.
 */
@Override
public Action actionChoosed(Player joueur) {
  WarPlayer player = this.castPlayer(joueur);
  int index = ((WarStrategy)(player.getStrategy())).skipOrDeploy();
  Action action = new SkipAction(player);
  if (index == 0) {
    Army army = this.inputArmy(player);
    Tile tile = ((WarStrategy)player.getStrategy()).inputTileStrategy();
    if(army != null && tile != null) {
      if (army.getArmySize() > 3 && tile.equals(new MountainTile(0, 0, 1, 0))) {
        System.out.println("   Army of size " + army.getArmySize() + " can't be deployed on a mountain tile ! ");
        player.addWarrior(army.getArmySize());
      } else {
        action = new WarActionDeploy(player, this.board, tile.getPosX(), tile.getPosY(), army);
      }
    } else if (army != null && tile == null) {
      player.addWarrior(army.getArmySize());
    }
  }
  return action;
}

  /**
   * Feeds the player armies of this war game.
   *
   * @param player the player whose armies are going to be fed.
   */
  public void feedArmies (WarPlayer player) {
    super.feedPersonnages(player, 1);
    System.out.println("   III ) Armies feeded.");
  }

  /**
   * Plays one round on this war game for the player that have been passed on parameter.
   *
   */
  public void displayTiles() {
    System.out.println("\n   Tiles of this game : ");
    String phrase = "No army deployed on this tile !";
    for (Tile tile : this.board.getAllTiles()) {
      if (!tile.isEmpty()) {
        System.out.print("                        { Type tile : " + tile.getCharacter() + ", Position Y : " + tile.getPosX() +", Position X : " + tile.getPosY() + ", State : Occuped, Player : " + tile.getPlayer().getName() + ", Army : " + tile.getPersonnage().getName() + ", Army size : " +((Army) tile.getPersonnage()).getArmySize() + "}\n");
      } else {
        System.out.print("                        { Type tile : " + tile.getCharacter() +", Position Y : " + tile.getPosX() +", Position X : " + tile.getPosY()+ ", State : "+ phrase + " }\n");
      }
    }
  }

  /**
   * transforms the resources of the player that passed on parameter of this war game.
   *
   * @param player The player whose resources are going to be fed.
   */
  public void transformResources (WarPlayer player) {
    int index =  player.getStrategy().inputHowMnayResourcesToTransform();
    if ( player.getResources().size() == 0) {
      System.out.println("\n\n   No resource to convert !\n");
    }
    else if (index == 0) {
      player.transformNeededResources(player.getFood());
      System.out.println("\n\n   Needed resources transformed !\n");
    } else if (index == 1) {
      player.transformAllResources();
      System.out.println("\n\n   All resources transformed !\n");
    }
  }
	@Override
	public void addPlayer(String name, Strategy strategy) {
		if ( strategy != null) {
			this.players.add(new WarPlayer(name, strategy));
		} else  {
      this.players.add(new WarPlayer(name, new WarRandomStrategy("strategy", this.board)));
    }
	}
}
