package game;

import game.board.*;
import game.games.*;


/**
 * MainGame class, a class by which we can model a farm game or a war game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */

public class MainGuerreGame {

	/**
	 * Returns the game that have been chosen.
	 *
	 */
    public static void usage () {
      System.out.print("\n   Error :\n   - This game can only be played between two players at least.");
      System.out.print("\n   Usage :\n   - java -jar guerre.jar Player1 Player2 Player3 . . .\n");
    }

  /**
   * Plays a game.
   *
   * @param args The arguments entered to play a game.
   */
   public static void main(String[] args) {
     Board board = new WarBoard(10, 10);
     Game game = new WarGame(10, (WarBoard)board);
     if (args.length <= 1) {

     } else {
       for ( int index = 0; index < args.length; index ++) {
         game.addPlayer(args[index], null);
       }
      game.play();
    }
    }
}
