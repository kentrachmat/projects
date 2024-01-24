package game.games;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import game.board.*;
import game.exception.*;
import game.player.*;
import game.strategy.*;
import game.tile.*;
import game.action.*;

/**
 * Game Class, a class by which we can model this game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class Game {

	// The number of rounds to play on this game.
	protected int rounds;

	// The number of rounds to play on this game.
	protected Board board;

	// The players of this game.
  	protected List<Player> players;

  /**
   * Creates a game which's defined by it's number of rounds and it's players.
	 *
   * @param turn The number of rounds of this game.
   * @param board the board of this game.
   */
  public Game(int turn, Board board){
    	this.rounds = turn;
	  	this.board = board;
    	this.players = new ArrayList<Player> ();
  }

	/**
	 * Returns the action that have been choosed by the player.
	 *
	 * @param player The player who chooses the action.
	 * @return action The player action to execute.
	 */
	public abstract Action actionChoosed (Player player);

	/**
	 * Plays one round on this game for the player that have been passed on parameter.
	 *
	 * @param player The player who plays this round.
	 */
	public abstract void playOneRound(Player player);

	/**
	 * Add one player to this game.
	 *
	 * @param name the name of this player
	 * @param strategy The strategy by which this player plays.
	 */
	  public abstract void addPlayer(String name, Strategy strategy);

  /**
   * Returns the numbers of rounds to play on this game.
	 *
   * @return the numbers of rounds to play on this game.
   */
  public int getNumberOfRounds(){
    return this.rounds;
  }

  /**
   * Plays a game between two or more of this game players.
   */
  public void play () {
	this.board.display();
    int index = 0;
		System.out.print("\n   *  *  *  *  *  *  *  *  GOOD LUCK  *  *  *  *  *  *  *  * \n\n\n");
    while (index < this.rounds && this.board.hasEmptyTile()) {
			System.out.print("   * * * * * * * * * * * * Round  " + (index + 1) + "  * * * * * * * * * * * *\n\n");
	    for (Player player : players) {
	      System.out.println("\n   ***************** " + player.getName() + " plays ! \n");
	      this.playOneRound(player);
	    }
	    System.out.println("\n\n   * * * * * * * * * * * * End of this round " + (index + 1) +": \n\n");
	    for (Player player : players) {
	      System.out.println("   " + player.getName() + " has : ");
	      player.display();
	      System.out.println("");
	    }
		  System.out.println("\n\n");
		  index += 1;
    }
		this.getWinner();
  }

  /**
	 * Feeds the player personages of this game.
	 *
	 * @param gold The gold to add the player in case that he loses a personage.
	 * @param player the player whose personages are going to be fed.
	 */
  protected void feedPersonnages (Player player, int gold) {
	  List<Tile> myList = new CopyOnWriteArrayList<Tile>();
	  for (Tile tile : player.getTerritories()) {
		  myList.add(tile);
	  }
	  Iterator<Tile> it = myList.iterator();
	  while (it.hasNext()) {
		  Tile tile = it.next();
		  try {
			  tile.getPersonnage().feedPersonnage();
		  } catch (NotEnoughFeedException e) {
				player.loseTerritory(tile);
			  tile.emptyTile();
			  this.board.EmptyTile();
			  player.addGold(gold);
		  }
	  }
  }

	/**
	 * Executes the player action of this war game that passed on parameters.
	 *
	 * @param action The player action to execute.
	 * @param player The player who plays the action.
	 * @param n the player food
	 */
	protected void roundSteps (Action action, Player player, int n) {
		action.execute();
		this.harvestResources(player);
		if (!(action instanceof SkipAction )) {
			System.out.println("\n   In stock : ");
			System.out.println("      Player food : " + n);
			player.displayTerritoires();
			player.displayResources();
		}
	}

	/**
	 * harvests the resources of player that passed on parameter of this war game.
	 *
	 * @param player The player whose resources are going to be fed.
	 */
	public void harvestResources (Player player) {
		ArrayList<Tile> tiles = (ArrayList<Tile>) player.getTerritories();
		if (!tiles.isEmpty()) {
			for (Tile tile : tiles) {
				player.addResource(tile.getResource());
			}
			System.out.println("   II ) Resources harvested !");
		} else {
			System.out.println("\n   II ) No Resources to harvest !");
		}
	}
  /**
	 * Displays the winners fo this game.
	 */
	public void getWinner() {
		System.out.println("   ---------------------------------------> End of the game !\n\n   Players points at the end of this game : \n");
		int points = 0;
		for (Player player : this.players) {
			System.out.println("   " + player.getName() +"\'s points : " + player.getPlayerPoints()+".");
			if (player.getPlayerPoints() > points) {
				points = player.getPlayerPoints();
			}
		}
		System.out.println("\n   The winners are : ");
		for (Player p : this.players) {
			if (p.getPlayerPoints() == points) {
				System.out.println("      - " + p.getName() + "               ");
			}
		}
		System.out.println("");
	}

}
