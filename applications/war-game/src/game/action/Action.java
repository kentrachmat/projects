package game.action;


/**
 * Action interface, an interface that defines a method execute by which we can execute an action that have been chosen by a player during a game (deploy a personage, or do nothing ...)
 * 
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public interface Action {


  /**
   * An action that a player can execute on a game.
   */
  public void execute();
}
