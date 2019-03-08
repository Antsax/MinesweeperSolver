package game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antsax
 */
public class GamefieldTest {

    Gamefield testfield;
    int width = 1000;
    int height = 1000;
    int mines = 2500;

    public GamefieldTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.testfield = new Gamefield(width, height, mines);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void classCreated() {
        assertNotNull(testfield);
    }

    @Test
    public void timeToGenerateField() {
        long start_time = System.currentTimeMillis();
        testfield.generateField();
        long end_time = System.currentTimeMillis();
        System.out.println("Execution of the subroutine took " + (end_time - start_time) + "ms.");
    }

    @Test
    public void afterGeneratingValuesAreSet() {
        testfield.generateField();
        boolean notEmpty = false;
        looping:
        for (int i = 0; i < testfield.getWidth(); i++) {
            for (int x = 0; x < testfield.getHeight(); x++) {
                if (!testfield.getSquares()[i][x].isMine()) {
                    notEmpty = true;
                    break looping;
                }
            }
        }
        
        assertTrue(notEmpty);
    }
    
    @Test
    public void correctHeight() {
        assertEquals(testfield.getHeight(), height);
    }
    
    @Test
    public void correctWidth() {
        assertEquals(testfield.getWidth(), width);
    }
    
    @Test
    public void correctMines() {
        assertEquals(testfield.getMines(), mines);
    }
}
