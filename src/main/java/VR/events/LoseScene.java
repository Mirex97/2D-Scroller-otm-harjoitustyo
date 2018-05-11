package VR.events;

import VR.Main;
import VR.entities.Animate;
import VR.entities.EntityCustom;
import VR.entities.Player;
import VR.gui.Pause;
import VR.levels.Test1;
import VR.sections.Section;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoseScene implements Section {

    private int scene;
    private final int maxScene = 1;
    private EntityCustom player;
    private String loseType;

    public LoseScene(String loseType) {
        scene = 0;
        this.loseType = loseType;
        player = new EntityCustom("/characters/player/confused.gif", 0, 0, 0, 4);
        player.setXY(((Main.guiCanvas.getWidth() / 2) - (player.getWidth() / 2)), ((Main.guiCanvas.getHeight() / 2) - (player.getHeight() / 2)));
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
                            Main.bgMusic.changeClip(Main.musicloader.getMusic("Cancelled"));
                            Main.bgMusic.setVolume(volume * Main.options.getVolume());
                            Main.bgMusic.play();

                            fadeIn = false;
                        }
                        Main.bgMusic.reloadVolume();
                    } else {
                        Main.gui.setFill(Color.BLACK);
                        Main.gui.fillRect(0, 0, Main.width, Main.height);
                        Main.gui.setFill(Color.WHITE);
                        player.draw(Main.gui);
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
                                Main.pauseMenu.setPaused(false);
                                Main.test.animate();
                                Main.bgMusic.changeClip(Main.musicloader.getMusic("Dream"));
                                Main.bgMusic.play();

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
            if (loseType.equals("CAUGHT")) {
                text = "Wasted 80€...";
            } else if (loseType.equals("OUT")) {
                text = "You fell...";
            } else {
                text = "Where did you learn to fly?.";
            }
            return text;
        }

        return text;
    }

}
