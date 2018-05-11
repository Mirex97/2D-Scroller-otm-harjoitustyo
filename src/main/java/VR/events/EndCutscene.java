package VR.events;

import VR.Main;
import VR.gui.Pause;
import VR.levels.Test1;
import VR.sections.Section;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndCutscene implements Section {

    private int scene;
    private final int maxScene = 3;

    public EndCutscene() {
        scene = 0;
    }

    @Override
    public void animate() {

        new AnimationTimer() {
            int wait = 0;
            boolean fadeOut = false;
            double alpha = 0;
            double volume = 1;
            int currentLetter = 0;
            String currentText = getSceneText();
            boolean fadeIn = true;

            @Override
            public void handle(long l) {
                if (Main.delta.deltaTime(l)) {
                    if (fadeIn) {
                        Main.bgMusic.setVolume(volume * Main.options.getVolume());

                        if (volume > 0) {
                            volume -= 0.05;
                        }
                        if (volume <= 0) {
                            volume = 0.0;
                        }
                        Main.gui.setGlobalAlpha(alpha);
                        Main.gui.setFill(Color.BLACK);
                        Main.gui.fillRect(0, 0, Main.width, Main.height);
                        if (alpha < 1) {
                            alpha += 0.01;

                        } else {
                            alpha = 1;
                            volume = 1;
                            Main.bgMusic.changeClip(Main.musicloader.getMusic("MainEnd"));
                            Main.bgMusic.setVolume(volume * Main.options.getVolume());
                            Main.bgMusic.play();

                            fadeIn = false;
                        }
                        Main.bgMusic.reloadVolume();
                    } else {
                        Main.gui.setFill(Color.BLACK);
                        Main.gui.fillRect(0, 0, Main.width, Main.height);
                        Main.gui.setFill(Color.WHITE);
                        if (!fadeOut) {
                            if (Main.keys.getInput().contains("ENTER") && currentLetter >= currentText.length()) {
                                scene++;

                                if (scene >= maxScene) {
                                    fadeOut = true;
                                } else {
                                    currentLetter = 0;
                                    currentText = getSceneText();
                                }
                            }
                            if (currentLetter < currentText.length()) {
                                currentLetter++;
                            }

                        } else {
                            Main.gui.setGlobalAlpha(alpha);
                            if (alpha > 0) {
                                alpha -= 0.01;
                            } else {
                                stop();
                                Main.pauseMenu.clearRect();
                                Main.pauseMenu = new Pause();
                                Main.gui.clearRect(0, 0, Main.width, Main.height);
                                Main.menu.reset();
                                Main.menu.setStop(false);
                                Main.menu.animate();
                                Main.pauseMenu.setPaused(false);
                            }

                        }
                        Text texti = new Text(currentText);
                        texti.setFont(Font.font("Impact", 20));
                        double width = texti.getLayoutBounds().getWidth();
                        Main.gui.setFont(Font.font("Impact", 20));
                        if (currentLetter < currentText.length()) {
                            if (currentText.charAt(currentLetter) == ' ') {
                                Main.talk.play();
                            }
                        }
                        Main.gui.fillText(currentText.substring(0, currentLetter), (Main.width / 2) - (width / 2), 100);
                        Main.gui.setGlobalAlpha(1);

                    }
                }
            }
        }.start();

        Main.stage.show();
    }

    public String getSceneText() {
        String text = "";
        if (scene == 0) {
            text = "This was a demo... full release in near future possibly.";
            return text;
        }
        if (scene == 1) {
            text = "Tämä oli siis harjoitus ja myös tavoite luoda ensimmäinen oma pelini.\n"
                    + "Alunperin suunnittelin tekeväni koko pelin valmiiksi näiden kahden kuukauden aikana,\n "
                    + "mutta tämä sitten tyssääntyi "
                    + "erilaisiin ongelmiin. \n\n"
                    + "Ja ehkä hieman laajahkon projektin valitsin itselleni mutta paljon tästä oppii. \n\n"
                    + "Tämä oli siis hyvin tärkeä haaste itselleni päästä kokeilemaan miltä\n"
                    + "pelinkehittäjänä tuntuu olla ja tiedän nyt 100% varmasti,\n "
                    + "että haluan jatkaa näiden parissa! \n"
                    + "(On jo muutamia ideoita lisää mitä tehdä ja \n "
                    + "tänä kesänä itseasiassa haluan aloittaa heti uuden pelin tekemisen.) \n "
                    + "Tietysti pitää tehdä VR The Adventure remastered versio, \n "
                    + "jollain pelimoottorilla!";
            return text;
        }
        if (scene == 2) {
            text = "Vielä viimeiset sanat: Kiitokset Tuukalle hyvistä biiseistä,\n "
                    + "varmaan jatkossakin pyydän häneltä jeesiä jälleen!\n"
                    + "Käytetyt sovellukset: Aseprite, Audacity, Tiled "
                    + "ja tietysti Netbeans!\n"
                    + "PS. Kannattaa tutustua Tiled sovellukseen.\n"
                    + "Pelini oli inspiroitu 'Shovel Knight' pelistä \n "
                    + "ja sen kautta myös löysin tämän kyseisen ohjelman.\n"
                    + "Ja ei ole pöllömpi! Jatkossa aijon käyttää sitä uudelleen ja paremmin.";
            return text;
        }

        return text;
    }

}
