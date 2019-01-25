package game;

/**
 *
 * @author antsax
 */
public class Square {
    
    private boolean mine;
    private boolean checked;
    private boolean flagged;
    private int value;
    
    public Square(boolean mine, int value) {
        this.mine = mine;
        this.checked = false;
        this.flagged = false;
        this.value = value;
    }
    
    public void flag() {
        this.flagged = !this.flagged;
    }
    
    public boolean isChecked() {
        return checked;
    }

    public boolean isMine() {
        return mine;
    }

    public boolean isFlagged() {
        return flagged;
    }
    
    public void check() {
        this.checked = true;
        flagged = false;
    }
    
    public void setMine() {
        this.mine = true;
    }
    
    @Override
    public String toString() {
        if (flagged) {
            return "X";
        }
        
        if (mine) {
            if (checked) {
                return "O";
            }
            
            return "*";
        }
        
        return "#";
    }
    
    public int getValue() {
        return value;
    }
}
