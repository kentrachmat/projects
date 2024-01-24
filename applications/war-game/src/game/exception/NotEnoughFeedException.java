package game.exception;

/**
 * NotEnoughFeedException Class, defiens an exception of not enough feed.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class NotEnoughFeedException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughFeedException () {
	    super();
	  }

  public NotEnoughFeedException (String message) {
    super(message);
  }
}
