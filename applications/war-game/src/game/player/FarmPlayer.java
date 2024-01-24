package game.player;

import java.util.*;

import game.personnage.Worker;
import game.strategy.Strategy;
import game.tile.*;

/**
 * FarmPlayer class, a class by which we can model a player in a  farm game (deploy workers on tiles of the board game, feed the workers by given them gold, harvest the resources of these tiles ...).
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class FarmPlayer extends Player {

  /**
   * Creates a farm player which.
   * @param n, The gold value
   * @param strategy the strategy name
   */
  public FarmPlayer (String n, Strategy strategy) {
    super(n, 15, strategy);
  }

  /**
   * Returns a name for a worker to create by this war player.
   *
   * @return the name of the worker to create.
   */
  private String provideNameWroker() {
    String WorkerName = this.name + "Worker" + Player.NUM;
    Player.NUM += 1;
    return WorkerName;
  }

  /**
   * Returns a new worker created by this farm player.
   *
   * @return A worker created by this farm player.
   */
  public Worker newWorker () {
    return new Worker(this.provideNameWroker());
  }

  @Override
  public void addNeed(int n) {
    this.addGold(n);
  }

  /**
   * Counts the number of points of this player.
   *
   * @return The player number points.
   */
  @Override
  public int getPlayerPoints() {
    this.points = 0;
    ArrayList<Tile> territoires = (ArrayList<Tile>) this.getTerritories();
    this.points += this.gold;
    for (Tile tile : territoires) {
      this.points += tile.getPersonnage().getGold();
    }
    return points;
  }

  /**
  * display the resource and territories
  */
  @Override
  public void display() {
    this.displayTerritoires();
    this.displayResources();
    System.out.println("      Gold : " + this.gold);
    System.out.println("      Points : " + this.points);
  }

  /**
  * display the territories.
  */
  @Override
  public void displayTerritoires () {
    ArrayList<Tile> territoires = (ArrayList<Tile>) this.getTerritories();
    System.out.print("      Territoires :  ");
    if (this.getTerritories().size() == 0) {
      System.out.print(" No territory conquered ");
    } else {
      for (Tile tile : territoires) {
        System.out.print("{Type tile  : " + tile.getCharacter() + ", position X : " + tile.getPosX() + ", position Y : " + tile.getPosY() +  ", Worker name : " + tile.getPersonnage().getName() + ", Worker gold : " + tile.getPersonnage().getGold() +" }\n                     ");
      }
    }
    System.out.print("\n");
  }

}
