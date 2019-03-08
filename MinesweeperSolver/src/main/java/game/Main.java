package game;

import solver.SolvingAlgorithm;
import ui.*;

/**
 *
 * @author antsax
 */
// The main class of the game
public class Main {

    private static int x;
    private static int y;

    public static void main(String[] args) {
        Asker asker = new Asker();

        // Ask the user for the size of the board and amount of mines
        asker.askSize();
        asker.askMines();

        Gamefield game = new Gamefield(asker.getX(), asker.getY(), asker.getMines());

        // Generate the field
        game.generateField();

        //GUI visual = new GUI(asker.getX(), asker.getY());
        // Reveal the location of the mines. This will only happen in the beginning of the game.
        // This is done so the user may see the difficulty of the game and if the algorithim truly 
        // solves it. 
        System.out.println(game.showMines());

        Inspector inspector = new Inspector(game.getSquares(), game.getWidth(), game.getHeight());
        GameConsole console = new GameConsole(game.getWidth(), game.getHeight());
        SolvingAlgorithm solver = new SolvingAlgorithm(inspector, game.getSquares(), game);

        // Ask if you want for the algorithim to solve the game. 
        if (asker.askSolver()) {
            solver.solve();
        } else {

            // Player wants to solve the game by themselves
            while (true) {
                console.start();

                //visual.clickBoard(console.getX(), console.getY());
                if (!console.getF()) {
                    inspector.reveal(console.getX(), console.getY());
                } else {
                    inspector.flagSquare(console.getX(), console.getY());
                }

                System.out.println(game.toString());
                inspector.checkVictory(asker.getMines());
            }
        }
    }

}
