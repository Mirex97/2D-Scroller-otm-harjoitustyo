
package VR.database;

import VR.Main;
import java.sql.*;

public class Database {
    private String databaseAddress;

    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseAddress);
        } catch (Exception e) {
            return null;
        }
        return conn;
    }
    
    public void createNewTables() {
        String profiles = "CREATE TABLE IF NOT EXISTS Profiles (\n"
                + " id Integer PRIMARY KEY, \n"
                + " name varchar(25) NOT NULL, \n"
                + " password String NOT NULL\n"
                + ");";
        String options = "CREATE TABLE IF NOT EXISTS Options (\n"
                + " id Integer PRIMARY KEY, \n"
                + " profile_id Integer, \n"
                + " volume Double, \n"
                + " playername String NOT NULL, \n"
                + " resolution Integer NOT NULL, \n"
                + " fullscreen Boolean NOT NULL, \n"
                + " FOREIGN KEY (profile_id) REFERENCES Profiles(id)\n"
                + ");";
        String saves = "CREATE TABLE IF NOT EXISTS saves (\n"
                + " id Integer PRIMARY KEY, \n"
                + " profile_id Integer, \n"
                + " letter varchar(1), \n"
                + " score Integer, \n"
                + " tutorialDone Integer, \n"
                + " level1Done Integer, \n"
                + " level2Done Integer, \n"
                + " FOREIGN KEY (profile_id) REFERENCES Profiles(id)\n"
                + ");";
        try (Connection conn = DriverManager.getConnection(this.databaseAddress)) {
            Statement prof = conn.createStatement();
            prof.execute(profiles);
            prof.execute(options);
            prof.execute(saves);
            conn.close();
        } catch (SQLException e) {
            System.out.println("Tables not created!");
            Main.login.error();
        }
    }
    
    
}
