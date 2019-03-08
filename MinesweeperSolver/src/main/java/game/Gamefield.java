package game;

import java.util.Random;
import utilities.SuperBuilder;

/**
 *
 * @author antsax
 */
// The logical aspect of the game. Generates the field, the mines and gives values of the game.
public class Gamefield {

    private final Square[][] squares;
    private boolean started;
    private int mines;
    private final int height;
    private final int width;

    // Pretty straightforward
    public Gamefield(int width, int height, int mines) {
        this.squares = new Square[width][height];
        this.started = false;
        this.mines = mines;
        this.width = width;
        this.height = height;
    }

    // Creates an array with squares and mines. In other word, creates the field
    public void generateField() {
        long timeBeginning = System.currentTimeMillis();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                squares[x][y] = new Square(false, 0, x, y);
                squares[x][y].x = x;
                squares[x][y].y = y;
            }
        }
        
        // Once the field has been created, put the mines in
        generateMinesFisherYates(squares);
        
        // Give each square its proper value
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                setValue(squares[i][j], i, j);
            }
        }
        long timeEnding = System.currentTimeMillis();
        System.out.println("It took " + (timeEnding - timeBeginning) + " ms to generate the map");
    }

    // Puts mines in the field
    // Generate mines using Fisher-Yates shuffle
    // Current time complexity is O(mines)
    public void generateMinesFisherYates(Square[][] array) {
        Random random = new Random();
        int tempMines = mines;

        for (int i = width - 1; i > 0; i--) {
            if (tempMines == 0) {
                break;
            }
            for (int j = height - 1; j > 0; j--) {
                int m = random.nextInt(i);
                int n = random.nextInt(j);

                Square temp = array[i][j];
                array[i][j] = array[m][n];
                array[m][n] = temp;
                array[m][n].setMine();

                tempMines--;
                if (tempMines == 0) {
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

    // Returns the gamefield
    public Square[][] getSquares() {
        return squares;
    }

    // Returns the amount of mines
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

    // Prints the board but with the mines revealed.
    public String showMines() {
        SuperBuilder builder = new SuperBuilder();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (squares[x][y].isMine()) {
                    builder.append("X");
                    builder.append(" ");
                } else {
                    builder.append(squares[x][y].toString());
                    builder.append(" ");
                }
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}
