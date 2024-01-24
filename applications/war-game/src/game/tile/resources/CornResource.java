package game.tile.resources;


/**
 * CornResource Class, a class by which we can model the corn resource of this game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public class CornResource extends Resource {

  /**
   * Creates a  corn ressource which's defined by it's name and it's value.
   * @param value The value of this sand resource
   */
  public CornResource(int value){
    super("Corn", value);
  }

  /**
   * Returns whether this resource is equal to the object passed on parameter or not.
   * @param o An object, any object to compare with this PlainTile object.
   * @return True if the object passed on parameter is equal to this resource object or not an.
   */
  public boolean equals(Object o) {
     if (o instanceof CornResource) {
       CornResource other = (CornResource) o;
       if (this.name.equals(other.getName()) && (this.value == other.getValue())) {
         return true;
       }
    }
    return false;
  }
}
