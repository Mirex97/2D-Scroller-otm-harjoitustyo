package VR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import VR.profile.Options;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OptionsTest {
    private Options option;
    
    @Before
    public void setUp() {
        option = new Options(1, 2, 1.4, "Player", 100, true);
    }
    
    @Test
    public void setIDsAndPlayerSettings() {
        option.setId(3);
        option.setProfile_id(3);
        option.setVolume(0);
        option.setPlayername("Do a barrel roll!");
        assertTrue("Set id didn't work! " + option.getId(), option.getId() == 3);
        assertTrue("Set prof_id didn't work! " + option.getProfile_id(), option.getProfile_id() == 3);
        assertTrue("Set volume didn't work! " + option.getVolume(), option.getVolume() == 0);
        assertTrue("Set playername didn't work! " + option.getPlayername(), option.getPlayername().equals("Do a barrel roll!"));
    }
}
