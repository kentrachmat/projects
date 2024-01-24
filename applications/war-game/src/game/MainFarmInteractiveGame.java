package game;

import game.board.*;
import game.games.*;
import game.strategy.*;


/**
 * MainGame class, a class by which we can model a farm game or a war game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */

public class MainFarmInteractiveGame {

	/**
	 * Returns the game that have been chosen.
	 *
	 */
    public static void usage () {
      System.out.print("\n   Error :    This game can only be played between two players at least.");
      System.out.print("\n   Usage :    java -jar guerre.jar PlayerName PlayerStrategy PlayerName PlayerStrategy . . .");
      System.out.print("\n                 PlayerName : The name of the player.");
      System.out.print("\n                 PlayerStrategy : The strategy by which this player will play.");
      System.out.print("\n                                - 0 To play with a random strategy.");
      System.out.print("\n                                - 1 To play with a human strategy.\n\n");
    }

    /**
     * Plays a game.
     *
     * @param args The arguments entered to play a game.
     */
    public static void main(String[] args) {
      Board board =  new FarmBoard(10, 10);
      Game game = new FarmGame((FarmBoard)board);
      if (args.length < 4 || (args.length % 2 != 0)) {
        MainFarmInteractiveGame.usage();
      } else {
        int index = 0;
        while (index < args.length ) {
          if (Integer.parseInt(args[index + 1]) == 0) {
            game.addPlayer(args[index], new FarmRandomStrategy("strategy", (FarmBoard)board));
          } else if (Integer.parseInt(args[index + 1]) == 1) {
            game.addPlayer(args[index], new FarmHumanStrategy("strategy", (FarmBoard)board));
          } else {
            MainFarmInteractiveGame.usage();
            break;
          }
          index += 2;
        }
        game.play();
      }
  }
}
