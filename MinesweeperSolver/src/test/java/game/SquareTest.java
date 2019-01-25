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
public class SquareTest {
    
    Square square;
    
    public SquareTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.square = new Square(false, 3);
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void mineDoesNotExist() {
        assertTrue(!square.isMine());
    }
    
    @Test
    public void valueIsCorrect() {
        assertEquals(square.getValue(), 3);
    }
    
    @Test
    public void flagWorks() {
        square.flag();
        assertTrue(square.isFlagged());
    }
}
