package ui;

import javax.swing.JFrame;

/**
 *
 * @author antsax
 */

// The GUI works as the primary interface for the user. It uses the class "Board" to render the board.

public class GUI extends JFrame {
    
    private Board board;

    // We will create the frame with the desired width and height. The constructor manages some default settings.
    
    public GUI(int width, int height) {
        this.board = new Board(width, height);
        
        this.setTitle("Minesweeper");
        this.setSize(board.getXSquares() * board.getSquareSize() + board.guiSpaceX(), board.getYSquares() * board.getSquareSize() + board.guiSpaceY());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        this.setContentPane(board);
    }
    
    // This method calls another method in Board which clicks on the desired location.
    
    public void clickBoard(int mx, int my) {
        board.click(mx, my);
        board.updateUI();
    }

}
