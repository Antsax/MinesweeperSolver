package utilities;

/**
 *
 * @author antsax
 */

// A class designed to append strings together and print out the desired compilation

public class SuperBuilder {
    
    private String complete;
    
    public SuperBuilder() {
        this.complete = "";
    }
    
    //Appends strings
    public void append(String app) {
        this.complete = this.complete + app;
    }
    
    //Print outcome
    @Override 
    public String toString() {
        return complete;
    }
}
