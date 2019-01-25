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

// The start() method will question the user on which square it wants to interact with
    
    public void start() {
        System.out.println("Choose square coordinates as x,y");
        String answer = reader.nextLine();

        if (!answer.isEmpty()) {
            int index = answer.indexOf(',');
            String x = answer.substring(0, index);
            String y = answer.substring(index + 1, answer.length());

            try {
                int testX = Integer.parseInt(x);
                this.x = Integer.parseInt(x);
            } catch (NumberFormatException e) {
                System.out.println("x not a number or poor input, try again.");
            }

            try {
                int testY = Integer.parseInt(y);
                this.y = Integer.parseInt(y);
            } catch (NumberFormatException e) {
                System.out.println("y not a number or poor, try again.");
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
