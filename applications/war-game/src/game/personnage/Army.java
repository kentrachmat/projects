package game.personnage;

import game.exception.*;
import game.player.*;
import game.tile.*;

/**
 * Army class, a class by which we can model an army, deloy this army on a territory, get it's capacity
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */

public class Army extends Personnage {

  // The size of this army.
  private int size;

  /**
   * Creates an army which's defined by it's name and it's size.
   *
   * @param name The name of this army.
   * @param size The size of this army.
   */
	public Army (String name, int size) {
    super(name);
    if ( size > 5 || size < 1) {
      this.size = 3;
    } else {
      this.size = size;
    }
  }

  /**
   * Reduces the size of the this army by taking away n warriors.
   *
   * @param n, The number of warriors to take away from this army.
   * @exception EmptyArmyException throws an exception if the army after reducing it's warriors will be empty.
   */
  public void takeAwayAWarrior (int n) throws EmptyArmyException  {
    if ( this.size - n >= 1) {
      this.size -= n;
    }
    else {
      throw new EmptyArmyException ("This army is empty.");
    }
  }

  /**
   * Returns this army size.
   *
   * @return This army size.
   */
  public int getArmySize () {
    return this.size;
  }

  /**
   * Returns the number of units of feed that this army needs.
   *
   * @return The number of units of feed that this army needs.
   */
  public int getArmyNeeds () {
    return this.size * this.tile.getTileCost();
  }

  public int getNeeds() {
    return this.getArmyNeeds();
  }

  /**
   * Returns whether the army passed on parameter in an enemy army.
   *
   * @param army, an army.
   * @return True if army passed on parameter in an enemy army, false otherwise.
   */
  public boolean isEnemy (Army army) {
    return this.player != army.getPlayer();
  }

/**
   * Returns the strength of the army opponent against this army.
   *
   * @param army The opponent army.
   * @return This army strength.
   */
  public int getEnemyArmyStrength (Army army) { // method name changed to getOpponentArmyStrength 
    if (this.isEnemy(army) && army.getTile().equals(new MountainTile(0, 0, 1, 0))) {
		  if (army.getArmySize() + 2 > 5) {
		    return 5;
		  } else {
		    return army.getArmySize() + 2;
		  }
		}
		return army.getArmySize();
  }

  /**
   * Increases the size of the this army by adding n warriors.
   *
   * @param warriors The number of warriors to add to this army.
   */
  public void joinArmy(int warriors) {
    int i = this.tile.getValueResource();
    if (i == 0) { 
      if (this.size + warriors > 3) {
        this.size = 3;
      } else {
    	  this.size = this.size + warriors;
      }
    } else {
        if (this.size + warriors > 5) {
        this.size = 5;
      } else {
        this.size = this.size + warriors;
      }
    }
  }

  /**
  * feed the army by decreasing the player food stock
  *
  * @exception NotEnoughFeedException throws an exception if the player's food is insuffisant
  */
  @Override
  public void feedPersonnage () throws NotEnoughFeedException {
    if (((WarPlayer) this.player).getFood() - this.getArmyNeeds() >= 0) {
      ((WarPlayer) this.player).decreaseFood(this.getArmyNeeds());
    } else {
      throw new NotEnoughFeedException("not enough food to feed this army");
    }
  }
}
