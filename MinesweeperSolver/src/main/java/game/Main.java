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
        
        //GUI visual = new GUI(asker.getX(), asker.getY());

        game.generateField();
        
        System.out.println(game.toString());
        
        Inspector inspector = new  Inspector(game.getSquares(), game.getWidth(), game.getHeight());

        GameConsole console = new GameConsole(game.getWidth(), game.getHeight());
        while (true) {
            console.start();
            
            //visual.clickBoard(console.getX(), console.getY());
            
            inspector.reveal(console.getX(), console.getY());
            inspector.checkVictory(asker.getMines());
            
            System.out.println(game.toString());
        }
    }

}
