package battleship;

/**
 * Сlass that represents a four-deck.
 * It contains an array of coordinates and its length.
 */
public class Battleship4 extends Ship {

    Battleship4() {
        length = 4;
        hits = new Point[4];
        for (int i = 0; i < length; i++) {
            hits[i] = new Point(0, 0);
            hits[i].flag = false;
        }
    }

    /**
     * Method for outputting information that a ship has sunk.
     */
    @Override
    public void fillingOut() {
        System.out.println("You just have sunk a battleship! \n");
    }
}
