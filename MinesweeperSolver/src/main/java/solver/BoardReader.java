package solver;

import game.Gamefield;
import game.Inspector;
import game.Square;
import java.util.Random;

// Acts as middleman for the solving algorithm to read the board and interact with it.
public class BoardReader {

    private Square grid[][];
    private int width;
    private int height;
    private Inspector cursor;
    private Random rng;
    private boolean[][] flags; //All flags in the map
    private Gamefield game;

    public BoardReader(Square field[][], Inspector reader, Gamefield game) {
        this.grid = field;
        this.width = game.getWidth();
        this.height = game.getHeight();
        this.cursor = reader;
        this.rng = new Random();
        this.flags = null;
        this.game = game;
    }

    // Return the board
    public Square[][] getGrid() {
        return grid;
    }

    // Reveals  all tiles around a square
    /**
     * @param x as x-coordinate
     * @param y as y-coordinate
     */
    public void revealAround(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + x > -1 && i + x < width && j + y > -1 && j + y < height) {
                    cursor.reveal(x + i, y + j);
                }
            }
        }
    }

    // Check if the game has been won
    public void checkVictory() {
        cursor.checkVictory(game.getMines());
    }

    // Reveal a square
    public void reveal(int x, int y) {
        cursor.reveal(x, y);
    }

    // Checks if any changes have occured on the board
    public boolean didBoardChange() {
        return cursor.informChange();
    }

    // Count the free squares around a specific location
    public int countUnopenedSquaresAround(int x, int y) {
        int cnt = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + x > -1 && i + x < width && j + y > -1 && j + y < height) {
                    Square neighbour = grid[x + i][y + j];
                    if (!neighbour.isChecked() && !neighbour.isFlagged()) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    // See if two squares are neigbours
    public boolean areNeighbours(Square s, Square t) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + s.getX() > -1 && i + s.getX() < width && j + s.getY() > -1 && j + s.getY() < height) {
                    Square neighbour = grid[s.getX() + i][s.getY() + j];
                    if (neighbour.getX() == t.getX() && neighbour.getY() == t.getY()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // Count the flagged tiles around a specific location
    public int countFlagsAround(int x, int y) {
        int cnt = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + x > -1 && i + x < width && j + y > -1 && j + y < height) {
                    Square neighbour = grid[x + i][y + j];
                    if (neighbour.isFlagged()) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    public Square getSquare(int x, int y) {
        return grid[x][y];
    }

    // A boundry square is an unrevelaed square with revelaed squares around it
    public boolean isBoundry(int x, int y) {
        if (grid[x][y].isChecked() || grid[x][y].isFlagged()) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + x > -1 && i + x < width && j + y > -1 && j + y < height) {
                    Square neighbour = grid[x + i][y + j];
                    if (neighbour.getValue() >= 0 || neighbour.isFlagged()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
