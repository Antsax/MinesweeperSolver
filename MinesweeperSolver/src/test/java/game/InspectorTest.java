/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class InspectorTest {

    Inspector inspector;
    int width = 10;
    int height = 10;
    Square[][] squares;

    public InspectorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.squares = new Square[width][height];
        this.inspector = new Inspector(squares, width, height);
        for (int i = 0; i < width; i ++) {
            for (int y = 0; y < height; y++) {
                Square square = new Square(false, 1, i, y);
                squares[i][y] = square;
            }
        }
        
        squares[5][5].setValue(0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void inspectorExist() {
        assertNotNull(inspector);
    }

    @Test
    public void reveals() {
        inspector.reveal(1, 1);
        assertTrue(squares[1][1].isChecked());
    }

    @Test
    public void boardChanged() {
        inspector.reveal(1, 1);
        assertTrue(inspector.informChange());
    }

    @Test
    public void flaggingWorks() {
        inspector.flagSquare(1, 1);
        assertTrue(squares[1][1].isFlagged());
    }

    @Test
    public void revealAround() {
        inspector.reveal(5, 5);
        int cnt = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                cnt += squares[5+i][5+j].getValue();
            }
        }
        
        assertEquals(8, cnt);
    }
}
