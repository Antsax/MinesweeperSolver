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
    private static Scanner reader = new Scanner(System.in);
    private int width;
    private int height;

    public GameConsole(int w, int h) {
        this.width = w;
        this.height = h;
        x = 100000;
        y = 100000;
        
    }

// The start() method will question the user on which square it wants to interact with
    public void start() {
        System.out.println("Choose square coordinates as x,y");
        String answer = reader.nextLine();
        System.out.println("");

        if (!answer.isEmpty()) {
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
        } else {
            System.out.print("Blank, try again. \n");
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
