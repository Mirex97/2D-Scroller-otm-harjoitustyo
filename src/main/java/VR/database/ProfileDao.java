package VR.database;

import VR.profile.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProfileDao implements Dao<Profile, Integer> {

    private Database database;

    public ProfileDao(Database base) {
        this.database = base;
    }

    @Override
    public Profile findOne(Integer key) throws SQLException {
        return null;
    }

    @Override
    public List<Profile> findAll() throws SQLException {
        return null;
    }

    @Override
    public Profile saveOrUpdate(Profile object) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement testStatement = conn.prepareStatement("SELECT * FROM Profiles WHERE (name IS ?)");
        testStatement.setString(1, object.getName());
        ResultSet rs = testStatement.executeQuery();
        boolean hasOne = rs.next();
        if (hasOne) {
            testStatement.close();
            rs.close();
            conn.close();
            return null;
        }
        testStatement.close();
        rs.close();
        PreparedStatement saveStatement = conn.prepareStatement("INSERT INTO Profiles (name, password) "
                + "VALUES (?, ?)");
        saveStatement.setString(1, object.getName());
        saveStatement.setString(2, object.getPassword());
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
        PreparedStatement removeStatement = conn.prepareStatement("DELETE FROM Profiles WHERE (id = ?)");
        removeStatement.setInt(1, key);
        removeStatement.execute();
        removeStatement.close();
        conn.close();
        System.out.println("Should be gone!");
    }

    public Profile findWithUsername(String key, String password) throws SQLException {
        Profile prof = null;
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Profiles "
                + "WHERE (name IS ?)"
                + "AND (password IS ?)");
        stmt.setString(1, key);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        prof = new Profile(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
        rs.close();
        stmt.close();
        conn.close();

        return prof;
    }

}
