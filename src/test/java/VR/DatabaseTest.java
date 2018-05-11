/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VR;

import static VR.Main.base;
import VR.database.*;
import VR.profile.Options;
import VR.profile.Profile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseTest {

    private static String address;
    private static Database base;
    private static Connection conn;
    private static ProfileDao profs;
    private static OptionsDao opts;
    private static Profile prof;
    private static Options opt;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void setUp() {
        address = "jdbc:sqlite:test.db";
        base = new Database(address);
        conn = base.getConnection();
        base.createNewTables();

        profs = new ProfileDao(base);
        opts = new OptionsDao(base);
    }

    @AfterClass
    public static void breakDown() {

        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("ERROR!");
        } finally {
            System.out.println("CLOSED!");
        }
        System.out.println("BREAKING DOWN!");
        String rem = System.getProperty("user.dir").replace('\\', '/') + "/test.db";

        if (!rem.equals("wat")) {
            
            File remove = new File(rem);
            if (remove.exists()) {
                System.out.println("EXISTS");
                remove.deleteOnExit();
            } else {
                System.out.println("DOESNT EXIST!");
            }
        } else {
            System.out.println("ERROR!");
        }

    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void fakeDatabase() {
        Database fakeBase = new Database("THISISNOTCORRECTBASE");
        try {
            assertTrue("It exists O_O", fakeBase.getConnection() == null);
            fakeBase.createNewTables();
            assertEquals("Tables not created!", outContent.toString());
        } catch (Exception e) {

        }
    }

    @Test
    public void databaseExists() {
        System.out.println("Testing databaseExists");
        Connection conn = null;
        try {
            conn = base.getConnection();
            assertTrue("Connection is not null!", conn != null);
            conn.close();
        } catch (Exception e) {
            assertTrue("Connection error!", false);
        }

        assertTrue("Connection not found!", conn != null);

    }

    @Test
    public void testUselessStuff() {
        try {
            assertTrue("Should be null right?", opts.findAll() == null);
            assertTrue("Should be null right?", profs.findAll() == null);
            assertTrue("Should be null right?", profs.findOne(1) == null);

        } catch (SQLException ex) {
            System.out.println("WAT");
        }
    }

    @Test
    public void createsAndDoesntCreateAndDeletesUser() {
        System.out.println("Testing createsAndDoesntCreateAndDeletesUser");

        prof = new Profile(-1, "Name", "Password");

        try {
            prof = profs.saveOrUpdate(prof);
            Profile save = prof;
            opt = new Options(-1, prof.getId(), 1.0, "MEH", 100, true);
            opt = opts.saveOrUpdate(opt);
            assertTrue("Profile has incorrect id! " + prof.getId(), prof.getId() != -1);
            assertTrue("Options has incorrect id! " + opt.getId(), opt.getId() != -1);
            assertTrue("Options has incorrect profile_id! " + opt.getProfile_id(), opt.getProfile_id() != -1);
            assertTrue("Profile not existing!", profs.findWithUsername(save.getName(), save.getPassword()) != null);
            opt = opts.findOne(prof.getId());
            assertTrue("Options were not found although should exits!", opt != null);
            opt.setVolume(0);
            opt = opts.saveOrUpdate(opt);
            assertTrue("Should be updated!", opt == null);
            prof = profs.saveOrUpdate(prof);
            assertTrue("Profile was saved...! Although taken!!!", prof == null);
            opts.delete(save.getId());
            profs.delete(save.getId());
            assertTrue("Options not deleted!", opts.findOne(save.getId()) == null);
            assertTrue("Profile not deleted!", profs.findWithUsername(save.getName(), save.getPassword()) == null);

        } catch (Exception e) {
            assertTrue("Not able to save!", false);
        }
    }

}
