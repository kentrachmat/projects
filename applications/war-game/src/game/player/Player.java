package game.player;

import game.strategy.Strategy;
import game.tile.Tile;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import game.tile.resources.*;

/**
 * Player class, a class by which we can model a player in a game (deploy personnages on tiles of the board game, feed these personnages, harvest the resources of these tiles ...).
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class Player {

  // The name of this player.
	protected String name;

	// The number of the personage that this player Creates.
	protected static int NUM = 0;

	// The strategy by which this player plays.
	protected Strategy strategy;

	// The points of this player.
  	protected int points;

	// The number of gold of this player.
	protected int gold;

	// The territories occupied by this player in a game.
  	protected List<Tile> territoires;

	// The resources harvested by this player.
	protected List<Resource> ressources;



  /**
	 * Creates a player which's defiend by it's name.
	 *
	 * @param gold The gold that this player has.
	 * @param n, The name of this player.
	 * @param strategy The strategy by which this player plays.
	 */
	public Player (String n, int gold, Strategy strategy) {
	  this.name = n;
	  this.points = 0;
	  this.gold = gold;
	  this.territoires = new ArrayList<Tile>();
	  this.ressources = new ArrayList<Resource>();
	  this.strategy = strategy;
  }

	/**
   * Returns the number of gold that this player has.
	 *
   * @return the number of gold that this player has.
   */
	public String getName () {
		return this.name;
	}

	/**
	 * Returns the strategy by which this player plays.
	 *
	 * @return the strategy by which this player plays.
	 */
	public Strategy getStrategy() {
		return this.strategy;
	}

  /**
   * Returns the number of gold that this player has.
	 *
   * @return the number of gold that this player has.
   */
	public int getGold () {
		return this.gold;
	}

  /**
	 * Add gold to this player.
	 *
	 * @param gold, the number of gold.
	 */
	public void addGold (int gold) {
		this.gold += gold;
	}

	/**
	 * Decreases this player gold.
	 *
	 * @param g, the number of gold.
	 */
	public void decreaseGold (int g) {
		if ((this.gold - g) >= 0) {
			this.gold = this.gold - g;
		}
	}

	/**
   * Displays a representation of the resources that this player has.
   */
	public abstract void displayTerritoires();

	/**
	 * Returns this player points.
	 *
   * @return The player number points.
	 */
	public abstract int getPlayerPoints ();

	/**
	 * Displays a representation of the position of this player.
	 */
	public abstract void display();

	/**
	 * Adds the to this player needs (gold or food) by transforming his resources.
	 *
	 * @param n The player needs.
	 */
	public abstract void addNeed(int n);

	/**
	 * Add a resource to this player resources.
	 *
	 * @param resource, The resource to add.
	 */
	public void addResource (Resource resource) {
		this.ressources.add(resource);
	}

	/**
	 * Add a tile that have been occupied by this player.
	 *
	 * @param tile, The tile that have been occupied by this player.
	 */
	public void addTerritory (Tile tile) {
		this.territoires.add(tile);
	}

	/**
	 * Returns the tiles that this player occupies.
	 *
	 * @return The tiles that this player occupies.
	 */
	public List<Tile> getTerritories () {
		return this.territoires;
	}

	/**
	 * Returns the resources that this player has.
	 *
	 * @return A map with the amount of each resource that this player has.
	 */
	public  ArrayList<Resource> getResources () {
		return (ArrayList<Resource>) this.ressources;
	}

	/**
	 * Returns the resources that this player has.
	 *
	 * @return A map with the amount of each resource that this player has.
	 */
	public  HashMap<String,Integer> getResourcesForDisplay () {
		HashMap<String,Integer> resources = new HashMap<String,Integer>();
		for(Resource resource : this.ressources) {
			if (resources.containsKey(resource.getName())) {
				resources.put(resource.getName(), resources.get(resource.getName()) + 1);
			} else {
				resources.put(resource.getName(), 1);
			}
		}
		return resources;
	}

	/**
	 * Returns the resources that this player has.
	 *
	 * @return A map with the amount of each resource that this player has.
	 */
	public void resetResource (String name, int n) {
		List<Resource> myList = new CopyOnWriteArrayList<Resource>();
		for (Resource resource : this.ressources) {
			myList.add(resource);
		}

		int index = 0;
		for(Resource resource : myList) {
			if (resource.getName().equals(name) && index < n ) {
				this.transformOneResource(resource);
				myList.remove(resource);
				index += 1;
			}
		}
	}

	/**
	 * Loses one territory for this player.
	 *
	 * @param tile, The tile take off one from this player.
	 */
	public void loseTerritory (Tile tile) {
		List<Tile> myList = new CopyOnWriteArrayList<Tile>();
		for (Tile til : this.territoires) {
			myList.add(til);
		}
		this.territoires.clear();
		for (Tile t : myList) {
			if (!(t.getPosX() == tile.getPosX()) && !(t.getPosY() == tile.getPosY())) {
				this.territoires.add(t);
			}
		}
	}

	/**
	 * Converts this player resources needs.
	 *
	 * @param player The player whose resources are going to be fed.
	 */
	public void convertResources (Player player) {
		int index = this.strategy.inputHowMnayResourcesToTransform();
	    if ( player.getResources().size() == 0) {
	      System.out.print("   No resource to convert !\n\n");
	    }
	    else if (index == 0) {
	      player.transformNeededResources(((WarPlayer) player).getFood());
	      System.out.print("   Needed resources transformed !\n\n");
	    } else if (index == 1) {
	      player.transformAllResources();
	      System.out.print("   All resources transformed !\n\n");
	    }
	  }

	/**
	 * transforms one resource harvested during a game.
	 *
	 * @param resource The resource to transform to gold.
	 */
	public void transformOneResource (Resource resource) {
		this.addNeed(resource.getValue());
	}

	/**
	 * Returns the necessary needs of this player personages.
	 *
	 * @return This necessary needs of this player personages.
	 */
	public int getPersonnagesNeeds() {
		int needs = 0;
		for (Tile tile : this.territoires) {
			needs += tile.getPersonnage().getNeeds();
		}
		return needs;
	}

	/**
	 * transforms the needed player resources in order to feed his personages.
	 *
	 * @param stock The resources that this player already has.
	 */
	public void transformNeededResources (int stock) {
		List<Resource> myList = new CopyOnWriteArrayList<Resource>();
		for (Resource resource : this.ressources) {
			myList.add(resource);
		}

		int index = this.getPersonnagesNeeds();
		Iterator<Resource> it = myList.iterator();
		while (it.hasNext() && stock < index) {
			Resource resource = it.next();
			this.transformOneResource(resource);
			this.ressources.remove(resource);
		}
	}

	/**
	 * transforms the needed player resources in order to feed his personages.
	 *
	 * @param stock The resources that this player already has.
	 */
	public void transformChosenResources (String name, int stock) {
		List<Resource> myList = new CopyOnWriteArrayList<Resource>();
		for (Resource resource : this.ressources) {
			myList.add(resource);
		}
    int index = 0;
		Iterator<Resource> it = myList.iterator();
		while (it.hasNext()) {
			Resource r = it.next();
			if (index < stock && r.getName().equals(name)) {
				this.transformOneResource(r);
				this.ressources.remove(r);
				index += 1;
			}
		}
		System.out.println("         - "+index +" of "+name+" ressources have been converted.");
	}


	/**
	 * transforms all the player resources.
	 *
	 */
	public void transformAllResources () {
		List<Resource> myList = new CopyOnWriteArrayList<Resource>();
		for (Resource resource : this.ressources) {
			myList.add(resource);
		}

		Iterator<Resource> it = myList.iterator();
		while (it.hasNext()) {
			Resource resource = it.next();
			this.transformOneResource(resource);
			this.ressources.remove(resource);
		}
	}

	/**
	 * Displays a representation of this player resources.
	 */
	public void displayResources () {
		System.out.println("      Resources :  ");
		HashMap<String,Integer> res = this.getResourcesForDisplay();
		for(Entry<String, Integer> paire : res.entrySet()) {
		  String resource = paire.getKey();
		  Integer value = paire.getValue();
		  System.out.println("                   {" + resource +" : "+ value +"}                  ");
		}
		System.out.println();
	}

}
