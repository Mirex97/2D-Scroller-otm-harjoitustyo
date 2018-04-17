/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VR;

import static VR.Main.base;
import VR.database.*;
import VR.profile.Profile;
import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {

    private Database base;
    private ProfileDao profs;
    private OptionsDao opts;
    private Profile prof;

    @Before
    public void setUp() {
        base = new Database("jdbc:sqlite:saves.db");
        base.getConnection();
        base.createNewTables();
        profs = new ProfileDao(base);
        opts = new OptionsDao(base);
    }

    @Test
    public void databaseExists() {
        Connection conn = null;
        try {
            conn = base.getConnection();
        } catch (Exception e) {
            assertTrue("Connection error!", false);
        }

        assertTrue("Connection not found!", conn != null);

    }

    @Test
    public void createsAndDoesntCreateAndDeletesUser() {
        base.getConnection();
        prof = new Profile(-1, "Name", "Password");
        try {
            prof = profs.saveOrUpdate(prof);
            Profile save = prof;
            assertTrue("Profile has incorrect id! " + prof.getId(), prof.getId() != -1);
            prof = profs.saveOrUpdate(prof);
            assertTrue("Profile was saved...! Although null!", prof == null);
            profs.delete(save.getId());
            assertTrue("Profile not deleted!", profs.findWithUsername(save.getName(), save.getPassword()) == null);
        } catch (Exception e) {
            assertTrue("Not able to save!", false);
        }
        

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
