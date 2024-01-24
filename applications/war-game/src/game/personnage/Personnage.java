package game.personnage;

import game.tile.*;
import game.player.*;
import game.exception.*;

/**
 * Personnage class, a class by which we can model a personnage, deloy this personnage on a territory, get whether it's needs are satisfied or not...
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class Personnage {

	// The gold that this personnage has.
	protected int gold;

	// This personnage namme.
	protected String name;

	// This personnage namme.
	protected Player player;

	// The tile that this army occupes.
	protected Tile tile;

  /**
	 * Creates a personnage which defined by it's name.
	 *
	 * @param name the name of the person
	 */
	public Personnage (String name) {
		this.name = name;
    	this.gold = 0;
		this.player = null;
		this.tile = null;
	}

	/**
	 * Returns the player who plays this personnage.
	 *
	 * @return the player who plays this personnage.
	 */
  public Player getPlayer() {
    return this.player;
  }

	/**
	 * Sets the player who plays this personnage.
	 *
	 * @param player set the player
	 */
  public void setPlayer(Player player) {
    this.player = player;
  }

	/**
	 * Sets the tile which this personnage will occupe.
	 *
	 * @param tile the tile which this personnage will occupe.
	 */
	public void setTile (Tile tile) {
		 this.tile = tile;
	}

	/**
	 * Returns the tile that this personnage occupes.
	 *
	 * @return return the tile concerning
	 */
	public Tile getTile () {
		 return this.tile;
	}

	/**
	 * Returns the number of gold that this personnage has.
	 *
	 * @return the number of gold that this personnage has.
	 */
	public int getGold(){
		return this.gold;
	}

	/**
	 * Add gold to this personnage.
	 *
	 * @param gold, the number of gold.
	 */
  public void addGold(int gold){
		this.gold += gold;
  }

	/**
	 * Returns the name of this personnage.
	 *
	 * @return the name of this personnage.
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Returns the needs that are necessary to feed this personage.
	 *
	 * @return The needs that are necessary.
	 */
	public abstract int getNeeds ();

	/**
	 * Feeds this personage.
	 *
	 * @exception NotEnoughFeedException if this player can't feed this personage.
	 */
	public abstract void feedPersonnage () throws NotEnoughFeedException;

}
