package game.exception;

/**
 * NotEnoughWarriorsException Class, defiens an exception of not enough enough warriors in order to create an army in a war game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class NotEnoughWarriorsException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughWarriorsException () {
	    super();
	  }

  public NotEnoughWarriorsException (String message) {
    super(message);
  }
}
