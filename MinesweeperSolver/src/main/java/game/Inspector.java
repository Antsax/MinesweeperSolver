package game;

import utilities.SuperBuilder;

/**
 *
 * @author antsax
 */

/* The inspector class works as the supervisor of the game. It reveals squares and checks if the game ends or continues.
 In other words it works as a rulebook and a judge. 
 */
public class Inspector {

    private Square squares[][];
    private int width;
    private int height;
    private boolean somethingChanged;

    public Inspector(Square squares[][], int width, int height) {
        this.squares = squares;
        this.width = width;
        this.height = height;
        this.somethingChanged = false;
    }

    public void reveal(int x, int y) {
        if (x < width && y < height) {
            Square square = squares[x][y];
            if (!square.isChecked() && !square.isFlagged()) {
                square.check();
                setChange(true);
                if (square.isMine()) {
                    gameLost();
                } else if (square.getValue() == 0) {
                    revealEmpty(x, y);
                }
            }
        }
    }

    public void setChange(boolean value) {
        somethingChanged = value;
    }

    public boolean informChange() {
        return somethingChanged;
    }

    public void flagSquare(int x, int y) {
        setChange(true);
        squares[x][y].flag();
    }

    public void checkVictory(int mines) {
        int count = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if(squares[x][y].isMine() && squares[x][y].isFlagged()) {
                    count++;
                }
            }
        }
        if (count == mines) {
            System.out.println("You won! Congratulations");
            printBoard();
            System.exit(0);
        }
    }

    public void gameLost() {
        System.out.println("You hit a mine! Game over!");
        System.exit(0);
    }

    public void revealEmpty(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i + x > -1 && i + x < width && j + y > -1 && j + y < height) {
                    Square neighbour = squares[x + i][y + j];
                    if (!neighbour.isMine()) {
                        reveal(x + i, y + j);
                    }
                }
            }
        }
    }

    public void printBoard() {
        SuperBuilder builder = new SuperBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                builder.append(squares[x][y].toString());
                builder.append(" ");
            }

            builder.append("\n");
        }

        System.out.println(builder.toString());
    }
}
