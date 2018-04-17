package VR.gui;

import VR.Main;
import VR.entities.EntityCustom;
import VR.mapitems.Text;
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

    private boolean rolledUP = true;
    private boolean rolledDOWN = false;
    private boolean toggle = false;
    private Text text;
    private EntityCustom speechBox;
    private EntityCustom portrait;

    private int textMinY;
    private int textAmount = 0;
    private int currentText = 0;
    private int letters = 0;
    private int currentLetter = 0;

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
        speechBox = new EntityCustom("gui/TalkBox.png");
        speechBox.setSpeed(2);
        speechBox.setY(0 - (int) speechBox.getHeight());
        textMinY = 0 - (int) speechBox.getHeight();
        portrait = new EntityCustom("characters/player/PlayerBox.png");
        startTime = false;
        ended = false;
        background = new EntityCustom("gui/Gui.png");
        text = null;
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

    public Text getText() {
        return text;
    }

    //Set writable text!
    public void write(Text text) {
        this.text = text;
        if (text.getCharacters().get(0).equals("Player")) {
            portrait.setImage("characters/player/PlayerBox.png");
        }
        toggle = false;
        currentText = 0;
        textAmount = text.getMessages().size();
    }

    public void resetText() {
        this.text = null;

    }
    
    public void clearRect() { //for manual use!
        Main.gui.clearRect(0, 0, Main.width, Main.height);
    }

    public void drawText() {
        portrait.setXY(speechBox.getX() + 100, speechBox.getY() + 10);
        speechBox.draw(Main.gui);
        portrait.draw(Main.gui);
        if (!toggle) {
            if (!rolledDOWN) {
                rolledUP = false;
                //Set everything to move down!
                //Once done set rolledDOWN to true;
                speechBox.setDir(EntityCustom.Dir.DOWN);
                if (speechBox.getY() >= 0) {
                    speechBox.setDir(EntityCustom.Dir.STILL);
                    rolledDOWN = true;
                }
            } else {
                if (currentText >= textAmount) {
                    toggle = true;
                } else {
                    Main.gui.setFont(Font.font("Impact", 15));
                    Main.gui.setFill(Color.WHITE);
                    Main.gui.setFont(meh);
                    if (text.getCharacters().get(currentText).equals("Player")) {
                        Main.gui.fillText(Main.options.getPlayername(), portrait.getX(), portrait.getY() + 3, portrait.getWidth());
                    } else {
                        Main.gui.fillText(text.getCharacters().get(currentText), portrait.getX(), portrait.getY() + 20, portrait.getWidth());
                    }
                    Main.gui.setFont(Font.font("Impact", 20));
                    Main.gui.setFill(Color.WHITE);
                    Main.gui.setFont(meh);
                    Main.gui.fillText(text.getMessages().get(currentText).substring(0, currentLetter), portrait.getX() + portrait.getWidth() + 50, portrait.getY() + (portrait.getHeight() / 2), speechBox.getWidth());
                    if (currentLetter < text.getMessages().get(currentText).length()) {
                        currentLetter++;
                    } else {
                        if (Main.keys.getInput().contains("ENTER")) {
                            currentLetter = 0;
                            letters = text.getMessages().get(currentText).length();
                            currentText++;
                        }
                    }

                }
            }
        } else {
            if (!rolledUP) {
                rolledDOWN = false;
                //Set everything to move up!
                //Once done set rolledUP to true;
                speechBox.setDir(EntityCustom.Dir.UP);
                if (speechBox.getY() <= textMinY) {
                    speechBox.setDir(EntityCustom.Dir.STILL);
                    rolledUP = true;
                }
            } else {
                if (text != null) {
                    resetText();
                }
            }

        }

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
            Main.gui.setFont(Font.font("Impact", 20));
            Main.gui.fillText("" + time, 955, 55);
        } else {
            Main.gui.fillText("TIMEOUT", 948, 55);
        }
    }
}
