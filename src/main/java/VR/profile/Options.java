package VR.profile;

public class Options {

    private int id;
    private double volume;
    private String playername;
    private int profile_id;
    private int resolution;
    private boolean fullscreen;
    
    public Options(int id, int profileid, double volume, String playername, int resolution, boolean fullscreen) {
        this.id = id;
        this.profile_id = profileid;
        this.volume = volume;
        this.playername = playername;
        this.resolution = resolution;
        this.fullscreen = fullscreen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
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
