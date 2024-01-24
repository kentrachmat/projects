package game.tile.resources;


/**
 * RockResource Class, a class by which we can model the rock resource of this game.
 */
public class RockResource extends Resource {

  /**
   * Creates a  rock ressource which's defined by it's name and it's value.
   * @param value The value of this rock resource
   */
  public RockResource(int value){
    super("Rock", value);
  }

  /**
   * Returns whether this resource is equal to the object passed on parameter or not.
   * @param o An object, any object to compare with this PlainTile object.
   * @return True if the object passed on parameter is equal to this resource object or not an.
   */
  public boolean equals(Object o) {
     if (o instanceof RockResource) {
       RockResource other = (RockResource) o;
       if (this.name.equals(other.getName()) && (this.value == other.getValue())) {
         return true;
       }
    }
    return false;
  }
}
