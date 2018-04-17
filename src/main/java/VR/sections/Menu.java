package VR.sections;

import VR.Main;
import VR.entities.EntityCustom;
import VR.gui.Pause;
import VR.handlers.Keylistener;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import VR.sections.Section;

public class Menu implements Section {

    private final EntityCustom logo;
    private final EntityCustom continueGame;
    private final EntityCustom continueGameNot;
    private final EntityCustom newGame;
    private final EntityCustom newGameNot;
    private final EntityCustom load;
    private final EntityCustom loadNot;
    private final EntityCustom credits;
    private final EntityCustom creditsNot;
    private final EntityCustom quit;
    private final EntityCustom quitNot;

    private final Keylistener keys;
    private final GraphicsContext gc;
    private int selection = 0;
    private boolean stop = false;

    public Menu() {
        double spotWidth = ((double) Main.width * (3.0 / 4.0));
        double spotHeight = ((double) Main.height * (2.0 / 4.0));

        logo = new EntityCustom("menu/VrTheAdventure.png", 0, 0, 1, 1.5);
        logo.setXY(10, 10);
        logo.setDir(EntityCustom.Dir.STILL);
        int spacing = 20;
        double size = 1;

        continueGame = new EntityCustom("menu/Continue (selected).gif", (int) spotWidth, (int) spotHeight, 1, size);
        continueGameNot = new EntityCustom("menu/Continue (deselected).png", (int) spotWidth + 10, (int) continueGame.getY(), 1, size);

        newGame = new EntityCustom("menu/NewGame (selected).gif", (int) spotWidth, (int) (continueGame.getY() + continueGame.getHeight() + spacing), 1, size);
        newGameNot = new EntityCustom("menu/NewGame (deselected).png", (int) spotWidth + 10, newGame.getY(), 1, size);

        load = new EntityCustom("menu/LoadGame (selected).gif", (int) spotWidth, (int) (newGame.getY() + newGame.getHeight() + spacing), 1, size);
        loadNot = new EntityCustom("menu/LoadGame (deselected).png", (int) spotWidth + 10, load.getY(), 1, size);

        credits = new EntityCustom("menu/Credits (selected).gif", (int) spotWidth, (int) (load.getY() + load.getHeight() + spacing), 1, size);
        creditsNot = new EntityCustom("menu/Credits (deselected).png", (int) spotWidth + 10, credits.getY(), 1, size);

        quit = new EntityCustom("menu/Quit (selected).gif", (int) spotWidth, (int) (credits.getY() + credits.getHeight() + spacing), 1, size);
        quitNot = new EntityCustom("menu/Quit (deselected).png", (int) spotWidth + 10, quit.getY(), 1, size);

        keys = Main.keys;
        gc = Main.gc;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    public void reset() {
        gc.restore();
        Main.pauseMenu.clearRect();
        Main.pauseMenu = new Pause();
    }

    @Override
    public void animate() {
        new AnimationTimer() {
            int selAmount = 5;
            EntityCustom current = newGame;
            int wait = 15;
            boolean waitON = false;

            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, Main.width * Main.scale, Main.height * Main.scale);
                if (stop) {
                    stop();
                }
                if (keys.getInput().contains("ENTER")) {
                    hitEnter();
                }
                if (!waitON) {
                    if ((keys.getInput().contains("S") && keys.getInput().contains("W")) || (keys.getInput().contains("UP") && keys.getInput().contains("DOWN"))) {

                    } else if (keys.getInput().contains("W")
                            || keys.getInput().contains("UP")) {
                        if (selection - 1 >= 0) {
                            selection--;
                        } else {
                            selection = selAmount - 1;
                        }
                        waitON = true;
                    } else if (keys.getInput().contains("S")
                            || keys.getInput().contains("DOWN")) {
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
                logo.draw(gc);

                //CONTINUE
                if (selection == 0) {
                    continueGame.draw(gc);
                } else {
                    continueGameNot.draw(gc);
                }
                //NEWGAME
                if (selection == 1) {
                    newGame.draw(gc);
                } else {
                    newGameNot.draw(gc);
                }
                //LOAD
                if (selection == 2) {
                    load.draw(gc);
                } else {
                    loadNot.draw(gc);
                }
                //CREDITS
                if (selection == 3) {
                    credits.draw(gc);
                } else {
                    creditsNot.draw(gc);
                }
                //QUIT
                if (selection == 4) {
                    quit.draw(gc);
                } else {
                    quitNot.draw(gc);
                }

            }
        }.start();

        Main.stage.show();
    }

    public void hitEnter() {
        if (selection == 0) {
            System.out.println("Continue not implemented!");
        }

        if (selection == 1) {
            if (!stop) {
                stop = true;
                gc.save();
                Main.test.animate();
                
            }
        }

        if (selection == 2) {
            System.out.println("Load not implemented!");
        }
        if (selection == 3) {
            if (!stop) {
                stop = true;
                gc.save();
                Main.credit.animate();
            }
        }
        if (selection == 4) {
            Main.stage.close();
        }
    }

}
