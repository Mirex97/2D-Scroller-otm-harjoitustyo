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
    
    public Test1() throws Exception {
        super("untitled.tmx");
        map = this.getMap();
        camera = new Camera(map, 16);
        
        pause = Main.pauseMenu;
        cutscenes = new CutsceneObjects(map);
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
    
    @Override
    public void animate() {
        //Camera test!
        new AnimationTimer() {
            int waitTime = 0;
            Text text = null;
            Cutscene cut = null;
            boolean written = false;
            
            @Override
            public void handle(long l) {
                
                if (Main.delta.deltaTime(l)) {
                    
                    
                    if (victory) {
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
                        Main.cutscenes.getEnd().animate();
                    }
                    
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
                        if (cut == null) {
                            if (!talking) {
                                //Do not move if talking!
                                player.move();
                                for (Hobo hobo : hobos) {
                                    hobo.move(player, null);
                                }
                            }
                        }
                        
                        camera.moveXY(player.getMiddleX() - (Main.width / 2), player.getMiddleY() - (Main.height / 2));
                        camera.drawImages("Images");
//                        camera.draw("Background");
                        player.draw();
                        for (Hobo hobo : hobos) {
                            hobo.draw();
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
                        
                        if (cut == null) {
                            cut = checkCutCollision();
                        }
                        text = checkTextCollision();
                        
                    } else {
                        pause.draw();
                    }
                }
                
            }
        }.start();
        
        Main.stage.show();
    }
}
