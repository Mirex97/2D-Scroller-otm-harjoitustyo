package VR.audio;

import VR.Main;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;

public class AudioPlayer {

    private Clip clip;
    private double volume;
    private boolean loopPlay;

    public AudioPlayer() {
        loopPlay = true;
    }

    public void changeClip(Clip clip) {
        if (this.clip != null) {
            if (this.clip.isRunning()) {
                this.clip.stop();
            }
        }
        this.clip = clip;
        reloadVolume();
    }
    
    public void toggleLoop() {
        if (loopPlay) {
            loopPlay = false;
        } else {
            loopPlay = true;
        }
    }

    public void play() {

        if (clip == null) {
            return;
        }
        if (clip.isRunning()) {
            return;
        }
        stop();
        clip.setFramePosition(0);
        clip.start();

    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public boolean isRunning() {
        return clip.isRunning();
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void reloadVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public long getMicroPosition() {
        return clip.getMicrosecondPosition();
    }

    public void setMicroPosition(long seconds) {
        clip.stop();
        clip.setMicrosecondPosition(seconds);

        clip.start();
    }

    public void close() {
        stop();
        clip.close();
    }
}
