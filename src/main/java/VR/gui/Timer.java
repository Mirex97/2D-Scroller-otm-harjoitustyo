package VR.gui;

public class Timer {

    private int time;
    private boolean on;
    private boolean ended;
    private long lastTime;
    private final int maxTime;

    public Timer(int time) {
        maxTime = time;
        this.time = time;
        ended = false;
        on = false;
        lastTime = 0;
    }
    
    public void resetAll() {
        this.time = maxTime;
        ended = false;
        on = false;
    }

    public void setTime(int time) {
        this.time = time;
        ended = false;
    }

    public int getTime() {
        return time;
    }
    public int getMaxTime() {
        return maxTime;
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
            time = 0;
            ended = true;
        }
    }

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
