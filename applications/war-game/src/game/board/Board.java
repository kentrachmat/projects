package game.board;

import game.exception.*;
import game.tile.*;

import java.util.*;

/**
 * Board Class, a class by which we can model a board game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class Board {

  // Width of this board.
  protected int boardWidth;

  // Height of this board.
  protected int boardHeight;

  // Table of the tiles which represent the board.
  protected Tile board [][];

  // The number of tiles that haven't been occupied yet.
  protected int emptytiles;

  // Generate random numbers.
  protected static Random alea = new Random();


  /**
   * Creates the board game which defined by it's width, height.
   *
   * @param height The height of this board to create.
   * @param width The width of this board to create.
   */
  public Board(int width, int height){
	  this.boardWidth = height;
	  this.boardHeight = width;
	  this.board = new Tile[this.boardHeight][this.boardWidth];
      this.emptytiles = this.randomNotOceanTile();
	  this.placeTiles(width, height, this.emptytiles);
	  this.placeOceanTiles();
  }

  /**
   * Returns the width of this board.
   *
   * @return The width of this board.
   */
  public int getWidth () {
	  return this.boardWidth;
  }

  /**
   * Returns the height of this board.
   *
   * @return The height of this board.
   */
  public int getHeight () {
	  return this.boardHeight;
  }

  /**
   * Returns whether this board has empty tile, tile that haven't been occupied by a personage.
   *
   * @return True if this board has empty tile that haven't been occupied by a personage, false otherwise.
   */
  public boolean hasEmptyTile () {
    return this.emptytiles > 0;
  }

  /**
   * Returns the number of the empty tiles on this board.
   *
   * @return the number of the empty tiles on this board.
   */
  public int getEmptyTiles () {
    return this.emptytiles;
  }

  /**
   * Increases the number of the empty tiles by one tile (tiles that haven't been occupied by a personage) on this board.
   */
  public void EmptyTile () {
	if (this.emptytiles < this.getAllTiles().size())
    this.emptytiles += 1;
  }

  /**
   * Decreases the number of the empty tiles (tiles that not have been occupied by a personage) on this board.
   */
  public void occupeTile () {
    this.emptytiles -= 1;
  }

  /**
   * Place the ocean tiles on this board.
   */
  private void placeOceanTiles() {
	  for (int i = 0; i <this.boardHeight ; i++) {
		  for (int j = 0; j < this.boardWidth; j++) {
			  if (this.board[i][j] == null) {
				  this.board[i][j] = new OceanTile(i, j);
			  }
      }
	  }
  }

  /**
   * Returns whether this board cell at coordinates (x,y) has been already initialized.
   *
   * @param x The cell abscissa.
   * @param y The cell ordinate.
   * @return True if this board cell at coordinates (x,y) has been already initialized, false otherwise.
   */
  private boolean isCellEmpty (int x, int y) {
	  if (( y < this.boardHeight && y >= 0 ) && ( x < this.boardWidth && x >= 0 )) {
		  return this.board[y][x] == null;
	  }
    return false;
  }

  /**
   * Returns this board tile at coordinates (x,y) that passed on parameter.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @return This board tile at coordinates (x,y).
   * @exception UnknownTileBoardException if coordinates (x,y) are not valid for this board.
   */
  public Tile getTile(int x, int y) throws UnknownTileBoardException {
	  if (( y < this.boardHeight && y >= 0 ) && ( x < this.boardWidth && x >= 0 )) {
		  return this.board[y][x];
	  } else {
		  throw new UnknownTileBoardException ("Invalid tile for the coordinates (" + x +" , " + y +")");
	  }
  }


  /**
   * Returns this board tile neighbors at coordinates (x,y) that passed on parameter.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @return This board tile neighbors at coordinates (x,y).
   */
  public ArrayList<Tile> getTiles(int x, int y) {
	  ArrayList<Tile> tiles = new ArrayList<Tile>();
	  for (int i = x - 1; i < x + 2; i++) {
		  for (int j = y - 1; j < y + 2; j++) {
			  try {
				  if (!this.getTile(i, j).equals(new OceanTile(0, 0)) && ((i != x) || (j != y))) {
					  tiles.add(this.getTile(i, j));
				  }
        } catch (UnknownTileBoardException e) {
          continue;
        }
      }
	  }
    return tiles;
  }

  /**
   * Returns this board tiles which are not ocean tile.
   *
   * @return This board tiles which are not ocean tile.
   */
  public ArrayList<Tile> getAllTiles() {
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    for (int i = 0 ; i < this.boardHeight; i++) {
      for (int j = 0; j < this.boardWidth; j++) {
        try {
          if (!this.getTile(j, i).equals(new OceanTile(0, 0))) {
            tiles.add(this.getTile(j, i));
          }
        } catch (UnknownTileBoardException e) {
          continue;
        }
      }
    }
    return tiles;
  }


  /**
   * Returns a new tile which's defined by it's coordinates (x,y) that passed on parameter.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @return A tile which's defined by it's coordinates (x,y).
   */
  public abstract Tile newTile(int x, int y);

  /**
   * Returns the number of not ocean tile to place on this board.
   *
   * @return The number of not ocean tile to place on this board.
   */
  private int randomNotOceanTile () {
    return 2 + alea.nextInt(((this.boardWidth * this.boardHeight ) / 3) - 2);
  }

  /**
   * Displays a representation of this board.
   */
  public void display() {
    System.out.print("\n\n   ");
    for (int j = 0; j < this.boardWidth; j++) {
    	System.out.print("     " + j);
    }
    System.out.print("\n\n");
    for (int i = 0; i < this.boardHeight; i++) {
    	System.out.print("   " + i + "  " );
      for (int j = 0; j < this.boardWidth; j++) {
        try {
          System.out.print("  " +this.getTile(j, i).getCharacter()+"   ");
          } catch (UnknownTileBoardException e) {
			      continue;
          }
        }
      System.out.print("\n\n\n");
    }
    System.out.print("\n");
  }

  /**
   * Returns the coordinates x, y of one empty neighbour cell randomly chosen.
   *
   * @param list An ArrayList of the adjacents neighbours cells coordinates.
   * @return One empty cell (coordinates x and y), otherwise an empty ArrayList.
   */
  private ArrayList<Integer> getOneAdjacentCell(ArrayList<Integer> list) {
    ArrayList<Integer> cell = new ArrayList<Integer>();
    if (list.size() > 2) {
      int index = alea.nextInt(list.size()/2) * 2;
      cell.add(list.get(index));
      cell.add(list.get(index + 1));
    } else if (list.size() <= 2) {
      return list;
    }
    return cell;
  }

  /**
   * Returns the empty adjacents neighbours cells.
   *
   * @param x The cell abscissa.
   * @param y The cell ordinate.
   * @return An ArrayList of the empty adjacents neighbours cells
   */
  private ArrayList<Integer> checkAdjacentsCells(int x, int y) {
    ArrayList<Integer> cells = new ArrayList<Integer>();
    this.checkUp(x, y, cells);
    this.checkDown(x, y, cells);
    this.checkLeft(x, y, cells);
    this.checkRight(x, y, cells);
    return cells;
  }

  /**
   * Places the last tile on this board.
   *
   * @return true The tile is placed.
   */
  private boolean placeLastTile() {
    for (int i = 0 ; i < this.boardHeight; i++) {
      for (int j = 0; j < this.boardWidth; j++) {
        ArrayList<Integer> cell = this.getOneAdjacentCell(checkAdjacentsCells(i, j));
        if (!this.isCellEmpty(j, i) && cell.size() != 0) {
          this.board[cell.get(1)][cell.get(0)] = this.newTile(cell.get(0), cell.get(1));
          return true;

        }
      }
    }
    return true;
  }

  /**
   * Places two tiles on this board, the two tiles are randomly chosen.
   *
   * @param x The main tile abscissa.
   * @param y The main tile ordinate.
   * @param posx The neighbor tile abscissa.
   * @param posy The neighbor tile ordinate.
   */
  private boolean placeTwoTiles(int x, int y, int posx, int posy) {
    this.board[y][x] = this.newTile(x, y);
    this.board[posy][posx] = this.newTile(posx, posy);
    return true;
   }

  /**
   * Places one tile on this board, the tile is randomly chosen.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   */
  private boolean placeOneTile(int x, int y) {
    this.board[y][x] = this.newTile(x, y);
    return true;
  }

  /**
   * Places different tiles except the ocean tile on this board.
   *
   * @param height This board height.
   * @param widht This board width.
   * @param tuiles A map of Tile and the number of each tile to place on this board.
   */
  private void placeTiles (int width, int height, int tuiles) {
    int i = 0;
    while ( i < tuiles) {
      boolean deployed = false;
      while (!deployed) {
        int x = alea.nextInt(this.boardWidth);
        int y = alea.nextInt(this.boardHeight);
        ArrayList<Integer> cell = this.getOneAdjacentCell(checkAdjacentsCells(x, y));
        if (tuiles - i == 1) {
          this.placeLastTile();
          i += 1;
          deployed = true;
        } else if (this.isCellEmpty(x,y) && cell.size() == 0 ) {
          deployed = this.placeOneTile(x, y);
          i += 1;
    		} else if (cell.size() != 0 && this.isCellEmpty(x,y)) {
          deployed = this.placeTwoTiles(x, y, cell.get(0), cell.get(1));
          i += 2;
    		}
      }
    }
  }

  /**assertEquals(this.farmBoardOne.getAllTiles().size(), this.farmBoardOne.getEmptyTiles());
   * Checks if the up neighbor cell is empty.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @param list An ArrayList where to add the empty tile.
   */
  private void checkUp(int x, int y, ArrayList<Integer> list) {
    if (this.isCellEmpty(x, y - 1)) {
      list.add(x);
      list.add(y - 1);
    }
  }

  /**
   * Checks if the down neighbour cell is empty.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @param list An ArrayList where to add the empty tile.
   */
  private void checkDown(int x, int y, ArrayList<Integer> list) {
    if (this.isCellEmpty(x, y - 1)) {
      list.add(x);
      list.add(y - 1);
    }
  }

  /**
   * Checks if the right neighbor cell is empty.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @param list An ArrayList where to add the empty tile.
   */
  private void checkRight(int x, int y, ArrayList<Integer> list) {
    if (this.isCellEmpty(x + 1, y)) {
      list.add(x + 1);
      list.add(y);
    }
  }

  /**
   * Checks if the left neighbor cell is empty.
   *
   * @param x The tile abscissa.
   * @param y The tile ordinate.
   * @param list An ArrayList where to add the empty tile.
   */
  private void checkLeft(int x, int y, ArrayList<Integer> list) {
    if (this.isCellEmpty(x - 1, y)) {
      list.add(x - 1);
      list.add(y);
    }
  }

}
