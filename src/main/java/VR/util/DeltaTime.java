package VR.util;

public class DeltaTime {

    private long last_time;

    public DeltaTime() {
        last_time = System.nanoTime();
    }

    public boolean deltaTime(long l) {
        int delta_time = (int) ((l - last_time) / 1000000);
        if (delta_time >= 15) {
            last_time = l;
            return true;
        }
        return false;
    }
}
