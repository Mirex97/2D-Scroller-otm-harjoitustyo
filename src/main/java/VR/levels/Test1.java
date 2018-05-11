package VR.levels;

import VR.Main;
import VR.camera.Camera;
import VR.entities.Animate;
import VR.entities.Conductor;
import VR.entities.EntityCustom;
import VR.entities.EntitySuper;
import VR.entities.Hobo;
import VR.mapitems.Coin;
import VR.entities.Player;
import VR.gui.Gui;
import VR.gui.Pause;
import VR.gui.Score;
import VR.handlers.CutsceneObjects;
import VR.handlers.MapObjecthandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import VR.handlers.Messagehandler;
import VR.mapitems.Cutscene;
import VR.mapitems.Text;
import VR.sections.Section;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Test1 extends MapSuper implements Section {

    private Camera camera;
    private GraphicsContext gc;
    private Gui gui;
    private Pause pause;
    private Player player;
    private int timelimit;
    private tiled.core.Map map;
    private MapObjecthandler objects;
    private CutsceneObjects cutscenes;
    private ArrayList<Hobo> hobos;
    private Messagehandler texts;
    private boolean talking = false;
    private boolean stop;
    private Score score;
    private boolean victory = false;
    private boolean lose = false;
    private String loseType = "OUT";
    private String location;

    private ArrayList<Conductor> conductors;

    public Test1(String location) throws Exception {
        super(location);

        this.location = location;
        map = this.getMap();
        camera = new Camera(map, 16);

        pause = Main.pauseMenu;
        cutscenes = new CutsceneObjects(map);
        objects = new MapObjecthandler(map);
        texts = new Messagehandler(map);

        hobos = new ArrayList<>();
        for (EntitySuper spawn : objects.getHobos()) {
            hobos.add(new Hobo(map, this.getBoundary(), spawn.getX(), spawn.getY()));
        }

        conductors = new ArrayList<>();
        for (EntitySuper spawn : objects.getConductors()) {
            conductors.add(new Conductor(map, this.getBoundary(), spawn.getX(), spawn.getY()));
        }

        gc = Main.gc;
        gui = new Gui(60);
        player = new Player(map, this.getBoundary());
        player.setSpawnpoint((int) objects.getPlayerX() * 2, (int) objects.getPlayerY() * 2);

        score = new Score();
    }

    public Cutscene checkCutCollision() {
        for (Cutscene cut : cutscenes.getCuts()) {
            if (cut.getCollision().intersects(player.getCollision())) {
                return cut;
            }
        }
        return null;
    }

    public Text checkTextCollision() {
        for (Text text : texts.getTexts()) {
            if (text.getCollision().intersects(player.getCollision())) {
                return text;
            }
        }
        return null;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean getStop() {
        return this.stop;
    }

    @Override
    public void animate() {

        camera.drawImages("Images");
        camera.drawImages("FrontGround");
        camera.drawImages("FrontImages");
        
        new AnimationTimer() {
            int waitTime = 0;
            Text text = null;
            Cutscene cut = null;
            boolean written = false;
            int waitASecond = 30;
            boolean once = false;

            @Override
            public void handle(long l) {

                if (Main.delta.deltaTime(l)) {

                    if (victory) {
                        this.stop();
                        gui.clearRect();
                        Main.pauseMenu.clearRect();
                        Main.pauseMenu = new Pause();
                        try {
                            Main.test = new Test1(location);
                        } catch (Exception e) {
                            System.out.println("Did not work! Test1!");
                            Main.login.error();
                        }
                        Main.cutscenes.getEnd().animate();
                    }
                    if (lose) {
                        if (waitASecond > 0) {
                            waitASecond--;
                        } else {

                            this.stop();
                            gui.clearRect();
                            Main.pauseMenu.clearRect();
                            Main.pauseMenu = new Pause();
                            try {
                                Main.test = new Test1(location);
                            } catch (Exception e) {
                                System.out.println("Did not work! Test1!");
                                Main.login.error();
                            }
                            Main.cutscenes.getLose(loseType).animate();
                        }
                    }

                    if (pause.getStop()) {
                        this.stop();
                        gui.clearRect();
                        Main.pauseMenu.clearRect();
                        Main.pauseMenu = new Pause();
                        try {
                            Main.test = new Test1(location);
                        } catch (Exception e) {
                            System.out.println("Did not work! Test1!");
                            Main.login.error();
                        }
                        Main.backGround.setGC(Main.background);
                        Main.menu.reset();
                        Main.menu.setStop(false);
                        Main.menu.animate();
                        Main.pauseMenu.setPaused(false);
                        Main.bgMusic.changeClip(Main.musicloader.getMusic("MainEnd"));
                        Main.bgMusic.play();
                    }

                    if (!pause.getPaused()) {
                        gc.clearRect((0 - gc.getTransform().getTx()), (0 - gc.getTransform().getTy()), Main.width * Main.scale, Main.height * Main.scale);
                        Main.backGround.draw();
                        if (lose) {
                            Main.gc.setFill(Color.RED);
                            Main.gc.fillRect((0 - gc.getTransform().getTx()), (0 - gc.getTransform().getTy()), Main.width * Main.scale, Main.height * Main.scale);
                            Main.gc.setFill(Color.WHITE);
                        }
                        if (cut == null && !lose) {
                            if (!talking) {
                                if (!player.getConfused()) {
                                    player.move();
                                } else {
                                    player.moveCUT(Animate.Dir.DOWN);
                                    player.setAction(Animate.Action.CONFUSED);
                                }
                                for (Hobo hobo : hobos) {
                                    hobo.move(player, null, l);
                                }
                                if (gui.getTimer().getEnded()) {
                                    for (Conductor con : conductors) {
                                        con.move(player, null, l);
                                    }
                                }
                            } else {
                                player.moveCUT(Animate.Dir.DOWN);
                            }
                        } else {
                            player.moveCUT(Animate.Dir.DOWN);
                        }
                        
                        if (objects.getKillZone().intersects(player.getCollision())) {
                            loseType = "OUT";
                            lose = true;
                        }

                        camera.moveXY(player.getMiddleX() - (Main.width / 2), player.getMiddleY() - (Main.height / 2));
                        if (!lose) {
                            camera.drawImages("Images");
                        }
                        player.draw(l);
                        for (Hobo hobo : hobos) {
                            if (player.getCollision().intersects(hobo.getCollision())) {
                                if (!player.getConfTimer().getOn()) {
                                    if (player.is_meterDown()) {
                                        player.setConfused(true);
                                        player.reset_confusionMeter();
                                        score.addScore(-10);
                                    } else {
                                        player.dec_confusionMeter();
                                    }

                                }
                            }
                            hobo.draw();
                        }

                        if (gui.getTimer().getEnded()) {
                            if (!once) {
                                once = true;
                                ArrayList<String> meh = new ArrayList<>();
                                ArrayList<String> meh1 = new ArrayList<>();
                                meh.add("Smurfradar");
                                meh1.add("Watch out! Bogies inbound!");
                                Text texti = new Text("RUN", meh, meh1, null, "nonstop");
                                texti.setTime(60);
                                gui.write(texti);
                                text = texti;
                                written = true;
                                
                                
                                Main.bgMusic.changeClip(Main.musicloader.getMusic("Cancelled"));
                                Main.bgMusic.play();
                            }
                            for (Conductor con : conductors) {
                                if (player.getCollision().intersects(con.getCollision())) {
                                    if (!player.getConfTimer().getOn()) {
                                        if (player.is_meterDown()) {
                                            lose = true;
                                            loseType = "CAUGHT";
                                            player.reset_confusionMeter();
                                            score.addScore(-80);
                                        } else {
                                            player.dec_confusionMeter();
                                        }

                                    }
                                }
                                con.draw();
                            }
                        }

                        if (objects.getVictory().intersects(player.getCollision())) {
                            victory = true;
                        }

                        if (objects.getLifts().intersects(player.getCollision())) {
                            player.setSuperJump(true);
                        } else {
                            player.setSuperJump(false);
                        }

                        Iterator<Coin> iter = objects.getCoins().listIterator();

                        while (iter.hasNext()) {
                            Coin coin = iter.next();
                            if (player.getCollision().intersects(coin.getCollision()) && !coin.getDestroy()) {
                                score.addScore(coin.getValue());
                                coin.destroy();
                            }

                            if (coin.getRemove()) {
                                iter.remove();
                            } else {
                                coin.draw();
                            }

                        }

                        if (!lose) {
                            camera.drawImages("FrontGround");
                            if (objects.getHide().intersects(player.getCollision())) {
                                camera.drawImages("FrontImages");
                            }
                        }
                        gui.draw(l);
                        score.updateScore();
                        gui.drawScore(score);
                        if (cut != null) {
                            if (!cut.getFinished()) {
                                cut.play(player, gui);
                            } else {
                                cutscenes.removeCut(cut);
                                cut = null;

                            }
                        }
                        if (text != null) {
                            if (gui.getText() == null) {
                                if (!written) {
                                    if (!text.getType().equals("nonstop")) {
                                        talking = true;
                                    } else {
                                        text.setTime(100);
                                    }
                                    written = true;
                                    gui.write(text);

                                } else {

                                    written = false;
                                    texts.removeText(text);
                                    text = null;
                                    talking = false;
                                }
                            } else {
                                gui.drawText();
                            }

                        }
                        if (Main.keys.getInput().contains("ESCAPE")) {
                            pause.setPaused(true);
                        }
                        if (Main.keys.getInput().contains("ADD")) {
                            camera.zoom(Camera.Direction.UP);
                        }
                        if (Main.keys.getInput().contains("SUBTRACT")) {
                            camera.zoom(Camera.Direction.DOWN);
                        }

                        if (cut == null) {
                            cut = checkCutCollision();
                        }
                        if (text == null) {
                            text = checkTextCollision();
                        }

                    } else {
                        pause.draw();
                    }
                }

            }
        }.start();

        Main.stage.show();
    }
}
