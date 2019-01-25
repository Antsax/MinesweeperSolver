package ui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author antsax
 */
// The Board class renders the gameboard visually
public class Board extends JPanel {

    private final int spacing = 2;
    private final int xSquares;
    private final int ySquares;
    private final int square = 50;
    private int mx;
    private int my;

    public Board(int xSquares, int ySquares) {
        this.xSquares = xSquares;
        this.ySquares = ySquares;
    }

    // Renders the field
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, xSquares * square, ySquares * square);
        for (int i = 0; i < xSquares; i++) {
            for (int j = 0; j < ySquares; j++) {
                g.setColor(Color.GRAY);
                if (mx >= spacing + i * square && mx < spacing + i * square + square - 2 * spacing && my >= spacing + j * square && my < spacing + j * square + square - 2 * spacing) {
                    g.setColor(Color.RED);
                }
                g.fillRect(spacing + i * square, spacing + j * square, square - 2 * spacing, square - 2 * spacing);
            }
        }
    }

    public int getYSquares() {
        return ySquares;
    }

    public int getXSquares() {
        return xSquares;
    }

    public int getSquareSize() {
        return square;
    }

    public int guiSpaceX() {
        return spacing * xSquares / 10 + spacing;
    }

    public int guiSpaceY() {
        return spacing * ySquares + 12 * spacing;
    }

    // Interact with a specific location
    public void click(int mx, int my) {
        this.mx = mx * square - square / 2;
        this.my = my * square - square / 2;
        System.out.println("X:" + mx + " Y:" + my);
    }
}
