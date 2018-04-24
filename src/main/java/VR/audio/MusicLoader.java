package VR.audio;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicLoader {

    private HashMap<String, Clip> music;

    public MusicLoader() {
        music = new HashMap<>();
        //Add music here! or Effects...?
        addMusic("MainBegin", "/music/Alku(MAIN).wav");
        addMusic("MainMiddle", "/music/Keski(MAIN).wav");
        addMusic("MainEnd", "/music/Loppu(MAIN).wav");
    }

    public Clip getMusic(String name) {
        if (music.get(name) == null) {
            return null;
        }
        return music.get(name);
    }

    public void addMusic(String name, String s) {
        Clip clip = null;
        try {
            InputStream audioSrc = getClass().getResourceAsStream(s);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
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
//            System.out.println("Audio error :S");
//            Main.login.error();
            e.printStackTrace();
            System.exit(-1);
        }

        music.put(name, clip);
    }
}
