package game.action;

import static org.junit.Assert.*;

import org.junit.Test;

import game.action.*;
import game.board.*;
import game.exception.EmptyArmyException;
import game.exception.NotEnoughWarriorsException;
import game.player.*;
import game.strategy.WarRandomStrategy;
import game.tile.Tile;

import org.junit.Before;

public class SkipActionTest {

	// A player
	private Player player;

	// Aboard game
	private Board board;
	private Action action;

	 @Before
	 public void before(){
		 this.player = new WarPlayer("Kent", new WarRandomStrategy("strategy", board));
		 this.board = new WarBoard(10, 10);
		 this.action = new SkipAction(this.player);
	 }

	 @Test
	 public void executeTestWhenThePlayerHasNoYetDeployedAnArmy() {
		 assertTrue(this.player.getTerritories().size() == 0);
		 assertEquals(((WarPlayer) this.player).getFood(), 10);
		 assertEquals(((WarPlayer) this.player).getWarriors(), 35);
		 this.action.execute();
		 assertTrue(this.player.getTerritories().size() == 0);
		 assertEquals(((WarPlayer) this.player).getFood(), 10);
		 assertEquals(((WarPlayer) this.player).getWarriors(), 35);
	 }

	 @Test
	 public void executeTestWhenThePlayerHasDeployedAnArmyPreviously() throws NotEnoughWarriorsException, EmptyArmyException {
		 assertTrue(this.player.getTerritories().size() == 0);
		 assertEquals(((WarPlayer) this.player).getFood(), 10);
		 assertEquals(((WarPlayer) this.player).getWarriors(), 35);

		 Tile tile = this.board.getAllTiles().get(1);
		 WarActionDeploy actionTwo = new WarActionDeploy (this.player, board, tile.getPosX(), tile.getPosY(), ((WarPlayer) this.player).newArmy(2));

		 actionTwo.execute();

		 assertTrue(this.player.getTerritories().size() == 1);
		 assertTrue(((WarPlayer) this.player).getFood() >= 6); // because on some tiles the army need to feeded twice it's size.
		 assertEquals(((WarPlayer) this.player).getWarriors(), 33);

		 this.action.execute();

		 assertTrue(this.player.getTerritories().size() == 1);
		 assertTrue(((WarPlayer) this.player).getFood() >= 4);
		 assertEquals(((WarPlayer) this.player).getWarriors(), 33);
	 }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter(SkipActionTest.class);
    }

}
