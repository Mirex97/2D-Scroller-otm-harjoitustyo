package VR.audio;

import VR.Main;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Lataa musiikit ja näitä voi hakea avain sanoilla.
 *
 * @version 1.0 3 May 2018
 * @author Mikael Kukkamäki
 */
public class MusicLoader {

    private HashMap<String, Clip> music;
    private HashMap<String, Clip> fx;

    /**
     * Konstruktori täytyy etukäteen ladata. Suositeltavana olla Main luokassa.
     * Tälle voisi luoda myös lataus ruudun, mutta ei ole pakollinen. Paitsi jos
     * vie liian kauan.
     */
    public MusicLoader() {
        music = new HashMap<>();
        fx = new HashMap<>();
        addMusic("MainBegin", "/music/Alku(MAIN).wav");
        addMusic("MainMiddle", "/music/Keski(MAIN).wav");
        addMusic("MainEnd", "/music/Loppu(MAIN).wav");
        addMusic("Dream", "/music/DREAM.wav");
        addMusic("Cancelled", "/music/CANCELLED.wav");
        addFX("SELECT", "/fx/SELECT.wav");
        addFX("TALK", "/fx/TALK.wav");

    }

    /**
     * metodi getMusic etsii musiikin avainsanalla.
     *
     * @param name avainsana, jolla haetaan musiikki.
     * @return palauttaa joko null tai clip olion musiikista.
     * @see VR.audio.AudioPlayer
     */
    public Clip getMusic(String name) {
        if (music.get(name) == null) {
            return null;
        }
        return music.get(name);
    }

    /**
     * metodi getMusic etsii tehosteen avainsanalla.
     *
     * @param name avainsana, jolla haetaan musiikki.
     * @return palauttaa joko null tai clip olion tehosteesta.
     * @see VR.audio.AudioPlayer
     */
    public Clip getFx(String name) {
        if (fx.get(name) == null) {
            return null;
        }
        return fx.get(name);
    }

    /**
     * metodi addMusic lisää musiikin HashMappiin. Jos musiikkia ei löydy tämä
     * vie Error sivulle.
     *
     * @param name avainsana musiikille.
     * @param s musiikin sijainti.
     * @see VR.login.Login#error()
     */
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
            System.out.println("Audio error :S");
            Main.login.error();
        }

        music.put(name, clip);
    }

    /**
     * metodi addFX lisää äänitehpsteen HashMappiin. Jos tehostetta ei löydy tämä vie
     * Error sivulle.
     * @param name avainsana musiikille.
     * @param s tehosteen sijainti.
     * @see VR.login.Login#error()
     */
    public void addFX(String name, String s) {
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
            System.out.println("Audio error :S");
            Main.login.error();
        }

        fx.put(name, clip);
    }
}
