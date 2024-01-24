package game.strategy;
import java.io.IOException;

import game.board.*;
import game.io.Input;
import game.tile.*;


/**
 * Strategy class, a class that defines a strategy by which a player can play during a game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class Strategy {

  // The name of this strategy.
  protected String nameStrategy;

  // The board where the we play the game.
  protected Board board;

  /**
   * Creates a strategy which's defined by it's name and board.
   *
   * @param value The value to return if this input isn't a number.
   * @param board The board where a player plays this strategy.
   */
  public Strategy(String name, Board board) {
    this.nameStrategy = name;
    this.board =  board;
  }

  /**
   * Returns how many resources to transform.
   *
   * @return how many resources to transform.
   */
  public abstract int inputHowMnayResourcesToTransform ();

  /**
   * Returns the tile where to deploy a personnage chosen.
   *
   * @return the tile chosen.
   */
  public abstract Tile inputTileStrategy();

  /**
   * Reads the number chosed at the input.
   *
   * @param value The value to return if this input isn't a number.
   * @return The value entered on the input.
   */
  public static int readPlayerChoice(int value) {
  	int index;
  	try {
  		index = Input.readInt();
  	} catch (IOException e) {
  		  index = value;
  	}
  	return index;
	}

}
