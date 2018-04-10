package VR.sections;

import VR.Main;
import VR.entities.EntityCustom;
import VR.events.Level1Cutscene;
import VR.keyhandlers.Keylistener;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

public class Intro implements section {

    private final Stage stage;
    private final Canvas canvas;
    private final GraphicsContext gc;

    private final EntityCustom train;
    private final EntityCustom anyKey;
    private final EntityCustom logo;

    private Boolean anyKeyPressed = false;
    private final Keylistener keys;

    private final int width;
    private final int height;
    private final double scale;

    private Level1Cutscene cut;

    /*Mitä pitää tehdä vielä: Toiminnallisuus valikolle <-- oma luokka?. 
    Valikko heittää siitä peliin tai pois pelistä. Valikko myös tallennuksille.
    Lisäksi myös creditsit! Luon näille mahdollisesti omat luokat, 
    koska venyy tämän metodi liian pitkäksi.
    Napit:
    - New Game (aloittaa uuden pelin).
    - Load Game (lataa jo tallennetun pelin, tulostaa No saves jos ei ole tallennuksia).
    - Credits (heittää lopputeksteihin. Ja jos sen skippaa niin palaa takaisin menuun.)
    - Quit (sulkee pelin, sama toiminnallisuus kuin nytten --> Stage.Close()).
    
    Vielä kertaalleen mitkä animaatiot puuttuvat:
    - Valikko <-- Todennäköisesti tässä luokassa. Pitkän rimpsun perään. MUUTOS tästä tuleekin luokka!!
    - Lopputekstit <-- Oma luokka! Voi kutsua sitten pelin voitettua!
    - Tallennus menu <-- Oma luokka. Tämän metodi vain kutsuu draw ja välittää profiilin tiedot.
    - DONE*/
    public Intro() {
        width = Main.width;
        height = Main.height;
        scale = Main.scale;
        keys = Main.keys;
        stage = Main.stage;
        canvas = Main.canvas;

//        stage.initStyle(StageStyle.TRANSPARENT);
//        scene.setFill(Color.TRANSPARENT);
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

        cut = new Level1Cutscene(stage, gc);
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
                gc.clearRect(0, 0, width * scale, height * scale);
                if (stop) {
                    stop();
                }
                if (!keys.getInput().isEmpty()) {
                    anyKeyPressed = true;
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
        }.start();

        stage.show();
    }

}
