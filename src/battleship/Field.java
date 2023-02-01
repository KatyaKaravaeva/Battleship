package battleship;

import java.util.Scanner;

/**
 * Сlass that displays the field in which the user plays.
 */
public class Field {
    private static final Scanner scanner = new Scanner(System.in);
    int N, M;
    int[] numberOfShip;
    Ship[][] ships;

    /**
     * Method to start the game, it initializes variables and prints the created field.
     *
     * @param numbers array that contains the number of ships
     * @param n field height
     * @param m field width
     */
    void game(int[] numbers, int n, int m) {
        numberOfShip = numbers;
        fillingOut(n, m);
        for (int i = 4; i >= 0; --i) {
            randomShips(i + 1, numberOfShip[i]);
        }
        print();
    }

    /**
     * Method for printing a field.
     */
    public void print() {
        for (int i = 0; i <= M; ++i) {
            if (i == 0) {
                System.out.print("    ");
            } else if (i < 10) {
                System.out.print(" " + i + " ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.print("\n");
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (j == 0) {
                    if (i < 9) {
                        System.out.print(" " + (i + 1) + "  ");
                    } else {
                        System.out.print(" " + (i + 1) + " ");
                    }
                }
                if (ships[i][j] instanceof MissPoint && ships[i][j].hits[0].flag) {
                    System.out.print(" ^ ");
                } else if (ships[i][j] instanceof MissPoint && !ships[i][j].hits[0].flag) {
                    System.out.print(" - ");
                } else if (ships[i][j].Sinked()) {
                    System.out.print(" x ");
                } else if (!(ships[i][j] instanceof MissPoint)) {
                    int fl = 0;
                    for (int k = 0; k < ships[i][j].length; k++) {
                        if (ships[i][j].hits[k].x == i
                                && ships[i][j].hits[k].y == j
                                && ships[i][j].hits[k].flag) {
                            System.out.print(" @ ");
                            fl = 1;
                        }
                    }
                    if (fl == 0) {
                        System.out.print(" - ");
                    }
                }
            }
            System.out.print("\n");
        }
    }

    /**
     *  Method for initializing a field with empty cells.
     *
     * @param n field height
     * @param m field width
     */
    public void fillingOut(int n, int m) {
        N = n;
        M = m;
        ships = new Ship[n][m];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                ships[i][j] = new MissPoint();
            }
        }
    }

    /**
     * Random ship generation.
     * Each time a random coordinate and direction are generated (4 cases).
     * Then calculate if it is possible to put the ship,
     * if the cells around are not occupied and all checks are passed,
     * then it is possible. Consequently, put the ship on the field.
     *
     * @param rate Ship rank
     * @param shipCount Number of ships with the same length
     */
    void randomShips(int rate, int shipCount) {
        int setting_is_possible = 1;
        int x, y;
        int direction = 0;
        int currentX;
        int currentY;
        int countShip = 0;
        while (countShip < shipCount) {
            int count = 0;
            do {
                x = ((int) (Math.random() * (9 + 1))) % N;
                y = ((int) (Math.random() * (9 + 1))) % M;

                currentX = x;
                currentY = y;

                direction = ((int) (Math.random() * 4 + 1)) % 4;

                setting_is_possible = 1;
                for (int i = 0; i < rate; ++i) {
                    if (x < 0 || y < 0 || x > (N - 1) || y > (M - 1)) {
                        setting_is_possible = 0;
                        break;
                    }
                    if (x == 0 && y == 0) {
                        if (!(ships[x][y] instanceof MissPoint) ||
                                !(ships[x][y + 1] instanceof MissPoint) ||
                                !(ships[x + 1][y] instanceof MissPoint) ||
                                !(ships[x + 1][y + 1] instanceof MissPoint)) {
                            setting_is_possible = 0;
                            break;
                        }
                    } else if (x == 0 && y == (M - 1)) {
                        if (!(ships[x][y] instanceof MissPoint) ||
                                !(ships[x][y - 1] instanceof MissPoint) ||
                                !(ships[x + 1][y] instanceof MissPoint) ||
                                !(ships[x + 1][y - 1] instanceof MissPoint)) {
                            setting_is_possible = 0;
                            break;
                        }
                    } else if (x == 0) {
                        if (!(ships[x][y] instanceof MissPoint) ||
                                !(ships[x + 1][y] instanceof MissPoint) ||
                                !(ships[x][y + 1] instanceof MissPoint) ||
                                !(ships[x][y - 1] instanceof MissPoint) ||
                                !(ships[x + 1][y + 1] instanceof MissPoint) ||
                                !(ships[x + 1][y - 1] instanceof MissPoint)) {
                            setting_is_possible = 0;
                            break;
                        }
                    } else if (y == 0 && x == (N - 1)) {
                        if (!(ships[x][y] instanceof MissPoint) ||
                                !(ships[x - 1][y] instanceof MissPoint) ||
                                !(ships[x][y + 1] instanceof MissPoint) ||
                                !(ships[x - 1][y + 1] instanceof MissPoint)) {
                            setting_is_possible = 0;
                            break;
                        }
                    } else if (y == 0) {
                        if (!(ships[x][y] instanceof MissPoint) ||
                                !(ships[x][y + 1] instanceof MissPoint) ||
                                !(ships[x - 1][y + 1] instanceof MissPoint) ||
                                !(ships[x - 1][y] instanceof MissPoint) ||
                                !(ships[x + 1][y] instanceof MissPoint) ||
                                !(ships[x + 1][y + 1] instanceof MissPoint)) {
                            setting_is_possible = 0;
                            break;
                        }
                    } else if (y == (M - 1) && x == (N - 1)) {
                        if (!(ships[x][y] instanceof MissPoint) ||
                                !(ships[x - 1][y] instanceof MissPoint) ||
                                !(ships[x][y - 1] instanceof MissPoint) ||
                                !(ships[x - 1][y - 1] instanceof MissPoint)) {
                            setting_is_possible = 0;
                            break;
                        }
                    } else if (y == (M - 1)) {
                        if (!(ships[x][y] instanceof MissPoint) ||
                                !(ships[x][y - 1] instanceof MissPoint) ||
                                !(ships[x - 1][y] instanceof MissPoint) ||
                                !(ships[x + 1][y] instanceof MissPoint) ||
                                !(ships[x - 1][y - 1] instanceof MissPoint) ||
                                !(ships[x + 1][y - 1] instanceof MissPoint)) {
                            setting_is_possible = 0;
                            break;
                        }
                    } else if (x == (N - 1)) {
                        if (!(ships[x][y] instanceof MissPoint) ||
                                !(ships[x - 1][y] instanceof MissPoint) ||
                                !(ships[x][y + 1] instanceof MissPoint) ||
                                !(ships[x][y - 1] instanceof MissPoint) ||
                                !(ships[x - 1][y - 1] instanceof MissPoint) ||
                                !(ships[x - 1][y + 1] instanceof MissPoint)) {
                            setting_is_possible = 0;
                            break;
                        }
                    } else if (!(ships[x][y] instanceof MissPoint) ||
                            !(ships[x][y + 1] instanceof MissPoint) ||
                            !(ships[x][y - 1] instanceof MissPoint) ||
                            !(ships[x + 1][y] instanceof MissPoint) ||
                            !(ships[x + 1][y + 1] instanceof MissPoint) ||
                            !(ships[x + 1][y - 1] instanceof MissPoint) ||
                            !(ships[x - 1][y] instanceof MissPoint) ||
                            !(ships[x - 1][y + 1] instanceof MissPoint) ||
                            !(ships[x - 1][y - 1] instanceof MissPoint)) {
                        setting_is_possible = 0;
                        break;
                    }
                    switch (direction) {
                        case 0:
                            x++;
                            break;
                        case 1:
                            y++;
                            break;
                        case 2:
                            x--;
                            break;
                        case 3:
                            y--;
                            break;
                    }
                }
                ++count;
                if (count == 60) {
                    throw new NullPointerException("Exception: s is null!");
                }
            } while (setting_is_possible != 1);


            if (setting_is_possible == 1) {
                Ship ship = new Ship();
                if (rate == 1) {
                    ship = new Submarine1();
                } else if (rate == 2) {
                    ship = new Destroyer2();
                } else if (rate == 3) {
                    ship = new Cruiser3();
                } else if (rate == 4) {
                    ship = new Battleship4();
                } else if (rate == 5) {
                    ship = new Carrier5();
                }
                x = currentX;
                y = currentY;
                for (int i = 0; i < rate; ++i) {

                    if (rate == 1) {
                        ships[x][y] = ship;
                    } else if (rate == 2) {
                        ships[x][y] = ship;
                    } else if (rate == 3) {
                        ships[x][y] = ship;
                    } else if (rate == 4) {
                        ships[x][y] = ship;
                    } else if (rate == 5) {
                        ships[x][y] = ship;
                    }
                    Point p = new Point(x, y);
                    ships[x][y].hits[i] = p;

                    switch (direction) {
                        case 0:
                            x++;
                            break;
                        case 1:
                            y++;
                            break;
                        case 2:
                            x--;
                            break;
                        case 3:
                            y--;
                            break;

                    }
                }
            }
            ++countShip;
        }

    }
}