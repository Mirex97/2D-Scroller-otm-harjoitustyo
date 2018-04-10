package VR.gui;

import VR.Main;
import VR.entities.EntityCustom;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Gui {

    private int time;
    private EntityCustom background;
    private Font meh;
    private boolean timelimit;
    private boolean startTime;
    private boolean ended;
    private AnimationTimer timer;
    private long lastTime;

    public Gui() {
        timelimit = false;
        time = -1;
        init();
    }

    public Gui(int time) {
        timelimit = true;
        this.time = time;
        init();
    }

    private void init() {
        startTime = false;
        ended = false;
        background = new EntityCustom("gui/Gui.png");
        Main.gui.setFont(meh);
        lastTime = 0;
    }

    private void startTime() {
        startTime = true;
    }

    public int getTime() {
        return time;
    }

    public boolean getEnded() {
        return ended;
    }

    public void draw(long l) {
        Main.gui.clearRect(0, 0, Main.width, Main.height);
        background.draw(Main.gui);
        Main.gui.setFill(Color.WHITE);
        if (timelimit && !ended) {
            if (lastTime != 0) {
                if (l > lastTime + 1_000_000_000) {
                    time--;
                    if (time <= 0) {
                        time = 0;
                        ended = true;
                    }
                    lastTime = l;
                }
            } else {
                lastTime = l;
            }
        }
        if (!ended) {
            Main.gui.setFont(Font.font ("Impact", 20));
            Main.gui.fillText("Time", Main.width/4, 50);
            Main.gui.fillText(""+time, Main.width/4, 100);
        } else {
            Main.gui.fillText("TIMEOUT", 0, 100);
        }
    }
}
