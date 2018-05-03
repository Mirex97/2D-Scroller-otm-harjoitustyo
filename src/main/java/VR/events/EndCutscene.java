package VR.events;

import VR.Main;
import VR.gui.Pause;
import VR.levels.Test1;
import VR.sections.Section;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//This cutscene will be played everytime a new game is started! --> Takes the player into tutorial.
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
                                try {
                                    Main.test = new Test1();
                                } catch (Exception e) {
                                    System.out.println("Did not work! Test1!");
                                    Main.login.error();
                                }
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
                        System.out.println(width);
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
            text = "This was a demo...";
            return text;
        }
        if (scene == 1) {
            text = "Didn't see that coming! :P";
            return text;
        }
        if (scene == 2) {
            text = "Viimeiset sanat: On ollut pahuksen hauskaa tehdä tätä projektia vaikka pieneltä vaikuttaa! \n"
                    + "(Tämä oli ensimmäinen koskaan tekemäni peli!)\n\n"
                    + "Kuitenkin nyt on jäänyt pahasti ajasta kiinni (ja tämä oli tosi laaja projekti näin vähälle ajalle muutenkin)\n "
                    + "Mutta kuitenkin tarkoituksena olikin alun perin tutustua pelien tekoon \n"
                    + "ja myös ymmärtää minkälaista se on! (Jatkoa ajatellen siis!)\n"
                    + "Jatkokehitys ideana aijon luoda kunnon pelin \n\n"
                    + "ja tähän käytän pelimoottorina esimerkiksi \n"
                    + "Unityä tai Unrealia (Niistä olen kiinnostunut tällä hetkellä)\n\n"
                    + "Virheitä joita tein alusta pitäen: Ei kunnon suunnitelmia alussa ja menin heti työn kimppuun.\n"
                    + "(Innostuin ehkä vähän liikaakin pelistä alusta pitäen ja ongelmia alkoi kasaantua \n"
                    + "(Viitaten libtiled ja mavenin yhteensopivuus. Jonka sain korjattua onneksi!))\n"
                    + "Grafiikan puute rajoitti myös paljon projektin tekoa. Ja sen aikavaativuus!\n"
                    + "Nyt ymmärrän paremmin miksi pelien tekemisessä vie niin paljon aikaa.\n"
                    + "Mutta kuitenkin opin tosi paljon kaikkea uutta.\n"
                    + "Käytetyt sovellukset: Tiled (Kentät), Audacity, Aseprite (Kaikki grafiikat), \n"
                    + "Netbeans, Rytmik Ultimate (ÄÄnitehosteet)\n"
                    + "Kiitokset vielä Tuukalle biiseistä!";
            return text;
        }

        return text;
    }

}
