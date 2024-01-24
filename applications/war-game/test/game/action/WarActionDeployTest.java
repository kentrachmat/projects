package game.action;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import game.action.*;
import game.board.*;
import game.exception.EmptyArmyException;
import game.exception.NotEnoughWarriorsException;
import game.player.*;
import game.strategy.*;
import game.tile.Tile;

import org.junit.Before;

public class WarActionDeployTest {

	// A player
	private Player player;

	// Aboard game
	private Board board;
	private Action actionPlayer;
	private Action actionPlayer2;


	 @Before
	 public void before() {
		 this.player = new WarPlayer("Aziz", new WarHumanStrategy("strategy", board));
		 this.board = new WarBoard(10, 10);
		 Tile tile = this.board.getAllTiles().get(1);
		 ArrayList<Tile> tiles = this.board.getTiles(tile.getPosX(), tile.getPosY());
		 Tile tile1 = tiles.get(0);
		 try {
			this.actionPlayer = new WarActionDeploy (this.player, board, tile.getPosX(), tile.getPosY(), ((WarPlayer) this.player).newArmy(1));
			 this.actionPlayer2 = new WarActionDeploy (this.player, board, tile1.getPosX(), tile1.getPosY(), ((WarPlayer) this.player).newArmy(3));
		 } catch (NotEnoughWarriorsException | EmptyArmyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	 }

	 @Test
	 public void executeTestWhenThePlayerDeployedAnArmyAndTheNoArmyInNeighbour() {
		 assertTrue(this.player.getTerritories().size() == 0);
	     this.actionPlayer.execute();
		 assertTrue(this.player.getTerritories().size() == 1);
		 assertEquals(this.player.getTerritories().get(0).getPersonnage().getGold(), 1);
	 }

	 @Test
	 public void executeTestWhenThePlayerDeployedAnArmyAndTheArmyInNeighbourIsAllie() {
		 assertTrue(this.player.getTerritories().size() == 0);

	     this.actionPlayer.execute();
		 assertTrue(this.player.getTerritories().size() == 1);

		 this.actionPlayer2.execute();
		 assertTrue(this.player.getTerritories().size() == 2);

		 // The army allie deployed ear an army allie earn 1 gold.
		 assertEquals(this.player.getTerritories().get(0).getPersonnage().getGold(), 1);
		 assertEquals(this.player.getTerritories().get(1).getPersonnage().getGold(), 2);
	 }


    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(WarActionDeployTest.class);
    }
}
