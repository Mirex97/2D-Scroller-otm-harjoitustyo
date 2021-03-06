package VR.gui;

import VR.Main;
import VR.entities.EntityCustom;
import VR.mapitems.Text;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Gui {

    private EntityCustom background;
    private boolean timelimit;

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

    private Timer timer;
    private int uptime;
    private boolean uptimer;

    public Gui() {
        timelimit = false;
        timer = new Timer(-1);
        init();
    }

    public Gui(int time) {
        timelimit = true;
        timer = new Timer(time);
        timer.setOn(true);
        init();
    }

    public Timer getTimer() {
        return timer;
    }

    private void init() {
        speechBox = new EntityCustom("gui/TalkBox.png");
        speechBox.setSpeed(2);
        speechBox.setY(0 - (int) speechBox.getHeight());
        textMinY = 0 - (int) speechBox.getHeight();
        portrait = Main.images.getPlayer();
        background = new EntityCustom("gui/Gui.png");
        text = null;

        uptimer = false;
    }

    public Text getText() {
        return text;
    }

    public void write(Text text) {
        this.text = text;
        if (text.getCharacters().get(0).equals("Player")) {
            portrait = Main.images.getPlayer();
        }
        if (text.getTime() != -1) {
            this.uptime = text.getTime();
            uptimer = true;
        } else {
            this.uptime = -1;
            uptimer = false;
        }
        toggle = false;
        currentText = 0;
        textAmount = text.getMessages().size();
    }

    public void resetText() {
        this.text = null;

    }

    public void clearRect() {
        Main.gui.clearRect(0, 0, Main.width, Main.height);
    }

    public void draw(long l) {
        Main.gui.clearRect(0, 0, Main.width, Main.height);
        background.draw(Main.gui);
        Main.gui.setFill(Color.WHITE);
        if (timelimit) {
            if (!timer.getEnded()) {
                timer.update(l);
                Main.gui.setFont(Font.font("Impact", 20));
                Main.gui.fillText("" + timer.getTime(), 955, 55);
            } else {
                Main.gui.fillText("TIMEOUT", 948, 55);
            }
        }
    }

    public void drawText() {
        portrait.setXY(speechBox.getX() + 100, speechBox.getY() + 10);
        speechBox.draw(Main.gui);
        portrait.draw(Main.gui);
        if (!toggle) {
            if (!rolledDOWN) {
                rolledUP = false;
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
                    if (text.getCharacters().get(currentText).equals("Player")) {
                        portrait = Main.images.getPlayer();
                        Main.gui.fillText(Main.options.getPlayername(), portrait.getX(), portrait.getY() + 3, portrait.getWidth());
                    } else {
                        if (text.getCharacters().get(currentText).equals("Intercom")) {
                            portrait = Main.images.getIntercom();
                        }
                        if (text.getCharacters().get(currentText).equals("Smurfradar")) {
                            portrait = Main.images.getRadar();
                        }
                        Main.gui.fillText(text.getCharacters().get(currentText), portrait.getX(), portrait.getY() + 3, portrait.getWidth());
                    }
                    Main.gui.setFont(Font.font("Impact", 20));
                    Main.gui.setFill(Color.WHITE);
                    Main.gui.fillText(text.getMessages().get(currentText).substring(0, currentLetter), portrait.getX() + portrait.getWidth() + 50, portrait.getY() + (portrait.getHeight() / 2), speechBox.getWidth());
                    if (currentLetter < text.getMessages().get(currentText).length()) {
                        if (text.getMessages().get(currentText).charAt(currentLetter) == ' ') {
                            Main.talk.play();
                        }
                        currentLetter++;
                    } else {
                        if (Main.keys.getInput().contains("ENTER")) {
                            currentLetter = 0;
                            letters = text.getMessages().get(currentText).length();
                            currentText++;
                            if (uptimer) {
                                uptime = text.getTime();
                            }
                        }

                        if (uptimer) {
                            if (uptime > -1) {
                                uptime--;
                            } else {
                                currentLetter = 0;
                                letters = text.getMessages().get(currentText).length();
                                currentText++;
                                uptime = text.getTime();
                            }
                        }

                    }
                }

            }

        } else {
            if (!rolledUP) {
                rolledDOWN = false;
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

    public void drawScore(Score score) {
        if (score.getActualScore() < 0) {
            Main.gui.setFill(Color.RED);
            Main.gui.setFont(Font.font("Impact", 20));
            Main.gui.fillText("" + score.getScore(), 10, 55);
        } else {
            Main.gui.setFont(Font.font("Impact", 20));
            Main.gui.fillText("" + score.getScore(), 10, 55);
        }
        Main.gui.setFill(Color.WHITE);
        
    }
}
