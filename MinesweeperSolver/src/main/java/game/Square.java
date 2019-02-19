package game;

/**
 *
 * @author antsax
 */
public class Square {

    private boolean mine;
    private boolean checked;
    private int value;
    private boolean flagged;
    int x, y;

    public Square(boolean mine, int value, int x, int y) {
        this.mine = mine;
        this.checked = false;
        this.value = value;
        this.flagged = false;
        this.x = x;
        this.y = y;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isMine() {
        return mine;
    }

    public void check() {
        this.checked = true;
    }

    public void setMine() {
        this.mine = true;
    }

    public void flag() {
        if (!isChecked() && flagged == false) {
            /*if (flagged == false) {
                flagged = true;
            } else {
                flagged = false;
            }*/
            flagged = true;
        }
    }

    public boolean isFlagged() {
        return flagged;
    }

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

    public int getValue() {
        if (checked == true) {
            return value;
        } else {
            return -1;
        }
    }
    
    
    // Only use this for start of algorithm
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
