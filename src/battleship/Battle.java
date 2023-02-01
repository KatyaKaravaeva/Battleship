package battleship;

import java.util.Scanner;

/**
 * Сlass in which all the logic of the game takes place.
 */
public class Battle {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int constantNumber = 5;
        int n = 0, m = 0;
        int[] numberOfShip = new int[constantNumber];
        if (args.length == 7) {
            try {
                n = Integer.parseInt(args[0]);
                m = Integer.parseInt(args[1]);

                for (int i = 0; i < constantNumber; i++) {
                    numberOfShip[i] = Integer.parseInt(args[i + 2]);
                }
            } catch (Exception e) {
                System.err.println("Wrong line format!");
                n = enterN();
                m = enterM();

                for (int i = 0; i < constantNumber; i++) {
                    printInfo(i);
                    numberOfShip[i] = numberIntEntry();
                }
            }
        } else {
            n = enterN();
            m = enterM();
            for (int i = 0; i < constantNumber; i++) {
                printInfo(i);
                numberOfShip[i] = numberIntEntry();
            }
        }
        gameLogic(n, m, numberOfShip, constantNumber);

    }

    /**
     * The method creates a field of the right size and starts the game,
     * if it fails to generate ships, the user is asked to re-enter the data.
     *
     * @param n              field height
     * @param m              field width
     * @param numberOfShip   array number of ships
     * @param constantNumber number of ship types
     */
    static void gameLogic(int n, int m, int[] numberOfShip, int constantNumber) {
        int count = 1;
        Field sea = new Field();
        while (count != 0) {
            try {
                sea = new Field();
                sea.game(numberOfShip, n, m);
                count = 0;
            } catch (NullPointerException e) {
                ++count;
                if (count > 60) {
                    System.out.print("The ships were unable to position themselves on the field\n");
                    System.out.println("Do you want to continue the game or get out? Enter \"1\" to continue, \"0\" to exit.");
                    int flag = numberIntEntry();
                    while (flag < 0 || flag > 1) {
                        System.out.println(" Enter \"1\" to continue, \"0\" to exit.");
                        flag = numberIntEntry();
                    }
                    if (flag == 1) {
                        System.out.println("Please re-enter N M");
                        n = enterN();
                        m = enterM();
                        for (int i = 0; i < constantNumber; i++) {
                            printInfo(i);
                            numberOfShip[i] = numberIntEntry();
                        }
                        count = 1;
                    } else {
                        return;
                    }
                }
            }
        }
        int finalCount = 0;
        for (int i = 0; i < numberOfShip.length; i++) {
            finalCount += numberOfShip[i];
        }
        action(finalCount, n, m, sea);
    }

    /**
     * Method to work with the user, it handles the correct input of coordinates,
     * and then the game is made.
     * Depending on where the user hit, an action message is printed.
     *
     * @param finalCount the number of beaten ships
     * @param n          field height
     * @param m          field width
     * @param sea        object Field
     */
    public static void action(int finalCount, int n, int m, Field sea) {
        int totalShots = 0;
        int currentCont = 0;
        while (currentCont != finalCount) {
            System.out.print("Enter the coordnates you want to hit vertically \n");
            int x = numberIntEntry() - 1;
            while (x > (n - 1) || x == -1) {
                System.out.println("Your coordinate is incorrect, it does not match the height of the field!");
                x = numberIntEntry() - 1;
            }
            System.out.print("Enter the coordnates you want to hit horizontally \n");
            int y = numberIntEntry() - 1;
            while (y > (m - 1) || y == -1) {
                System.out.println("Your coordinate is incorrect, it does not match the width of the field!");
                y = numberIntEntry() - 1;
            }
            int caseType = shooting(sea, x, y);
            if (caseType == 0) {
                System.out.print("Hit! \n");
            } else if (caseType == 1) {
                sea.ships[x][y].fillingOut();
                currentCont++;
            } else if (caseType == 2) {
                System.out.print("Miss :(\n");
            } else {
                System.out.print("You have already shot :(\n");
            }
            sea.print();
            ++totalShots;
        }
        System.out.println("Congratulations, you won!\n" +
                "The total number of shots was:" + totalShots);
    }

    /**
     * Method for entering height correctly.
     * The height cannot be less than 1 and greater than 99.
     *
     * @return height
     */
    public static int enterN() {
        int n;
        System.out.print("Enter the height, it must be greater than 1 and less than 99\n ");
        n = numberIntEntry();
        while (n <= 1 || n >= 100) {
            System.out.println("Enter the height, it must be greater than 1 and less than 99\n");
            n = numberIntEntry();
        }
        return n;
    }

    /**
     * Method for entering width correctly.
     *
     * @return width
     */
    public static int enterM() {
        int m;
        System.out.print("Enter the width, it must be greater than 1 and less than 99\n");
        m = numberIntEntry();
        while (m <= 1 || m >= 100) {
            System.out.println("Enter the width, it must be greater than 1 and less than 99");
            m = numberIntEntry();
        }
        return m;
    }

    /**
     * Method to simulate shooting.
     * Check if the ship is sunk, if so, return 1.
     * If the ship was hit, then return 0 and put a flag that it was hit,
     * otherwise return 2 - the player missed.
     *
     * @param sea field class object
     * @param i   height coordinate
     * @param j   width coordinate
     * @return returns which case occurred
     */
    static int shooting(Field sea, int i, int j) {
        if (!(sea.ships[i][j] instanceof MissPoint)) {
            for (int h = 0; h < sea.ships[i][j].length; ++h) {
                if (sea.ships[i][j].hits[h].x == i && sea.ships[i][j].hits[h].y == j) {
                    if (sea.ships[i][j].Sinked()) {
                        return -1;
                    }
                    sea.ships[i][j].hits[h].flag = true;
                }
            }
            if (sea.ships[i][j].Sinked()) {
                return 1;
            }
            return 0;
        }
        sea.ships[i][j].hits[0].flag = true;
        return 2;
    }

    /**
     * Print information about ships.
     *
     * @param i ship type
     */
    public static void printInfo(Integer i) {
        if (i == 0) {
            System.out.print("Number of Submarine (1 cell)\n");
        } else if (i == 1) {
            System.out.print("Number of Destroyer (2 cell)\n");
        } else if (i == 2) {
            System.out.print("Number of Cruiser (3 cells)\n");
        } else if (i == 3) {
            System.out.print("Number of Battleship (4 cells)\n");
        } else {
            System.out.print("Number of Carrier  (5 cells)\n");
        }
    }

    /**
     * Converting a string to an integer.
     *
     * @param number string
     * @return true if it is possible to convert the string to a number, otherwise false
     */
    public static boolean tryParse(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    /**
     * Method for entering a number.
     * We also check that the number is non-negative.
     *
     * @return digit
     */
    public static int numberIntEntry() {
        System.out.print("Enter a number: ");
        String input = scanner.nextLine();
        while (!tryParse(input) || Integer.parseInt(input) < 0) {
            System.out.print("You entered the number incorrectly :(  Try again... \n");
            System.out.print("Enter a number: ");
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }
}
