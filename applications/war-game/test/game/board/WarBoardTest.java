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

public class WarBoardTest {
    
	//A farm board.
    private Board warBoardOne;
    
    @Before
    public void before(){
        this.warBoardOne = new WarBoard(10,9);
    }

    @Test
    public void getWidthTest(){
        assertEquals(9,this.warBoardOne.getWidth());
    }

    @Test
    public void getHeightTest(){
        assertEquals(10,this.warBoardOne.getHeight());
   
    }
    
    @Test 
    public void hasEmptyTileTest () {
    	assertTrue(this.warBoardOne.hasEmptyTile());
    }
    
    @Test 
    public void occupeTileTest () {
    	ArrayList<Tile> tiles = this.warBoardOne.getAllTiles();
    	assertEquals(this.warBoardOne.getAllTiles().size(), this.warBoardOne.getEmptyTiles());
    	tiles.get(0).setPersonnage(new Worker("worker"));
    	this.warBoardOne.occupeTile();
    	assertEquals(this.warBoardOne.getAllTiles().size() - 1, this.warBoardOne.getEmptyTiles());
    }
    
    @Test 
    public void emptyTileTest () {
    	ArrayList<Tile> tiles = this.warBoardOne.getAllTiles();
    	assertEquals(this.warBoardOne.getAllTiles().size(), this.warBoardOne.getEmptyTiles());
    	tiles.get(0).setPersonnage(new Worker("worker"));
    	this.warBoardOne.occupeTile();
    	assertEquals(this.warBoardOne.getAllTiles().size() - 1, this.warBoardOne.getEmptyTiles());
    	tiles.get(0).setPersonnage(null);
    	this.warBoardOne.EmptyTile();
    }
    
    @Test 
    public void getEmptyTileTest () {
    	ArrayList<Tile> tiles = this.warBoardOne.getAllTiles();
    	int index = this.warBoardOne.getEmptyTiles();
    	tiles.get(0).setPersonnage(new Worker("worker"));
    	this.warBoardOne.occupeTile();
    	assertEquals(index - 1, this.warBoardOne.getEmptyTiles());
    }
    
    
    @Test 
    public void hasEmptyTestWhenAllTilesAreOccupied () {
    	for(Tile tile : this.warBoardOne.getAllTiles()) {
    		tile.setPersonnage(new Worker("worker"));
    		this.warBoardOne.occupeTile();
    	}
    	assertFalse(this.warBoardOne.hasEmptyTile());
    }
    
    @Test(expected=UnknownTileBoardException.class) 
    public void getTileTestWhenCoordinatesAreOK () throws UnknownTileBoardException {
        try{ 
            assertNotNull(this.warBoardOne.getTile(1,1));
        
        } 
        catch(UnknownTileBoardException e) {
        
        }
    }

    @Test(expected=UnknownTileBoardException.class) 
    public void getTileTestWhenCoordinatesAreUpperToHeight () throws UnknownTileBoardException {
        try{ 
            assertNull(this.warBoardOne.getTile(300,1));
        } 
        catch(UnknownTileBoardException e) {
       
        }
    }
    
    @Test(expected=UnknownTileBoardException.class) 
    public void getTileTestWhenCoordinatesAreUpperToWidth () throws UnknownTileBoardException {
        try{ 
            assertNull(this.warBoardOne.getTile(1,13));
        } 
        catch(UnknownTileBoardException e) {
        }
    }
    
    @Test
    public void newTileTestIsOK ()  {
    	Tile tile = this.warBoardOne.newTile (1, 3);
        if (tile.equals(new DesertTile(5, 8, 2, 0))) {
        	assertEquals(0, tile.getResource().getValue());
          } else if (tile.equals(new PlainTile(7, 5, 1, 5))) {
        	  assertEquals(5, tile.getResource().getValue());
          } else if (tile.equals(new ForestTile(7, 5,  1, 1))) {
        	  assertEquals(1, tile.getResource().getValue());
          } else {
        	  assertEquals(0, tile.getResource().getValue());
          }
    }

    public static junit.framework.Test suite() {
        return new junit.framework.JUnit4TestAdapter( WarBoardTest.class);
    }

}
