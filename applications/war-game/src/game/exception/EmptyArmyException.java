package game.exception;

/**
 * EmptyArmyException Class.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class EmptyArmyException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyArmyException () {
	    super();
	  }

  public EmptyArmyException (String message) {
    super(message);
  }
}
