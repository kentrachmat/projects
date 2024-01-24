package game.player;


import game.personnage.*;
import game.strategy.Strategy;
import game.tile.*;
import java.util.ArrayList;
import game.exception.*;

/**
 * WarPlayer class, a class by which we can model a player in a war game (deploy armies on tiles of the board game, feed these armies, harvest the resources of these tiles, steal armies of ennemis ...).
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class WarPlayer extends Player {

  // The stock of food available to this player.
  private int food;

  // The number of warriors this war player has.
  private int warriors;

  /**
   * Creates a war player which has a stock of 35 warriors, a stock of 10 units of food and 0 coins of gold.
   *
   * @param n This war player name.
   * @param strategy the strategy name
   */
  public WarPlayer (String n, Strategy strategy) {
    super(n, 0, strategy);
    this.food = 10;
    this.warriors = 35;
  }

  /**
   * Returns a new army created by this player.
   *
   * @return the name of the army to create.
   */
  private String provideNameArmy() {
    String ArmyName = this.name + "Army" + Player.NUM;
    Player.NUM += 1;
    return ArmyName;
  }

  /**
   * Returns a name for the army created by this war player.
   *
   * @param size the size of the new army
   * @return the name of the army.
   * @exception NotEnoughWarriorsException the exception is raised when the warrior is not enough
   * @exception EmptyArmyException exception if the army is empty
   */
  public Army newArmy (int size) throws NotEnoughWarriorsException, EmptyArmyException {
    Army army = null;
    if (this.warriors > 0) {
      if (this.warriors - size >= 0) {
        army = new Army(this.provideNameArmy(), size);
        this.warriors = this.warriors - army.getArmySize();
      } else {
        throw new NotEnoughWarriorsException ("not enough warriors to create an army.");
      }
    } else {
      throw new EmptyArmyException ("This army is empty.");
    }
    return army;
  }

  /**
   * Returns the number of food.
   *
   * @return the number of food.
   */
  public int getFood () {
    return this.food;
  }

  /**
   * Add food to this player.
   *
   * @param value, the value
   */
  public void addFood (int value) {
    this.food += value;
  }

  /**
   * Add warriors to this player.
   *
   * @param value, the value
   */
  public void addWarrior (int value) {
    this.warriors += value;
  }

  /**
   * Decrease the number of food.
   *
   * @param i the number of food.
   */
  public void decreaseFood (int i) {
    this.food = this.food - i;
  }

  /**
   * Lose the army that have been stolen by the enemy war player from this player.
   *
   * @param tile the take where the army loses
   */
  public void loseArmy (Tile tile) {
    this.territoires.remove(tile);
  }

  /**
   * Add food to the war player
   *
   * @param n the food quantity
   */
  @Override
  public void addNeed(int n) {
    this.addFood(n);
  }

  /**
   * The Warrior Gold, Food, Points, and army unsued
   *
   */
  @Override
  public void display() {
    this.displayTerritoires();
    this.displayResources();
    System.out.println("      Warriors unused : " + this.warriors);
    System.out.println("      Gold : " + this.gold);
    System.out.println("      Food : " + this.food);
    System.out.println("      Points : " + this.points);
  }

  @Override
  public void displayTerritoires () {
    ArrayList<Tile> territoires = (ArrayList<Tile>) this.getTerritories();
    System.out.print("      Territoires :  ");
    if (this.getTerritories().size() == 0) {
      System.out.print(" No territory conquered ");
    } else {
      System.out.println();
      for (Tile tile : territoires) {
        System.out.println("                     {Type tile  : " + tile.getCharacter() + ", position X : " + tile.getPosX() + ", position Y : " + tile.getPosY() +  ", Army name : " + ((Army) tile.getPersonnage()).getName() + ", Army size : " + ((Army) tile.getPersonnage()).getArmySize() + ", Army gold : " + ((Army) tile.getPersonnage()).getGold() +" }");
      }
    }
    //System.out.print("\n");
  }

  public void transformNeededResources () {
    super.transformNeededResources(this.food);
    }

  /**
   * Counts the number of points of a player.
   *
   * @return The player number points.
   */
   @Override
  public int getPlayerPoints() {
    this.points = 0;
    ArrayList<Tile> territoires = (ArrayList<Tile>) this.getTerritories();
    this.points += this.gold;
    if (this.getTerritories().size() >= 10) {
      this.points += 5;
    } else {
      for (Tile tile : territoires) {
        if (tile.getPersonnage()!=null)
          this.points += tile.getPersonnage().getGold();
        if (tile.equals(new PlainTile(0, 0, 1, 5))) {
          this.points += 1;
        }
        else if (tile.equals(new ForestTile(0, 0, 1, 1))) {
          this.points += 2;
        }
        else if (tile.equals(new MountainTile(0, 0, 1, 0)) || tile.equals(new DesertTile(0, 0, 2, 0))) {
          this.points += 4;
        }
      }
    }
    return points;
  }

}
