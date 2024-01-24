package game.personnage;

import game.exception.*;

/**
 * Worker class, a class by which we can model a worker, deloy this worker on a territory, get it's capacity, By the way this class extends Personnage to get personnage caracteristcs.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class Worker extends Personnage{

	/**
	 * Creates a worker defined by it's name.
   *
	 * @param name the worker name
	 */
  public Worker(String name){
      super(name);
  }

  /**
   * Returns the number of gold that are necessary to pay this worker.
   *
   * @return the number of gold.
   */
  public int getWorkerNeeds () {
    return this.tile.getTileCost();
  }

  @Override
  public int getNeeds() {
    return this.getWorkerNeeds();
  }

  /**
  * feed the worker by decreasing the player food stock
  *
  * @exception NotEnoughFeedException throws an exception if the player's food is insuffisant
  */
	@Override
	public void feedPersonnage() throws NotEnoughFeedException {
		if ((this.player.getGold() - this.getWorkerNeeds ()) >= 0) {
			this.player.decreaseGold(this.getWorkerNeeds ());
			this.addGold(getWorkerNeeds());
		} else {
			throw new NotEnoughFeedException("not enough gold to pay this worker");
		}
	}

}
