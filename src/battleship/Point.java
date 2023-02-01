package battleship;

/**
 * Class that contains two point coordinates
 * and a flag that is responsible for hitting it or not.
 */
public class Point {
    int x, y;
    boolean flag;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
