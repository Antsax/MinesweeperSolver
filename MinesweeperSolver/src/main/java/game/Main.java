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
        //asker.askSize();
        //asker.askMines();
        
        int w= 30;
        int h = 16;
        int mines = 99;

        Gamefield game = new Gamefield(w, h, mines);

        game.generateField();

        //GUI visual = new GUI(asker.getX(), asker.getY());
        System.out.println(game.toString());

        Inspector inspector = new Inspector(game.getSquares(), game.getWidth(), game.getHeight());

        GameConsole console = new GameConsole(game.getWidth(), game.getHeight());

        SolvingAlgorithm solver = new SolvingAlgorithm(inspector, game.getSquares(), w, h, game);

        if (asker.askSolver()) {
            solver.solve();
        } else {
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
