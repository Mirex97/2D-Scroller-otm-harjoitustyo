package VR.levels;

import VR.Main;
import VR.camera.Camera;
import VR.entities.Player;
import VR.gui.Gui;
import VR.handlers.MapObjecthandler;
import VR.handlers.Messagehandler;
import VR.mapitems.Coin;
import VR.mapitems.Text;
import VR.sections.Section;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Credits extends MapSuper implements Section {

    private Camera camera;
    private GraphicsContext gc;
    private Gui gui;
    private Player player;
    private int timelimit;
    private tiled.core.Map map;
    private MapObjecthandler objects;
    private Messagehandler texts;
    private boolean talking = false;

    private boolean stop;

    public Credits() throws Exception {
        super("Credits.tmx");
        camera = new Camera(this.getMap(), 8);
        map = this.getMap();
        stop = false;
        objects = new MapObjecthandler(this.getMap());
        texts = new Messagehandler(this.getMap());

        gc = Main.gc;
        gui = new Gui();
        player = new Player(this.getMap(), this.getBoundary());
        player.setSpawnpoint((int) objects.getPlayerX() * 2, (int) objects.getPlayerY() * 2);
        player.setGravity(1, 0.05);
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Text checkCollision() {
        for (Text text : texts.getTexts()) {
            if (text.getCollision().intersects(player.getCollision())) {
                return text;
            }
        }
        return null;
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

                    gc.clearRect((0 - gc.getTransform().getTx()), (0 - gc.getTransform().getTy()), Main.width * Main.scale, Main.height * Main.scale);

                    if (!talking) {
                        
                        player.move();

                    }

                    camera.moveXY((int) player.getMiddleX() - (Main.width / 2), (int) player.getMiddleY() - (Main.height / 2));
                    camera.draw("Background");
                    player.draw(l);
                    for (Coin coin : objects.getCoins()) {
                        coin.draw();
                    }
                    camera.draw("Frontground");
                    gui.clearRect();
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
                        this.stop();
                        gui.clearRect();
                        try {
                            Main.credit = new Credits();
                        } catch (Exception e) {
                            System.out.println("did not work");
                            Main.login.error();
                        }
                        Main.menu.reset();
                        Main.menu.setStop(false);
                        Main.menu.animate();
                    }
                    if (Main.keys.getInput().contains("ADD")) {
                        camera.zoom(Camera.Direction.UP);
                    }
                    if (Main.keys.getInput().contains("SUBTRACT")) {
                        camera.zoom(Camera.Direction.DOWN);
                    }
                    if (text == null) {
                        text = checkCollision();
                    }
                }
            }
        }.start();

        Main.stage.show();
    }

}
