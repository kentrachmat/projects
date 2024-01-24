package game;

import game.board.*;
import game.games.*;


/**
 * MainGame class, a class by which we can model a farm game or a war game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */

public class MainAgricoleGame {

	/**
	 * Returns the game that have been chosen.
	 *
	 * @return The game that have been chosen, O for a war game and 1 for a farm game.
	 */
    public static Game gameChoosed () {
      Board board = new FarmBoard(10, 9);
      Game game = new FarmGame((FarmBoard)board);
      return game;
    }

  /**
   * Plays a game.
   *
   * @param args The arguments entered to play a game.
   */
   public static void main(String[] args) {
     Game game = gameChoosed();
     if (args.length <= 1) {
       System.out.print("\n   Error :\n   - This game can only be played between two players at least.");
       System.out.print("\n   Usage :\n   - java -jar guerre.jar Player1 Player2 Player3 . . .\n\n");
     } else {
       for (int index = 0; index < args.length; index ++) {
         game.addPlayer(args[index], null);
       }
       game.play();
     }
   }
}
