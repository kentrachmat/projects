package game;

import game.board.*;
import game.games.*;
import game.strategy.*;


/**
 * MainGame class, a class by which we can model a farm game or a war game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */

public class MainWarInteractiveGame {

	/**
	 * Returns the game that have been chosen.
	 *
	 */
    public static void usage () {
      System.out.print("\n   Error :\n   - This game can only be played between two players at least.");
      System.out.print("\n   Usage :\n   - java -jar guerre.jar PlayerName PlayerStrategy PlayerName PlayerStrategy . . .");
      System.out.print("\n                 PlayerName : The name of the player. PlayerStrategy PlayerName PlayerStrategy . . .");
      System.out.print("\n                 PlayerStrategy : The strategy by which this player will play.");
      System.out.print("\n                                - 0 To play with a random strategy.");
      System.out.print("\n                                - 1 To play with a human strategy.\n");
    }

    /**
     * Plays a game.
     *
     * @param args The arguments entered to play a game.
     */
    public static void main(String[] args) {
      Board board = new WarBoard(10, 10);
      Game game = new WarGame(10, (WarBoard)board);
      if (args.length < 4 || (args.length % 2 != 0)) {
        MainWarInteractiveGame.usage();
      } else {
        for ( int index = 0; index < args.length; index += 2) {
          if (Integer.parseInt(args[index + 1]) == 0) {
            game.addPlayer(args[index], new WarRandomStrategy("strategy", (WarBoard)board));
          } else if (Integer.parseInt(args[index + 1]) == 1) {
            game.addPlayer(args[index], new WarHumanStrategy("strategy", (WarBoard)board));
          } else {
            MainFarmInteractiveGame.usage();
            break;
          }
        }
        game.play();
      }
  }
}
