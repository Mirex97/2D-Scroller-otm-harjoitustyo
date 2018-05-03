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

/**
 * Soittaa annetun äänen ja säätää myös äänenvoimakkuuden
 *
 * @version 1.0 3 May 2018
 * @author Mikael Kukkamäki
 */
public class AudioPlayer {

    private Clip clip;
    private double volume;
    private boolean loopPlay;

    /**
     * Konstruktori täytyy etukäteen ladata. Suositeltavana olla Main luokassa.
     */
    public AudioPlayer() {
        loopPlay = true;
    }

    /**
     * metodi changeClip vaihtaa tämän hetkisen äänen toiseen.
     *
     * @param clip on ääni johon halutaan vaihtaa.
     * @see VR.audio.MusicLoader
     */
    public void changeClip(Clip clip) {
        if (this.clip != null) {
            if (this.clip.isRunning()) {
                this.clip.stop();
            }
        }
        this.clip = clip;
        reloadVolume();
    }

    /**
     * metodi toggleLoop vaihtaa onko soitin, joko loopissa vai ei. Tämä
     * kannattaa asettaa ennen kuin mennään AnimationTimer osioihin.
     */
    public void toggleLoop() {
        if (loopPlay) {
            loopPlay = false;
        } else {
            loopPlay = true;
        }
    }

    /**
     * metodi play käynnistää olemassa olevan musiikin. Ei tee mitään, jos
     * musiikki ei ole tai se pyörii jo.
     *
     * @see VR.audio.MusicLoader
     */
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

    /**
     * metodi stop keskeyttää musiikin.
     */
    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * metodi isRunning palauttaa, jos musiikki on päällä.
     *
     * @return palauttaa true tai false, riippuen onko musiikki päällä.
     * @see VR.audio.MusicLoader
     */
    public boolean isRunning() {
        return clip.isRunning();
    }

    /**
     * metodi setVolume asettaa äänenvoimakkuuden.
     *
     * @param volume äänenvoimakkuus johon voimakkuus asetetaan.
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * metodi reloadVolume lataa voimakkuuden uudelleen olemassa olevalle
     * äänelle. Ääni pitää olla olemassa ennen tätä metodia.
     */
    public void reloadVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    /**
     * metodi getMicroPosition palauttaa äänen kohdan, jossa se on. Tätä metodia
     * käytin alussa, mutta aiheutti ihmeelistä pätkintää. Katkaisin äänet
     * Audacityllä osiksi ja tämä on turha.
     *
     * @return palauttaa äänen mikrokohdan.
     */
    public long getMicroPosition() {
        return clip.getMicrosecondPosition();
    }

    /**
     * metodi changeClip vaihtaa tämän hetkisen äänen toiseen. Tämä pysäyttää
     * biisin, vaihtaa kohdan ja käynnistää.
     *
     * @param seconds vaihtaa äänen kohdan (millisekunnit).
     */
    public void setMicroPosition(long seconds) {
        clip.stop();
        clip.setMicrosecondPosition(seconds);

        clip.start();
    }

    /**
     * metodi close sulkee äänen kokonaan. Tämän jälkeen ei voi käyttää ääntä
     * enään.
     */
    public void close() {
        stop();
        clip.close();
    }
}
