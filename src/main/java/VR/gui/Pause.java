package VR.gui;

import VR.Main;
import VR.entities.EntityCustom;

public class Pause {

    private EntityCustom logo;
//    private EntityCustom onContinue;
//    private EntityCustom offContinue;
//    private EntityCustom onSave;
//    private EntityCustom offSave;
//    private EntityCustom onReturnToMenu;
//    private EntityCustom offReturnToMenu;
//    private EntityCustom onQuit;
//    private EntityCustom offQuit;
//    private EntityCustom onYESon;
//    private EntityCustom offYESof;
//    private EntityCustom onNO;
//    private EntityCustom offNO;

//    private boolean saved;
    private boolean isPaused;
    private int wait;

    public Pause() {

        logo = new EntityCustom("menu/VrTheAdventure.png");
        isPaused = false;

    }

    public void setPaused(Boolean pause) {
        if (pause) {
            wait = 30;
        }
        isPaused = pause;
    }

    public boolean getPaused() {
        return isPaused;
    }

    public void draw() {
        Main.pause.clearRect(0, 0, Main.width, Main.height);
        Main.pause.setGlobalAlpha(0.6);
        Main.pause.fillRect(0, 0, Main.width, Main.height);
        Main.pause.setGlobalAlpha(1);

        logo.draw(Main.pause);

        if (Main.keys.getInput().contains("ESCAPE") && wait <= 0) {
            Main.pause.clearRect(0, 0, Main.width, Main.height);
            setPaused(false);
        }
        if (wait > 0) {
            wait--;
        }
    }
}
