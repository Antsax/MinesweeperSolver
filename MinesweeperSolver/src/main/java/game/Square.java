package game;

/**
 *
 * @author antsax
 */

// The basic element of the game; the square
public class Square {

    private boolean mine;
    private boolean checked;
    private int value;
    private boolean flagged;
    int x, y;

    // Is it a mine, what is its value and its coordinates
    public Square(boolean mine, int value, int x, int y) {
        this.mine = mine;
        this.checked = false;
        this.value = value;
        this.flagged = false;
        this.x = x;
        this.y = y;
    }

    // Is the square checked?
    public boolean isChecked() {
        return checked;
    }

    // Is the square a mine?
    public boolean isMine() {
        return mine;
    }

    // Check the square
    public void check() {
        this.checked = true;
    }

    public void setMine() {
        this.mine = true;
    }

    // Flag the square
    public void flag() {
        if (!isChecked() && flagged == false) {
            if (flagged == false) {
                flagged = true;
            } else {
                flagged = false;
            }
            flagged = true;
        }
    }

    // Is the square flagged?
    public boolean isFlagged() {
        return flagged;
    }

    // Print the value of the square
    @Override
    public String toString() {
        if (flagged) {
            return "P";
        }

        if (isChecked() && !isMine()) {
            return Integer.toString(value);
        }

        if (isChecked() && isMine()) {
            return "*";
        }

        return ".";
    }

    // Get the value of the square. Only give the value if the square is checked
    public int getValue() {
        if (checked == true) {
            return value;
        } else {
            return -1;
        }
    }

    // Only use this for start of algorithm. Gives the true value of the square despite of the fact if it's checked or not
    public int getTrueValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
