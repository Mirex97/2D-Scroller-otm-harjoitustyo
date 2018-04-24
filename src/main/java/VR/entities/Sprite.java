package VR.entities;

import javafx.scene.image.Image;

public class Sprite {

    private Image[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private int loopPoint;
    private boolean reverse;

    private boolean playedOnce;

    public Sprite() {
        playedOnce = false;
        loopPoint = 0;
        reverse = false;
    }

    public void setReverse() {
        this.reverse = true;
        currentFrame = frames.length-1;
    }

    public void setLoop(int l) {
        this.loopPoint = l;
    }

    public void resetLoop() {
        this.loopPoint = 0;
    }

    public void resetReverse() {
        this.reverse = false;
    }

    public void setFrames(Image[] frames) {
        this.frames = frames;
        currentFrame = 0;
        resetLoop();
        resetReverse();
        startTime = System.nanoTime();
        playedOnce = false;
    }

    public void setDelay(long d) {
        delay = d;
    }

    public void setFrame(int i) {
        currentFrame = i;
    }

    public int getFrame() {
        return currentFrame;
    }

    public Image getImage() {
        return frames[currentFrame];
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }

    public void update() {
        if (delay == -1) {
            return;
        }

        long elapsed = (System.nanoTime() - startTime) / 1000000;

        if (!reverse) {
            if (elapsed > delay) {
                currentFrame++;
                startTime = System.nanoTime();
            }

            if (currentFrame == frames.length) {
                currentFrame = loopPoint;
                playedOnce = true;
            }
        } else {
            if (elapsed > delay) {
                currentFrame--;
                startTime = System.nanoTime();
            }

            if (currentFrame == loopPoint-1) {
                currentFrame = frames.length-1;
                playedOnce = true;
            }
        }

    }

}
