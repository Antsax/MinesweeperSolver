/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
// Testing of the GameConsole class. We will not test the input functionality as  that requires a human to type in the value
public class GameConsoleTest {

    GameConsole console;

    public GameConsoleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.console = new GameConsole(30, 16);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void classCreated() {
        assertNotNull(console);
    }
    @Test
    public void xDefaultCorrect() {
        assertEquals(100000, console.getX());
    }

    @Test
    public void yDefaultCorrect() {
        assertEquals(100000, console.getY());
    }
    
    @Test
    public void flagFalseByDefault() {
        assertFalse(console.getF());
    }
}
