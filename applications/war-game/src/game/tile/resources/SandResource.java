package game.tile.resources;


/**
 * SandResource Class, a class by which we can model the sand resource of this game.
 */
public class SandResource extends Resource {

  /**
   * Creates a  sand ressource which's defined by it's name and it's value.
   * @param value The value of this sand resource
   */
  public SandResource(int value){
    super("Sand", value);
  }

  /**
   * Returns whether this resource is equal to the object passed on parameter or not.
   * @param o An object, any object to compare with this PlainTile object.
   * @return True if the object passed on parameter is equal to this resource object or not an.
   */
  public boolean equals(Object o) {
     if (o instanceof SandResource) {
       SandResource other = (SandResource) o;
       if (this.name.equals(other.getName()) && (this.value == other.getValue())) {
         return true;
       }
    }
    return false;
  }
}
