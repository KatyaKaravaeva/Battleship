package battleship;

/**
 * Class that represents a three-deck.
 * It contains an array of coordinates and its length.
 */
public class Cruiser3 extends Ship {
    /**
     * Method for outputting information that a ship has sunk.
     */
    @Override
    public void fillingOut() {
        System.out.println("You just have sunk a cruiser! \n");
    }

    Cruiser3() {
        length = 3;
        hits = new Point[3];
        for (int i = 0; i < length; i++) {
            hits[i] = new Point(0, 0);
            hits[i].flag = false;
        }
    }
}
