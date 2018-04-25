
package VR;

import VR.gui.Timer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TimerTest {
    private Timer timer;
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        timer = new Timer(100);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void getters() {
        assertTrue("Should be False", !timer.getEnded());
        assertTrue("Should be False", !timer.getOn());
    }
    
    @Test
    public void setters() {
        timer.setOn(true);
        assertTrue("Should be True", timer.getOn());
        timer.setTime(1);
        timer.count();
        assertTrue("Should be True", timer.getEnded());
    }
    
    @Test
    public void update() {
        timer.update(1);
        timer.update(1_000_000_002);
        assertTrue("Didn't count? " + timer.getTime(), timer.getTime() == 99);
        
    }

    
}
