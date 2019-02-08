package solver;

import game.Inspector;
import game.Square;

public class SolvingAlgorithm {

    private BoardReader reader;

    public SolvingAlgorithm(Inspector cursor, Square grid[][], int width, int height) {
        this.reader = new BoardReader(grid, cursor, width, height);
    }

    public void solve() {
        reader.gameStart();
    }
}
