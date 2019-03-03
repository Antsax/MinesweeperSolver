package ui;

import java.util.Scanner;

/**
 *
 * @author antsax
 */

// The asker asks the user at the start of the game about desired values. They are width, height and amount of mines.
public class Asker {

    Scanner reader = new Scanner(System.in);
    private int x = 9;
    private int y = 9;
    private int mines = 10;

    // Ask the width and height
    public void askSize() {
        System.out.println("How many squares would you like the grid to have by width? \nDefault: 9 \nRange: 8-? \n(leave this blank if you prefer default)\n");
        String answer = reader.nextLine();
        if (!answer.isEmpty() && Integer.parseInt(answer) >= 8) {
            x = Integer.parseInt(answer);
        }

        System.out.println("\nHow many squares would you like the grid to have by height? \nDefault: 9 \nRange: 1-? \n(leave this blank if you prefer default) \n");
        String answer2 = reader.nextLine();
        if (!answer2.isEmpty() && Integer.parseInt(answer2) >= 1) {
            y = Integer.parseInt(answer2);
        }
    }

    // Ask how many mines
    public void askMines() {
        System.out.println("\nHow many mines would you prefer? \nDefault: 10 \nRange: 0 - [(width * height) - 1]  \n(leave this blank if you prefer default) \n");
        String answer3 = reader.nextLine();
        if (!answer3.isEmpty() && Integer.parseInt(answer3) >= x * y) {
            mines = x * y - 1;
        } else if (!answer3.isEmpty() && Integer.parseInt(answer3) >= 0) {
            mines = Integer.parseInt(answer3);
        } else if (answer3.isEmpty() && 10 >= x * y) {
            mines = x*y - 1;
        }
    }
    
    // Ask if the user wants the solver to solve the game
    public boolean askSolver() {
        System.out.println("Would you like for the algorithm to solve the game? Y/N");
        String answer = reader.nextLine();
        if (answer.equals("Y")) {
            return true;
        }
        
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMines() {
        return mines;
    }

}
