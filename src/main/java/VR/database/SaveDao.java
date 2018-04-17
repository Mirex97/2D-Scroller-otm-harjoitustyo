
package VR.database;

import VR.profile.Save;
import java.sql.SQLException;
import java.util.List;

public class SaveDao implements Dao<Save, Integer> {

    private Database database;
    
    public SaveDao(Database base) {
        this.database = base;
    }

    @Override
    public Save findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Save> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Save saveOrUpdate(Save object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
