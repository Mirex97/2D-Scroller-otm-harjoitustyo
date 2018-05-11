package VR.sections;

import VR.Main;
import VR.audio.AudioPlayer;
import VR.entities.EntityCustom;
import VR.handlers.Keylistener;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.stage.Stage;
import VR.sections.Section;


public class Intro implements Section {

    private final Stage stage;
    private final Canvas canvas;
    private final GraphicsContext gc;

    private final EntityCustom train;
    private final EntityCustom anyKey;
    private final EntityCustom logo;

    private Boolean anyKeyPressed = false;
    private final Keylistener keys;

    private final double width;
    private final double height;
    private final double scale;


    
    public Intro() {
        width = Main.width;
        height = Main.height;
        scale = Main.scale;
        keys = Main.keys;
        stage = Main.stage;
        canvas = Main.canvas;

        gc = Main.gc;

        /*Load manageri näitä varten!*/
        this.train = new EntityCustom("objects/K-Juna.gif", 0, 0, 2, 1.25);
        this.train.setX(0 - (int) (this.train.getWidth() * this.train.getScale()));
        this.train.setY((int) (canvas.getHeight() / 2));
        this.train.setDir(EntityCustom.Dir.RIGHT);

        this.anyKey = new EntityCustom("menu/PressanyKey.png", 0, 0, 1, 1);
        this.anyKey.setX((width / 2) - (int) anyKey.getWidth() / 2);
        this.anyKey.setY(height - (int) anyKey.getHeight() * 2);
        this.anyKey.setDir(EntityCustom.Dir.STILL);

        this.logo = new EntityCustom("menu/VrTheAdventure.png", 0, 0, 1, 3);
        this.logo.setX((width / 2) - (int) this.logo.getWidth() / 2);
        this.logo.setY((height / 2) - (int) this.logo.getHeight() / 2);
        this.logo.setDir(EntityCustom.Dir.STILL);
        this.logo.setOpacity(0.0);

    }

    @Override
    public void animate() {

        new AnimationTimer() {
            long time = 0;
            int pullBack = 1;
            double junaStop = canvas.getWidth() / 2;
            double pingPong = 1.0;
            double fade = 0.01;
            boolean pong = true;
            double appear = 0.01;
            boolean appeared = false;
            int hold = 60;
            int waitForClose = 60;
            boolean stop = false;

            @Override
            public void handle(long currentNanoTime) {

                if (Main.delta.deltaTime(currentNanoTime)) {

                    Main.backGround.draw();
                    gc.clearRect(0, 0, width * scale, height * scale);
                    if (stop) {
                        stop();
                    }
                    if (!anyKeyPressed) {
                        Main.bgMusic.play();
                    }
                    if (!keys.getInput().isEmpty() && !anyKeyPressed) {
                        anyKeyPressed = true;
                        Main.bgMusic.changeClip(Main.musicloader.getMusic("MainEnd"));

                    }

                    if (train.getDraw()) {
                        if (train.getX() > canvas.getWidth()) {
                            train.setDraw(false);
                        }
                        if (!anyKeyPressed && train.getX() + (train.getWidth() / 2) >= junaStop) {
                            train.setDir(EntityCustom.Dir.STILL);
                            if (anyKey.getDraw()) {
                                if (pong) {
                                    if (pingPong + fade > 1.0) {
                                        pong = false;
                                    } else {
                                        pingPong += fade;
                                    }
                                } else {
                                    if (pingPong - fade < 0.0) {
                                        pong = true;
                                    } else {
                                        pingPong -= fade;
                                    }
                                }
                                anyKey.setOpacity(pingPong);
                                anyKey.draw(gc);
                            }

                        } else if (anyKeyPressed) {
                            Main.bgMusic.play();
                            anyKey.setDraw(false);
                            train.setDir(EntityCustom.Dir.RIGHT);
                            train.setSpeed(pullBack);
                            pullBack++;
                        }
                        train.draw(gc);
                    } else {

                        logo.setOpacity(appear);
                        logo.draw(gc);
                        if (appear < 1.0 && appeared == false) {
                            appear += 0.01;
                        } else {
                            appeared = true;
                            if (hold <= 0) {
                                if (appear > 0) {
                                    appear -= 0.01;
                                } else {
                                    logo.setDraw(false);
                                    if (waitForClose > 0) {
                                        waitForClose--;
                                    } else {
                                        if (!stop) {
                                            stop = true;
                                            Main.menu.animate();
                                        }
                                    }
                                }
                            } else {
                                hold--;
                            }
                        }

                    }
                    time += 1;
                }
            }

        }.start();

        stage.show();
    }

}
