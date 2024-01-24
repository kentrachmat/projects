package game.board;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import game.exception.UnknownTileBoardException;
import game.personnage.Worker;
import game.tile.DesertTile;
import game.tile.ForestTile;
import game.tile.PlainTile;
import game.tile.Tile;

import org.junit.Before;

public class FarmBoardTest {
    
	//A farm board.
    private Board farmBoardOne;
    
    @Before
    public void before(){
        this.farmBoardOne = new FarmBoard(10,9);
    }

    @Test
    public void getWidthTest(){
        assertEquals(9,this.farmBoardOne.getWidth());
    }

    @Test
    public void getHeightTest(){
        assertEquals(10,this.farmBoardOne.getHeight());
   
    }
    
    @Test 
    public void hasEmptyTileTest () {
    	assertTrue(this.farmBoardOne.hasEmptyTile());
    }
    
    @Test 
    public void occupeTileTest () {
    	ArrayList<Tile> tiles = this.farmBoardOne.getAllTiles();
    	assertEquals(this.farmBoardOne.getAllTiles().size(), this.farmBoardOne.getEmptyTiles());
    	tiles.get(0).setPersonnage(new Worker("worker"));
    	this.farmBoardOne.occupeTile();
    	assertEquals(this.farmBoardOne.getAllTiles().size() - 1, this.farmBoardOne.getEmptyTiles());
    }
    
    @Test 
    public void emptyTileTest () {
    	ArrayList<Tile> tiles = this.farmBoardOne.getAllTiles();
    	assertEquals(this.farmBoardOne.getAllTiles().size(), this.farmBoardOne.getEmptyTiles());
    	tiles.get(0).setPersonnage(new Worker("worker"));
    	this.farmBoardOne.occupeTile();
    	assertEquals(this.farmBoardOne.getAllTiles().size() - 1, this.farmBoardOne.getEmptyTiles());
    	tiles.get(0).setPersonnage(null);
    	this.farmBoardOne.EmptyTile();
    }
    
    @Test 
    public void getEmptyTileTest () {
    	ArrayList<Tile> tiles = this.farmBoardOne.getAllTiles();
    	int index = this.farmBoardOne.getEmptyTiles();
    	tiles.get(0).setPersonnage(new Worker("worker"));
    	this.farmBoardOne.occupeTile();
    	assertEquals(index - 1, this.farmBoardOne.getEmptyTiles());
    }
    
    
    @Test 
    public void hasEmptyTestWhenAllTilesAreOccupied () {
    	for(Tile tile : this.farmBoardOne.getAllTiles()) {
    		tile.setPersonnage(new Worker("worker"));
    		this.farmBoardOne.occupeTile();
    	}
    	assertFalse(this.farmBoardOne.hasEmptyTile());
    }
    
    @Test(expected=UnknownTileBoardException.class) 
    public void getTileTestWhenCoordinatesAreOK () throws UnknownTileBoardException {
        try{ 
            assertNotNull(this.farmBoardOne.getTile(1,1));
        
        } 
        catch(UnknownTileBoardException e) {
        
        }
    }

    @Test(expected=UnknownTileBoardException.class) 
    public void getTileTestWhenCoordinatesAreUpperToHeight () throws UnknownTileBoardException {
        try{ 
            assertNull(this.farmBoardOne.getTile(300,1));
        } 
        catch(UnknownTileBoardException e) {
       
        }
    }
    
    @Test(expected=UnknownTileBoardException.class) 
    public void getTileTestWhenCoordinatesAreUpperToWidth () throws UnknownTileBoardException {
        try{ 
            assertNull(this.farmBoardOne.getTile(1,13));
        } 
        catch(UnknownTileBoardException e) {
        }
    }
    
    @Test
    public void newTileTestIsOK () {
    	Tile tile = this.farmBoardOne.newTile (1, 3);
        if (tile.equals(new DesertTile(5, 8, 3, 5))) {
        	assertEquals(5, tile.getResource().getValue());
          } else if (tile.equals(new PlainTile(7, 5, 1, 2))) {
        	  assertEquals(2, tile.getResource().getValue());
          } else if (tile.equals(new ForestTile(7, 5, 1, 2))) {
        	  assertEquals(2, tile.getResource().getValue());
          } else {
        	  assertEquals(5, tile.getResource().getValue());
          }
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter( FarmBoardTest.class);
    }

}