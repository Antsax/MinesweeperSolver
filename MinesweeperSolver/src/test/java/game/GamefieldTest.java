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
        this.testfield = new Gamefield(10, 10, 15);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void classCreated() {
        assertNotNull(testfield);
    }
}
