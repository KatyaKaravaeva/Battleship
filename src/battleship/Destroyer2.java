package battleship;

/**
 * Class that represents a two-deck ship.
 * It contains an array of coordinates and its length.
 */
public class Destroyer2 extends Ship {
    /**
     * Method for outputting information that a ship has sunk.
     */
    @Override
    public void fillingOut() {
        System.out.println("You just have sunk a destroyer! \n");
    }

    Destroyer2() {
        length = 2;
        hits = new Point[2];
        for (int i = 0; i < length; i++) {
            hits[i] = new Point(0, 0);
            hits[i].flag = false;
        }
    }
}
