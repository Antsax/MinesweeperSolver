package game;

/**
 *
 * @author antsax
 */
public class Square {

    private boolean mine;
    private boolean checked;
    private int value;

    public Square(boolean mine, int value) {
        this.mine = mine;
        this.checked = false;
        this.value = value;
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

    @Override
    public String toString() {
        if (isChecked() && !isMine()) {
            return Integer.toString(value);
        }
        
        if(isChecked() && isMine()) {
            return "*";
        }
        
        return "X";
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
