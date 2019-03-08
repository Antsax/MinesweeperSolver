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
        this.square = new Square(false, 3, 5, 5);
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
        assertEquals(square.getValue(), -1);
    }
    
    @Test
    public void checkedCorrect() {
        assertFalse(square.isChecked());
    }
    
    @Test
    public void isNotMine() {
        assertFalse(square.isMine());
    }
    
    @Test
    public void checkingWorks() {
        square.check();
        assertTrue(square.isChecked());
    }
    
    @Test
    public void setMinesWorks() {
        square.setMine();
        assertTrue(square.isMine());
    }
    
    @Test
    public void flaggingWorks() {
        square.flag();
        assertTrue(square.isFlagged());
    }
    
    @Test
    public void correctPrint() {
        square.check();
        assertEquals(square.toString(), "3");
    }
    
    @Test
    public void trueValue() {
        assertEquals(3, square.getTrueValue());
    }
    
    @Test 
    public void getX() {
        assertEquals(5, square.getX());
    }
    
    @Test
    public void getY() {
        assertEquals(5, square.getY());
    }
}
