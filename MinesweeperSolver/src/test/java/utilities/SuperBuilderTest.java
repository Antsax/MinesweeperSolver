/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class SuperBuilderTest {
    
    SuperBuilder builder;
    
    public SuperBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.builder = new SuperBuilder();
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void builderExists() {
        assertNotNull(builder);
    }
    
    @Test
    public void stringIsCorrect() {
        String testi = "Testi testinen";
        builder.append("Testi testinen");
        
        assertEquals(testi, builder.toString());
    }
}
