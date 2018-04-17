package VR.gui;

import VR.Main;
import VR.entities.EntityCustom;

public class Pause {

    private EntityCustom logo;
    private EntityCustom onContinue;
    private EntityCustom offContinue;
    private EntityCustom onSave;
    private EntityCustom offSave;
    private EntityCustom onQuit;
    private EntityCustom offQuit;
//    private EntityCustom onYES;
//    private EntityCustom offYES;
//    private EntityCustom onNO;
//    private EntityCustom offNO;

//    private boolean saved;
    private boolean isPaused;
    private int selection = 0;
    private int selAmount = 3;
    private int wait = 15;
    private boolean waitON = false;
    private boolean stop;
    

    public Pause() {
        int spacing = 50;
        this.stop = false;

        logo = new EntityCustom("menu/VrTheAdventure.png");
        onContinue = new EntityCustom("menu/Continue (selected).gif");
        onContinue.setXY((Main.width / 2) - (int) (onContinue.getWidth() / 2), (Main.height / 2) - 20);
        offContinue = new EntityCustom("menu/Continue (deselected).png", onContinue.getX() + 10, onContinue.getY());
        onSave = new EntityCustom("gui/Save (selected).gif", onContinue.getX(), onContinue.getY() + spacing);
        offSave = new EntityCustom("gui/Save (deselected).png", onSave.getX() + 10, onSave.getY());
        onQuit = new EntityCustom("menu/Quit (selected).gif", onSave.getX(), onSave.getY() + spacing);
        offQuit = new EntityCustom("menu/Quit (deselected).png", onQuit.getX() + 10, onQuit.getY());
        isPaused = false;

    }

    public void setPaused(Boolean pause) {
        isPaused = pause;
    }
    
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    public boolean getStop() {
        return this.stop;
    }
    
    public void clearRect() {
        Main.pause.clearRect(0, 0, Main.width, Main.height);
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

        if (!waitON) {
            if ((Main.keys.getInput().contains("S") && Main.keys.getInput().contains("W"))
                    || (Main.keys.getInput().contains("UP")
                    && Main.keys.getInput().contains("DOWN"))) {
                //donothing
            } else if (Main.keys.getInput().contains("W")
                    || Main.keys.getInput().contains("UP")) {
                if (selection - 1 >= 0) {
                    selection--;
                } else {
                    selection = selAmount - 1;
                }
                waitON = true;
            } else if (Main.keys.getInput().contains("S")
                    || Main.keys.getInput().contains("DOWN")) {
                if (selection + 1 < selAmount) {
                    selection++;
                } else {
                    selection = 0;
                }
                waitON = true;
            }
        } else {
            if (wait <= 0) {
                wait = 15;
                waitON = false;
            } else {
                wait--;
            }
        }

        //CONTINUE
        if (selection == 0) {
            onContinue.draw(Main.pause);
        } else {
            offContinue.draw(Main.pause);
        }
        if (selection == 1) {
            onSave.draw(Main.pause);
        } else {
            offSave.draw(Main.pause);
        }
        if (selection == 2) {
            onQuit.draw(Main.pause);
        } else {
            offQuit.draw(Main.pause);
        }

        if (Main.keys.getInput().contains("ENTER")) {
            hitEnter();
        }
    }

    public void hitEnter() {
        if (selection == 0) {
            Main.pause.clearRect(0, 0, Main.width, Main.height);
            setPaused(false);
        }

        if (selection == 1) {
            System.out.println("Not implemented");
        }

        if (selection == 2) {
            Main.pause.clearRect(0, 0, Main.width, Main.height);
            setStop(true);
        }
    }
}
