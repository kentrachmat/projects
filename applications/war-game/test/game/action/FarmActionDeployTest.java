package game.action;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import game.action.*;
import game.board.*;
import game.player.*;
import game.strategy.*;
import game.tile.Tile;

import org.junit.Before;

public class FarmActionDeployTest {

	// A player
	private Player player;

	// Aboard game
	private Board board;
	private Action actionPlayer;
	private Action actionPlayer2;


	 @Before
	 public void before() {
		 this.player = new FarmPlayer("Aziz", new WarHumanStrategy("strategy", board));
		 this.board = new FarmBoard(10, 10);
		 Tile tile = this.board.getAllTiles().get(1);
		 ArrayList<Tile> tiles = this.board.getTiles(tile.getPosX(), tile.getPosY());
		 Tile tile1 = tiles.get(0);
		 this.actionPlayer = new FarmActionDeploy (this.player, board, tile.getPosX(), tile.getPosY(), ((FarmPlayer) this.player).newWorker());
		 this.actionPlayer2 = new FarmActionDeploy (this.player, board, tile1.getPosX(), tile1.getPosY(), ((FarmPlayer) this.player).newWorker());

	 }

	 @Test
	 public void executeTestWhenThePlayerDeployesAndTileIsEempty() {
		 assertTrue(this.player.getTerritories().size() == 0);
	     this.actionPlayer.execute();
	     assertTrue(this.player.getTerritories().size() == 1);
	     this.actionPlayer2.execute();
	     assertTrue(this.player.getTerritories().size() == 2);
	 }

	 @Test
	 public void executeTestWhenThePlayerDeployesAndTileIsOccupied() {
		 assertTrue(this.player.getTerritories().size() == 0);
	     this.actionPlayer.execute();
	     this.actionPlayer.execute();
	     assertTrue(this.player.getTerritories().size() == 1);
	     this.actionPlayer2.execute();
	     this.actionPlayer2.execute();
	     assertTrue(this.player.getTerritories().size() == 2);
	 }




    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(FarmActionDeployTest.class);
    }
}
