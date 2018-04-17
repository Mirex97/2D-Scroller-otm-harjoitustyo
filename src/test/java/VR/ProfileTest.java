package VR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import VR.profile.Profile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ProfileTest {
    private Profile prof; 
    
    
    @Before
    public void setUp() {
        prof = new Profile(1, "Test", "test");
    }
    
    @Test
    public void setNamePasswordAndId() {
        prof.setId(10);
        assertTrue("Set didn't work! That's bad! " + prof.getId(), prof.getId() == 10);
        prof.setName("NotATest");
        assertTrue("Set didn't work! That's even worse!" + prof.getName(), prof.getName().equals("NotATest"));
        prof.setPassword("Where did you learn to fly?");
        assertTrue("Set password didn't work! " + prof.getPassword(), prof.getPassword().equals("Where did you learn to fly?"));
        
    }
}
