package VR.database;

import VR.profile.Options;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OptionsDao implements Dao<Options, Integer> {

    private Database database;

    public OptionsDao(Database database) {
        this.database = database;
    }

    @Override
    public Options findOne(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Options "
                + "WHERE profile_id = ?");
        stmt.setInt(1, key);
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        Options option = new Options(
                rs.getInt("id"),
                rs.getInt("profile_id"),
                rs.getDouble("volume"),
                rs.getString("playername"),
                rs.getInt("resolution"),
                rs.getBoolean("fullscreen"));
        rs.close();
        stmt.close();
        conn.close();
        return option;
    }

    @Override
    public List<Options> findAll() throws SQLException {
        return null;
    }

    @Override
    public Options saveOrUpdate(Options object) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement testStatement = conn.prepareStatement("SELECT * FROM Options WHERE (profile_id = ?)");
        testStatement.setInt(1, object.getProfile_id());
        ResultSet rs = testStatement.executeQuery();
        boolean hasOne = rs.next();
        if (hasOne) {
            testStatement.close();
            PreparedStatement updateStatement = conn.prepareStatement("UPDATE Options SET volume = ?, "
                    + "playername = ?,"
                    + "resolution = ?,"
                    + "fullscreen = ?"
                    + "WHERE (profile_id = ?)");
            updateStatement.setDouble(1, object.getVolume());
            updateStatement.setString(2, object.getPlayername());
            updateStatement.setInt(3, object.getResolution());
            updateStatement.setBoolean(4, object.isFullscreen());
            updateStatement.setInt(5, object.getProfile_id());
            updateStatement.executeUpdate();
            updateStatement.close();

            rs.close();
            conn.close();
            return null;
        }
        testStatement.close();
        rs.close();
        PreparedStatement saveStatement = conn.prepareStatement("INSERT INTO Options (profile_id, volume, playername, resolution, fullscreen) "
                + "VALUES (?, ?, ?, ?, ?)");
        saveStatement.setInt(1, object.getProfile_id());
        saveStatement.setDouble(2, object.getVolume());
        saveStatement.setString(3, object.getPlayername());
        saveStatement.setInt(4, object.getResolution());
        saveStatement.setBoolean(5, object.isFullscreen());
        saveStatement.executeUpdate();
        saveStatement.close();

        PreparedStatement retrieveStatement = conn.prepareStatement("SELECT last_insert_rowid() as id");

        rs = retrieveStatement.executeQuery();
        rs.next();
        int latestId = rs.getInt("id");
        object.setId(latestId);
        rs.close();
        retrieveStatement.close();
        conn.close();
        return object;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement removeStatement = conn.prepareStatement("DELETE FROM Options WHERE (profile_id = ?)");
        removeStatement.setInt(1, key);
        removeStatement.execute();
        removeStatement.close();
        conn.close();
        System.out.println("Should be all gone!");
    }

}
