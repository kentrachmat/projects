package game.exception;

/**
 * UnknownTileBoardException Class, defiens an exception of unknown tile in a board game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class UnknownTileBoardException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnknownTileBoardException () {
	    super();
	  }

  public UnknownTileBoardException (String message) {
    super(message);
  }
}
