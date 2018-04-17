package VR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import VR.profile.Save;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SaveTest {
    private Save save;
    
    @Before
    public void setUp() {
        this.save = new Save(1, "A", true, true, false);
    }
    
    @Test
    public void checkIdAndLetter() {
        save.setId(2);
        save.setLetter("B");
        assertTrue("ID was not set! " + save.getId(), save.getId() == 2);
        assertTrue("Letter was not set! " + save.getLetter(), save.getLetter().equals("B"));
    }
    
    @Test
    public void checkBooleans() {
        save.setTutorialDone(false);
        save.setLevel1Done(false);
        save.setLevel2Done(true);
        assertTrue("Dang it Tutorial! " + save.isTutorialDone(), !save.isTutorialDone());
        assertTrue("Dang it Level1! " + save.isLevel1Done(), !save.isLevel1Done());
        assertTrue("Dang it Level2! " + save.isLevel2Done(), save.isLevel2Done());
        
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
