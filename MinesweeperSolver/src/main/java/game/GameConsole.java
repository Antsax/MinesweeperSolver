package game;

import java.util.Scanner;

/**
 *
 * @author antsax
 */
// The GameConsole functions as the primary input device where the user (or machine) may interact with the game.
public class GameConsole {

    private int x;
    private int y;
    private static Scanner reader;
    private int width;
    private int height;
    private boolean f;

    // The console requires width w and height h parameters to determine, if we can interact with the board
    // when given the coordinate values.
    public GameConsole(int w, int h) {
        this.width = w;
        this.height = h;
        x = 100000;
        y = 100000;
        this.f = false;
        this.reader = new Scanner(System.in);

    }

// The start() method will question the user on which square it wants to interact with
    public void start() {
        System.out.println("Choose square coordinates as x,y or flag as fx,y");
        String answer = reader.nextLine();
        System.out.println("");

        // if the answer contains the letter "f", then flag the location
        if (answer.contains("f")) {
            f = true;
            int index = answer.indexOf(',');
            String x = answer.substring(1, index);
            String y = answer.substring(index + 1, answer.length());

            try {
                int testX = Integer.parseInt(x);
                if (testX >= width) {
                    System.out.println("X out of bounds");
                } else {
                    this.x = Integer.parseInt(x);
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("x not a number or poor input, try again.");
            }

            try {
                int testY = Integer.parseInt(y);
                if (testY >= height) {
                    System.out.println("Y out of bounds");
                } else {
                    this.y = Integer.parseInt(y);
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("y not a number or poor input, try again.");
            }

            // if the answer is not empty, then reveal the location.
        } else if (!answer.isEmpty()) {
            int index = answer.indexOf(',');
            String x = answer.substring(0, index);
            String y = answer.substring(index + 1, answer.length());

            try {
                int testX = Integer.parseInt(x);
                if (testX >= width) {
                    System.out.println("X out of bounds");
                } else {
                    this.x = Integer.parseInt(x);
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("x not a number or poor input, try again.");
            }

            try {
                int testY = Integer.parseInt(y);
                if (testY >= height) {
                    System.out.println("Y out of bounds");
                } else {
                    this.y = Integer.parseInt(y);
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                System.out.println("y not a number or poor input, try again.");
            }
        } // didn't type anything
        else {
            System.out.print("Blank, try again. \n");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // tells if the last command was flagging
    public boolean getF() {
        boolean realF = f;
        f = false;
        return realF;
    }

}
