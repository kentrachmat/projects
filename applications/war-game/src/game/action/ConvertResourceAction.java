package game.action;

import game.player.*;
import game.strategy.FarmStrategy;


/**
 * ConvertResourceAction class, a class by which we can model the convert resource action that the players can play during a farm game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class ConvertResourceAction implements Action {

  // The player who plays this skip action.
  private Player player;


  /**
   * Creates a convert resource action that a player can play during a game.
   *
   * @param player, The player that plays this action.
   */
  public ConvertResourceAction (Player player) {
    this.player = player;
  }

  /**
   * Executes this convert resource action.
   */
  public void execute () {
    this.convertResources (this.player);
    this.display();
  }

  /**
   * Converts the player resources to gold.
   *
   * @param player The player whose resources are going to be fed.
   */
  public void convertResources (Player player) {
    int index = player.getStrategy().inputHowMnayResourcesToTransform();
    if (index == 0) {
    	player.transformAllResources();
    	System.out.print("      Needed resources transformed !");
    } else if (index == 1) {
    	player.transformAllResources();
   	  System.out.print("      All resources transformed !");
    } else if (index == 2) {
    	System.out.println("      How many resources you want to transform from (only the quantity that you have will be converted) ");
      player.transformChosenResources("Wood", this.displayRespresentation("Wood"));
      player.transformChosenResources("Rock", this.displayRespresentation("Rock"));
      player.transformChosenResources("Sand", this.displayRespresentation("Sand"));
      player.transformChosenResources("Corn", this.displayRespresentation("Corn"));

      System.out.print("      The chosen ressources have been converted !");
    }
    else {
      System.out.print("      No resource transformed !");
    }
  }

  /**
   * Displays a representation of the resource convertion.
   */
  private int displayRespresentation(String name) {
    System.out.print("         " + name + " : ");
    int index = ((FarmStrategy)player.getStrategy()).inputTheQuantity();
    player.resetResource(name, index);
    return index;
  }


  /**
   * Displays a representation of this skip action.
   */
  private void display () {
    System.out.println("\n   " + this.player.getName() + " converted his resources to gold ! ");
  }
}
