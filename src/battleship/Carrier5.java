package battleship;

/**
 * Сlass that represents a five-deck.
 * It contains an array of coordinates and its length.
 */
public class Carrier5 extends Ship {
    /**
     * Method for outputting information that a ship has sunk.
     */
    @Override
    public void fillingOut() {
        System.out.println("You just have sunk a carrier! \n");
    }

    Carrier5() {
        length = 5;
        hits = new Point[5];
        for (int i = 0; i < length; i++) {
            hits[i] = new Point(0, 0);
            hits[i].flag = false;
        }
    }
}
