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
        this.testfield = new Gamefield(1000, 1000, 2500);
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
}
