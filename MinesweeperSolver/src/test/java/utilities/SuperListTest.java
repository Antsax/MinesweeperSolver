package utilities;

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
public class SuperListTest {
    
    SuperList list;
    
    public SuperListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.list = new SuperList();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void listExists() {
        assertNotNull(list);
    }
    
    @Test
    public void addingWorks() {
        int x = 2;
        list.add(x);
        assertEquals(2, list.get(0));
    }
    
    @Test
    public void doublingCapacity() {
        list.doubleCapacity();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        
        assertEquals(19, list.get(19));
    }
    
    @Test
    public void emptyInBeginning() {
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void sizeIsCorrect() {
        assertEquals(0, list.size());
    }
    
    @Test
    public void getWorks() {
        list.add(1);
        assertNotNull(list.get(0));
    }
    
    @Test
    public void pollingWorks() {
        list.add(1);
        int x = (int) list.poll();
        assertEquals(1, x);
    }
    
    @Test
    public void removingWorks() {
        list.add(1);
        list.remove(0);
        assertEquals(0, list.size());
    }
    
    @Test
    public void listContains() {
        int x = 1;
        list.add(x);
        assertTrue(list.contains(x));
    }
}
