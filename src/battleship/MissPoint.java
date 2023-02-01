package battleship;
/**
 * A class that represents a miss.
 * It contains an array of coordinates and its slip length equal to 1.
 */
public class MissPoint extends Ship {
    MissPoint() {
        length = 1;
        hits = new Point[1];
        for (int i = 0; i < length; i++) {
            hits[i] = new Point(0, 0);
            hits[i].flag = false;
        }
    }
}
