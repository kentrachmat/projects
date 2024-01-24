package game.tile.resources;


/**
 * Resource Class, a class by which we can model the resources of this game.
 *
 * @author Aziz BOURAHAMA, Benedictus Kent RACHMAT, Dorian ABRAHAM, Corentin DUVIVIER.
 */
public abstract class Resource {

  // The name of this resource.
  protected String name;

  // The value of this resource.
  protected int value;


  /**
   * Creates a resource which's defined by it's name.
   * @param s, The name of this resource.
   * @param value the value concerning
   */
  public Resource (String s, int value) {
    this.name = s;
    this.value = value;
  }

  /**
   * Returns the name of this resource.
   * @return The name of this resource.
   */
  public String getName () {
    return this.name;
  }

  /**
   * Returns the value of this resource.
   * @return The value of this resource.
   */
  public int getValue () {
    return this.value;
  }

  /**
   * Returns whether this resource is equal to the object passed on parameter or not.
   * @param o An object, any object to compare with this PlainTile object.
   * @return True if the object passed on parameter is equal to this resource object or not an.
   */
  public boolean equals(Object o) {
    if (o instanceof Resource) {
      Resource other = (Resource) o;
      if (this.name.equals(other.getName()) && (this.value == other.getValue())) {
        return true;
      }
    }
    return false;
  }

}
