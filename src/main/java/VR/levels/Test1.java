package VR.levels;

import VR.Main;
import VR.camera.Camera;
import VR.entities.EntitySuper;
import VR.entities.Hobo;
import VR.mapitems.Coin;
import VR.entities.Player;
import VR.gui.Gui;
import VR.gui.Pause;
import VR.gui.Score;
import VR.handlers.MapObjecthandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import VR.handlers.Messagehandler;
import VR.mapitems.Text;
import VR.sections.Section;
import java.util.ArrayList;
import java.util.Iterator;

public class Test1 extends MapSuper implements Section {

    private Camera camera;
    private GraphicsContext gc;
    private Gui gui;
    private Pause pause;
    private Player player;
    private int timelimit;
    private tiled.core.Map map;
    private MapObjecthandler objects;
    private ArrayList<Hobo> hobos;
    private Messagehandler texts;
    private boolean talking = false;
    private boolean stop;
    private Score score;

    public Test1() throws Exception {
        super("Helsinki.tmx");
        map = this.getMap();
        camera = new Camera(map, 16);

        pause = Main.pauseMenu;
        objects = new MapObjecthandler(map);
        texts = new Messagehandler(map);

        //These need to be organized better into the Super class!
        //Once I have more levels then this is necessary!
        hobos = new ArrayList<>();
        for (EntitySuper spawn : objects.getHobos()) {
            hobos.add(new Hobo(map, this.getBoundary(), spawn.getX(), spawn.getY()));
        }

        gc = Main.gc;
        gui = new Gui(300);
        player = new Player(map, this.getBoundary());
        player.setSpawnpoint((int) objects.getPlayerX() * 2, (int) objects.getPlayerY() * 2);

        score = new Score();
    }

    public Text checkCollision() {
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

    @Override
    public void animate() {
        new AnimationTimer() {
            int waitTime = 0;
            Text text = null;
            boolean written = false;

            @Override
            public void handle(long l) {

                if (Main.delta.deltaTime(l)) {

                    if (pause.getStop()) {
                        this.stop();
                        gui.clearRect();
                        Main.pauseMenu.clearRect();
                        Main.pauseMenu = new Pause();
                        try {
                            Main.test = new Test1();
                        } catch (Exception e) {
                            System.out.println("Did not work! Test1!");
                            Main.login.error();
                        }
                        Main.menu.reset();
                        Main.menu.setStop(false);
                        Main.menu.animate();
                        Main.pauseMenu.setPaused(false);
                        Main.bgMusic.changeClip(Main.musicloader.getMusic("MainEnd"));
                        Main.bgMusic.play();
                    }

                    if (!pause.getPaused()) {
                        gc.clearRect((0 - gc.getTransform().getTx()), (0 - gc.getTransform().getTy()), Main.width * Main.scale, Main.height * Main.scale);

                        if (!talking) {
                            //Do not move if talking!
                            player.move();
                            for (Hobo hobo : hobos) {
                                hobo.move(player, null);
                            }
                        }

                        camera.moveXY(player.getMiddleX() - (Main.width / 2), player.getMiddleY() - (Main.height / 2));
                        camera.draw("Background");
                        player.draw();
                        for (Hobo hobo : hobos) {
                            hobo.draw();
                        }

                        Iterator<Coin> iter = objects.getCoins().listIterator();

                        //Need to use sprite class for coins! Then the coins dont just disappear when collected!
                        //This also allows to make floating scores when drawing the coin!
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

                        camera.draw("Frontground");
                        gui.draw(l);
                        score.updateScore();
                        gui.drawScore(score);
                        if (text != null) {
                            if (gui.getText() == null) {
                                if (!written) {
                                    written = true;
                                    gui.write(text);
                                    talking = true;
                                } else {
                                    //RESET
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

                        text = checkCollision();

                    } else {
                        pause.draw();
                    }
                }

            }
        }.start();

        Main.stage.show();
    }
}
