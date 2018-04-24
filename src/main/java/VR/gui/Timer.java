package VR.gui;

//Taken from Gui! Better to have it differently!
public class Timer {

    private int time;
    private boolean on;
    private boolean ended;
    private long lastTime;

    public Timer(int time) {
        this.time = time;
        ended = false;
        on = false;
        lastTime = 0;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }
    
    public boolean getEnded() {
        return ended;
    }
    
    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean getOn() {
        return on;
    }

    public void count() {
        time--;
        if (time <= 0) {
            System.out.println("wat");
            time = 0;
            ended = true;
        }
    }

    //For animation purposes!
    public void update(long currentTime) {
        if (lastTime != 0) {
            if (currentTime > lastTime + 1_000_000_000) {
                count();
                lastTime = currentTime;
            }
        } else {
            lastTime = currentTime;
        }
    }

}
