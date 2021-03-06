package solver;

import game.Gamefield;
import game.Inspector;
import game.Square;
import java.util.Random;
import utilities.SuperList;

// The solving algorithm which solves the game of Minesweeper
public class SolvingAlgorithm {

    private BoardReader reader;
    private int width;
    private int height;
    boolean borderoptimization;
    private SuperList<boolean[]> tankSolutions;
    private Square[][] tankBoard = null;
    private boolean[][] knownMine = null;
    private boolean[][] knownEmpty = null;
    private int mines;
    private Gamefield game;

    public SolvingAlgorithm(Inspector cursor, Square grid[][], Gamefield game) {
        this.reader = new BoardReader(grid, cursor, game);
        this.width = game.getWidth();
        this.height = game.getHeight();
        this.mines = game.getMines();
        this.game = game;
    }

    public void solve() {

        // Our algorithm functions best, when it reveals a location that has a value of 0, so it will reveal other squares
        // around it and give the algorithm more info to work with. For this reason, at the start of the game we will provide
        // it with the location of a square that has a value of 0. This will only occur in the beginning and will not be
        // implemented later. If this functionality wasn't provided, the algorithm would have to randomly check squares
        // until a suitable candidate was found. This works, but it takes too much time and we have to restart the game
        // too many times
        boolean found = false;
        for (int x = 0; x < width; x++) {
            if (found) {
                break;
            }
            for (int y = 0; y < height; y++) {
                if (reader.getSquare(x, y).getTrueValue() == 0) {
                    reader.reveal(x, y);
                    found = true;
                    break;
                }
            }
        }

        try {
            int reruns = 0;
            boolean triedTank = false;

            for (int times = 1; times <= 300; times++) {
                System.out.println(game.toString());
                reader.checkVictory();
                if (reader.didBoardChange()) {
                    reruns = 0;
                    triedTank = false;
                } else {
                    reader.didBoardChange();
                    if (reruns < 3) {
                        reruns++;
                    } else {
                        if (!triedTank) {
                            tankSolver();
                            triedTank = true;
                        } else {
                            System.out.println("Nothing happened");
                        }
                    }
                }

                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        if (reader.getSquare(x, y).getValue() > 0) {
                            solveSingle(x, y);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    // Solve a single square
    public void solveSingle(int x, int y) {
        int countClosed = reader.countUnopenedSquaresAround(x, y);
        if (countClosed == 0) {
            return;
        }

        int alreadyFlagged = reader.countFlagsAround(x, y);
        int minesAround = reader.getSquare(x, y).getValue();

        // Flag as many squares as possible
        if (minesAround == countClosed + alreadyFlagged) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i + x > -1 && i + x < width && j + y > -1 && j + y < height) {
                        reader.getSquare(x + i, y + j).flag();
                    }
                }
            }

            alreadyFlagged = reader.countFlagsAround(x, y);
        }

        // Open as many squares as possible
        if (minesAround == alreadyFlagged) {
            reader.revealAround(x, y);
        }
    }

    // Tanksolver, our main solving algorithm.
    public void tankSolver() {
        SuperList<Square> borderSquares = new SuperList<>();
        SuperList<Square> allEmptyBlocks = new SuperList<>();

        // Endgame situation. If there are only few squares left, don't check border tiles
        borderoptimization = false;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!reader.getSquare(x, y).isChecked() && !reader.getSquare(x, y).isFlagged()) {
                    allEmptyBlocks.add(reader.getSquare(x, y));
                }
            }
        }

        // Determine all border tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (reader.isBoundry(x, y) && !reader.getSquare(x, y).isFlagged()) {
                    borderSquares.add(reader.getSquare(x, y));
                }
            }
        }

        // How many squares are outside of our range; squares we can't and don't know anything about
        int blocksOutOfRange = allEmptyBlocks.size() - borderSquares.size();
        if (blocksOutOfRange > 8) {
            borderoptimization = true;
        } else {
            borderSquares = allEmptyBlocks;
        }

        // Something went wrong
        if (borderSquares.isEmpty()) {
            return;
        }

        // Segregation routine. Don't run during endgame.
        SuperList<SuperList<Square>> segregated;
        if (!borderoptimization) {
            segregated = new SuperList<>();
            segregated.add(borderSquares);
        } else {
            segregated = tankSegregate(borderSquares);
        }

        boolean success = false;
        double probBest = 0; //Best probability
        int totalMultiCases = 1;
        int probBestSquare = -1;
        int probBestS = -1;

        for (int currentSquareID = 0; currentSquareID < segregated.size(); currentSquareID++) {
            // Copy everything into temporary constructs
            tankSolutions = new SuperList<>();
            tankBoard = reader.getGrid().clone();

            knownMine = new boolean[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    knownMine[x][y] = reader.getSquare(x, y).isFlagged();
                }
            }

            knownEmpty = new boolean[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    knownEmpty[x][y] = tankBoard[x][y].getValue() >= 0;
                }
            }

            // Compute solutions
            tankRecurse((SuperList<Square>) segregated.get(currentSquareID), 0);

            // Something went wrong
            if (tankSolutions.isEmpty()) {
                return;
            }

            // Check for solved squares
            for (int i = 0; i < segregated.get(currentSquareID).size(); i++) {
                boolean allMine = true;
                boolean allEmpty = true;
                for (int b = 0; b < tankSolutions.size(); b++) {
                    boolean[] sln = (boolean[]) tankSolutions.get(b);
                    if (!sln[i]) {
                        allMine = false;
                    }

                    if (sln[i]) {
                        allEmpty = false;
                    }
                }

                Square square = segregated.get(currentSquareID).get(i);
                if (allMine) {
                    reader.getSquare(square.getX(), square.getY()).flag();
                } else if (allEmpty) {
                    success = true;
                    reader.reveal(square.getX(), square.getY());
                }
            }

            totalMultiCases *= tankSolutions.size();

            //Calculate probabilities
            if (success) {
                continue;
            }
            int maxEmpty = -10000;
            int iEmpty = -1;
            for (int i = 0; i < segregated.get(currentSquareID).size(); i++) {
                int nEmpty = 0;
                for (int b = 0; b < tankSolutions.size(); b++) {
                    boolean[] sln = (boolean[]) tankSolutions.get(b);
                    if (!sln[i]) {
                        nEmpty++;
                    }
                }
                if (nEmpty > maxEmpty) {
                    maxEmpty = nEmpty;
                    iEmpty = i;
                }
            }

            double prob = (double) maxEmpty / (double) tankSolutions.size();

            if (prob > probBest) {
                probBest = prob;
                probBestSquare = iEmpty;
                probBestS = currentSquareID;
            }

        }

        if (success) {
            return;
        }

        Square s = segregated.get(probBestS).get(probBestSquare);
        reader.reveal(s.getX(), s.getY());
    }

    // Segregated areas. This makes our algorithm faster, as we only need to check individual areas.
    private SuperList<SuperList<Square>> tankSegregate(SuperList<Square> borderSquares) {
        SuperList<SuperList<Square>> allRegions = new SuperList<>();
        SuperList<Square> checked = new SuperList<>();

        while (true) {
            SuperList<Square> queue = new SuperList<>();
            SuperList<Square> finishedRegion = new SuperList<>();

            //Choose a point from where to start
            for (int i = 0; i < borderSquares.size(); i++) {
                Square firstS = (Square) borderSquares.get(i);
                if (!checked.contains(firstS)) {
                    queue.add(firstS);
                    break;
                }
            }

            if (queue.isEmpty()) {
                break;
            }

            while (!queue.isEmpty()) {
                Square square = (Square) queue.poll();
                finishedRegion.add(square);
                checked.add(square);

                for (int i = 0; i < borderSquares.size(); i++) {
                    Square compareSquare = (Square) borderSquares.get(i);
                    boolean isConnected = false;
                    if (finishedRegion.contains(compareSquare)) {
                        continue;
                    }
                    if (Math.abs(square.getX() - compareSquare.getX()) > 2 || Math.abs(square.getY() - compareSquare.getY()) > 2) {
                        isConnected = false;
                    } else {
                        // Search all of the squares
                        squareSearch:
                        for (int x = 0; x < width; x++) {
                            for (int y = 0; y < height; y++) {
                                if (reader.getSquare(x, y).getValue() > 0) {
                                    if (Math.abs(square.getX() - x) <= 1 && Math.abs(square.getY() - y) <= 1 && Math.abs(compareSquare.getX() - x) <= 1 && Math.abs(compareSquare.getY() - y) <= 1) {
                                        isConnected = true;
                                        break squareSearch;
                                    }
                                }
                            }
                        }
                    }

                    if (!isConnected) {
                        continue;
                    }
                    if (!queue.contains(compareSquare)) {
                        queue.add(compareSquare);
                    }

                }
            }

            allRegions.add(finishedRegion);
        }

        return allRegions;
    }

    // See if everything checks. If it does, continue on with the algorithm.
    private void tankRecurse(SuperList<Square> borderSquares, int depth) {
        // Return if at this point, it's already inconsistent

        int flagCount = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Count flags
                if (knownMine[x][y]) {
                    flagCount++;
                }

                int currentSquareValue = tankBoard[x][y].getValue();
                if (currentSquareValue < 0) {
                    continue;
                }
                // Scenario 1: too many mines
                if (reader.countFlagsAround(x, y) > currentSquareValue) {
                    return;
                }

                //Total bordering squares
                int borderingSquares;
                if ((x == 0 && y == 0) || (x == width - 1 && y == height - 1)) {
                    borderingSquares = 3;
                } else if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
                    borderingSquares = 5;
                } else {
                    borderingSquares = 8;
                }

                //Scenario 2: too many empty
                if (borderingSquares - reader.countFlagsAround(x, y) < currentSquareValue) {
                    return;
                }

            }
        }

        //We have too many flags
        if (flagCount > mines) {
            return;
        }

        // We have found a solution
        if (depth == borderSquares.size()) {

            // If mine count is incorrect, we don't have a solution
            if (!borderoptimization && flagCount < mines) {
                return;
            }

            boolean[] solution = new boolean[borderSquares.size()];
            for (int i = 0; i < borderSquares.size(); i++) {
                Square square = (Square) borderSquares.get(i);
                solution[i] = knownMine[square.getX()][square.getY()];
            }

            tankSolutions.add(solution);
            return;
        }

        Square square = (Square) borderSquares.get(depth);

        // Recurse two positions: mine and no mine
        knownMine[square.getX()][square.getY()] = true;
        tankRecurse(borderSquares, depth + 1);
        knownMine[square.getX()][square.getY()] = false;

        knownEmpty[square.getX()][square.getY()] = true;
        tankRecurse(borderSquares, depth + 1);
        knownEmpty[square.getX()][square.getY()] = false;

    }
}
