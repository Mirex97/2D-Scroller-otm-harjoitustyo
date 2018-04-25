
package VR;

import VR.gui.Score;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ScoreTest {
    private Score score;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        score = new Score();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getScore() {
        assertTrue("WAT!", score.getScore() == 0); 
    }
    
    @Test
    public void setScoreAndUpdateAndGetActual() {
        score.setScore(100);
        assertTrue("Wasn't right..?", score.getActualScore() == 100);
        assertTrue("Should be zero if not updated?", score.getScore() == 0);
        score.updateScore();
        assertTrue("Should be one after update?", score.getScore() == 1);
        score.addScore(-101);
        assertTrue("Actual score should be -1", score.getActualScore() == -1);
        score.updateScore();
        score.updateScore();
        assertTrue("Score should now be same -1", score.getScore() == -1);
    } 
}
