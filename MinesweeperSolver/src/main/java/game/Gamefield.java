package game;

import java.util.Random;

/**
 *
 * @author antsax
 */
// The logical aspect of the game
public class Gamefield {

    private final Square[][] squares;
    private boolean started;
    private final int mines;
    private final int height;
    private final int width;

    public Gamefield(int width, int height, int mines) {
        this.squares = new Square[width][height];
        this.started = false;
        this.mines = mines;
        this.width = width;
        this.height = height;
    }

    // Creates an array with squares
    public void generateField() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                squares[x][y] = new Square(false, 0);
            }
        }
    }

    // Puts mines in the field
    public void generateMines() {
        if (mines != 0) {
            Random rng = new Random();
            for (int n = 0; n < mines; n++) {
                while (true) {
                    int x = rng.nextInt(getWidth());
                    int y = rng.nextInt(getHeight());
                    if (!squares[x][y].isMine()) {
                        squares[x][y].setMine();
                        break;
                    }
                }
            }
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Square[][] getSquares() {
        return squares;
    }

    // Prints the board
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                builder.append(squares[x][y].toString());
                builder.append(" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}
