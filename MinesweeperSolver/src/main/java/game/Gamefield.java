package game;

import java.util.Random;
import utilities.SuperBuilder;

/**
 *
 * @author antsax
 */
// The logical aspect of the game
public class Gamefield {

    private final Square[][] squares;
    private boolean started;
    private int mines;
    private final int height;
    private final int width;

    public Gamefield(int width, int height, int mines) {
        this.squares = new Square[width][height];
        this.started = false;
        this.mines = mines;
        this.width = width;
        this.height = height;
    }

    // Creates an array with squares and mines. In other word, creates the field
    public void generateField() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                squares[x][y] = new Square(false, 0, x, y);
                squares[x][y].x = x;
                squares[x][y].y = y;
            }
        }

        generateMinesFisherYates(squares);
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                setValue(squares[i][j], i, j);
            }
        }
    }

    // Puts mines in the field
    // Generate mines using Fisher-Yates shuffle
    // Current time complexity is O(mines)
    public void generateMinesFisherYates(Square[][] array) {
        Random random = new Random();

        for (int i = width; i > 0; i--) {
            if (mines == 0) {
                break;
            }
            for (int j = height; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);
                
                Square temp = array[i][j];
                array[i][j] = array[m][n];
                array[m][n] = temp;
                array[i][j].setMine();
                
                mines--;
                if (mines == 0) {
                    break;
                }
            }
        }
    }

    // Set value for each square
    public void setValue(Square square, int x, int y) {
        int cnt = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + x > -1 && i + x < getWidth() && j + y > -1 && j + y < getHeight()) {
                    Square neighbour = squares[x + i][y + j];
                    if (neighbour.isMine()) {
                        cnt++;
                    }
                }
            }
        }

        square.setValue(cnt);
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

    public int getMines() {
        return mines;
    }

    // Prints the board
    @Override
    public String toString() {
        SuperBuilder builder = new SuperBuilder();
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
