package competition;

/**
 * A class to design a Competitor who can participate in a Competition.
 * Competitor is a class where the player name and points are declared.
 *
 * @author Hichem KARFA, Benedictus Kent RACHMAT
 * @version 1.0
 */
public class Competitor {

    private int points;
    private String name;

    /**
     * Competitor constructor
     *
     * @param pName the player's name
     */
    public Competitor(String pName) {
        this.name = pName;
        this.points = 0;
    }

    /**
     * Adds a point to the player
     *
     * @param nbPoints number of points
     */
    public void addPoints(int nbPoints) {
        this.points += nbPoints;
    }

    /**
     * Reset the player points to 0
     */
    public void resetPoints() {
        this.setPoints(0);
    }

    /**
     * Set the player's points
     *
     * @param newPoints number of points
     */
    public void setPoints(int newPoints) {
        this.points = newPoints;
    }

    /**
     * Set the player's name
     *
     * @param newName player's name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Get the player's points
     *
     * @return the player's points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Get the player's name
     *
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }


}