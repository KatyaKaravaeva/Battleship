package battleship;

/**
 * Сlass that represents a single-deck ship.
 * It contains an array of coordinates and its length.
 */
public class Submarine1 extends Ship {

    /**
     * Method for outputting information that a ship has sunk.
     */
    @Override
    public void fillingOut() {

        System.out.println("You just have sunk a submarine!\n");
    }

    Submarine1() {
        length = 1;
        hits = new Point[1];
        for (int i = 0; i < length; i++) {
            hits[i] = new Point(0, 0);
            hits[i].flag = false;
        }
    }


}
