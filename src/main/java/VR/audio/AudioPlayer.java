package VR.audio;

import VR.Main;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;

public class AudioPlayer {

    private Clip clip;

    public AudioPlayer(String s) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        } catch (Exception e) {
            Main.login.error();
        }
    }

    public void play() {
        if (clip == null) {
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
