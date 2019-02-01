package game;

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
    private int uncheckedSquares;

    public Inspector(Square squares[][], int width, int height) {
        this.squares = squares;
        this.width = width;
        this.height = height;
        this.uncheckedSquares = width * height;
    }

    public void reveal(int x, int y) {
        if (x < width && y < height) {
            Square square = squares[x][y];
            if (!square.isChecked()) {
                square.check();
                uncheckedSquares -= 1;
                if (square.isMine()) {
                    gameLost();
                } else if (square.getValue() == 0) {
                    revealEmpty(x, y);
                }
            }
        }
    }

    public void checkVictory(int mines) {
        if (mines == uncheckedSquares) {
            System.out.println("You won! Congratulations");
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
}
