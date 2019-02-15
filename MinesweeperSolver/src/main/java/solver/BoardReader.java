package solver;

import game.Gamefield;
import game.Inspector;
import game.Square;
import java.util.Random;

public class BoardReader {

    private Square grid[][];
    private int width;
    private int height;
    private Inspector cursor;
    private Random rng;
    private boolean[][] flags; //All flags in the map
    private Gamefield game;

    public BoardReader(Square field[][], Inspector reader, int width, int height, Gamefield game) {
        this.grid = field;
        this.width = width;
        this.height = height;
        this.cursor = reader;
        this.rng = new Random();
        this.flags = null;
        this.game = game;
    }

    public Square[][] getGrid() {
        return grid;
    }

    public void gameStart() {
        cursor.reveal(width / 2, height / 2);
    }

    public void revealAround(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + x > -1 && i + x < width && j + y > -1 && j + y < height) {
                    cursor.reveal(x + i, y + j);
                }
            }
        }
    }

    public void checkVictory() {
        cursor.checkVictory(game.getMines());
    }

    public void reveal(int x, int y) {
        cursor.reveal(x, y);
    }
    
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
        if (grid[x][y].isChecked()) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + x > -1 && i + x < width && j + y > -1 && j + y < height) {
                    Square neighbour = grid[x + i][y + j];
                    if (neighbour.isChecked() || neighbour.isFlagged()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
