package game.tile.resources;


/**
 * WoodResource Class, a class by which we can model the wood resource of this game.
 */
public class WoodResource extends Resource {

  /**
   * Creates a  wood ressource which's defined by it's name and it's value.
   * @param value The value of this wood resource
   */
  public WoodResource(int value){
    super("Wood", value);
  }

  /**
   * Returns whether this resource is equal to the object passed on parameter or not.
   * @param o An object, any object to compare with this PlainTile object.
   * @return True if the object passed on parameter is equal to this resource object or not an.
   */
  public boolean equals(Object o) {
     if (o instanceof WoodResource) {
       WoodResource other = (WoodResource) o;
       if (this.name.equals(other.getName()) && (this.value == other.getValue())) {
         return true;
       }
    }
    return false;
  }
  
}
