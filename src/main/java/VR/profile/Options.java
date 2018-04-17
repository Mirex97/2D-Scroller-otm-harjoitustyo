package VR.profile;

public class Options {

    private int id;
    private double volume;
    private String playername;
    private int profile_id;

    public Options(int id, int profileid, double volume, String playername) {
        this.id = id;
        this.profile_id = profileid;
        this.volume = volume;
        this.playername = playername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
