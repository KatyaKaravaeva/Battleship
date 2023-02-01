package battleship;

/**
 * Сlass that contains common functions for all ships.
 */
public class Ship {
    /** An array that stores coordinates with a flag that indicates whether they were shot or not */
    Point[] hits;

    /** The length of the ship, its rank. */
    int length;

    /**
     * Method for outputting information that a ship has sunk.
     */
    public void fillingOut() {

        System.out.println("You just have sunk a ship-type");
    }

    /**
     * Method for checking if a ship has sunk.
     *
     * @return true if the ship is sunk, otherwise false
     */
    boolean Sinked() {
        for (int i = 0; i < hits.length; ++i) {
            if (!hits[i].flag) {
                return false;
            }
        }
        return true;
    }
}
