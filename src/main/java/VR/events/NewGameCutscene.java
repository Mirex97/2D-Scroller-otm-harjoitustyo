package VR.events;

import VR.Main;
import VR.sections.Section;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

//This cutscene will be played everytime a new game is started! --> Takes the player into tutorial.
public class NewGameCutscene implements Section {

    private int scene;
    private final int maxScene = 3;

    public NewGameCutscene() {
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
                        Main.bgMusic.changeClip(Main.musicloader.getMusic("Dream"));
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
                            Main.test.animate();
                        }

                    }
                    Text texti = new Text(currentText);
                    texti.setFont(Font.font("Impact", 20));
                    double width = texti.getLayoutBounds().getWidth();
                    System.out.println(width);
                    Main.gui.setFont(Font.font("Impact", 20));
                    Main.gui.fillText(currentText.substring(0, currentLetter), (Main.width/2) - (width/2), 100);
                    Main.gui.setGlobalAlpha(1);

                }
            }
        }.start();

        Main.stage.show();
    }

    public String getSceneText() {
        String text = "";
        if (scene == 0) {
            text = "This is a story of a student!";
            return text;
        }
        if (scene == 1) {
            text = "And this is a test message!";
            return text;
        }
        if (scene == 2) {
            text = "Yeah... it works! But anyways... The story!\n\n"
                    + "After a long lecture in Helsinki, our student has finally\n"
                    + "boarded the K-Train. But the student didn't have enough money\n"
                    + "to load his traveling card! To be continued...";
            return text;
        }

        return text;
    }

}
