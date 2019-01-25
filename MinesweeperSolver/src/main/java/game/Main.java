package game;

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
        asker.askSize();
        asker.askMines();

        Gamefield game = new Gamefield(asker.getX(), asker.getY(), asker.getMines());
        GUI visual = new GUI(asker.getX(), asker.getY());

        game.generateField();
        game.generateMines();
        System.out.println(game.toString());

        GameConsole console = new GameConsole();
        while (true) {
            console.start();
            visual.clickBoard(console.getX(), console.getY());
        }
    }

}
