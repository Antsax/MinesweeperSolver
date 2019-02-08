package solver;

import game.Inspector;
import game.Square;

public class BoardReader {

    private Square grid[][];
    private int width;
    private int height;
    private Inspector cursor;
    

    public BoardReader(Square field[][], Inspector reader, int width, int height) {
        this.grid = field;
        this.width = width;
        this.height = height;
        this.cursor = reader;
    }

    public Square[][] getGrid() {
        return grid;
    }

    public void gameStart() {
        cursor.reveal(width / 2, height / 2);
    }
}
