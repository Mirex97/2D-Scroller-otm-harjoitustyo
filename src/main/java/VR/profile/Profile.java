
package VR.profile;
/*Not yet Implemented!
This class will contain the player profile which is loaded from database!*/
public class Profile {
    private int id;
    private String name;
    private String password;
    
    public Profile(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
