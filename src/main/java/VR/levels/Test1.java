package VR.levels;

import VR.Main;
import VR.camera.Camera;
import VR.entities.Hobo;
import VR.mapitems.Coin;
import VR.entities.Player;
import VR.gui.Gui;
import VR.gui.Pause;
import VR.handlers.MapObjecthandler;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import VR.handlers.Messagehandler;
import VR.mapitems.Text;
import VR.sections.Section;

public class Test1 extends MapSuper implements Section {

    private Camera camera;
    private GraphicsContext gc;
    private Gui gui;
    private Pause pause;
    private Player player;
    private int timelimit;
    private tiled.core.Map map;
    private MapObjecthandler objects;
    private Messagehandler texts;
    private boolean talking = false;
    private Hobo hobo;
    private boolean stop;

    public Test1() throws Exception {
        super("Helsinki.tmx");
//        super("levels/Helsinki.tmx", "levels/Helsinkitileset.tsx", "levels/tileset.png");
        camera = new Camera(this.getMap(), 16);
        map = this.getMap();
        pause = Main.pauseMenu;
        objects = new MapObjecthandler(this.getMap());
        texts = new Messagehandler(this.getMap());
        hobo = new Hobo(this.getMap(), this.getBoundary(), 100, 100);

        gc = Main.gc;
        gui = new Gui(300);
        player = new Player(this.getMap(), this.getBoundary());
        player.setSpawnpoint((int) objects.getPlayerX() * 2, (int) objects.getPlayerY() * 2);
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
                if (pause.getStop()) {
                    this.stop();
                    gui.clearRect();
                    Main.pauseMenu.clearRect();
                    Main.pauseMenu = new Pause();
                    try {
                        Main.test = new Test1();
                    } catch (Exception e) {
                        System.out.println("did not work!");
                        Main.login.error();
                    }
                    Main.menu.reset();
                    Main.menu.setStop(false);
                    Main.menu.animate();
                    Main.pauseMenu.setPaused(false);
                }

                if (!pause.getPaused()) {
                    gc.clearRect((0 - gc.getTransform().getTx()), (0 - gc.getTransform().getTy()), Main.width * Main.scale, Main.height * Main.scale);

                    if (!talking) {
                        //Do not move if talking!
                        player.move();
                        hobo.move(player, null);

                    }

                    camera.moveXY((int) player.getMiddleX() - (Main.width / 2), (int) player.getMiddleY() - (Main.height / 2));
                    camera.draw("Background");
                    player.draw();
                    hobo.draw();
                    for (Coin coin : objects.getCoins()) {
                        coin.draw();
                    }
                    camera.draw("Frontground");
                    gui.draw(l);
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
                        camera.move(Camera.Direction.UP);
                    }
                    if (Main.keys.getInput().contains("SUBTRACT")) {
                        camera.move(Camera.Direction.DOWN);
                    }

                    text = checkCollision();

                } else {
                    pause.draw();
                }

            }
        }.start();

        Main.stage.show();
    }
}
