package VR.levels;

import VR.Main;
import VR.camera.Camera;
import VR.entities.Player;
import VR.gui.Gui;
import VR.gui.Pause;
import VR.sections.section;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Test1 extends MapSuper implements section {

    private Camera camera;
    private GraphicsContext gc;
    private Gui gui;
    private Pause pause;
    private Player player;
    private int timelimit;
    private tiled.core.Map map;

    public Test1() {
        super("src/main/resources/levels/Safezones/Helsinki.tmx");
        camera = new Camera(this.getMap(), 16);
        map = this.getMap();
        
        pause = Main.pauseMenu;

        gc = Main.gc;
        gui = new Gui(10);
        player = new Player(this.getMap(), camera, this.getBoundary());
        player.setSpawnpoint(100, 102);
    }

    @Override
    public void animate() {
        new AnimationTimer() {
            int waitTime = 0;
            @Override
            public void handle(long l) {
                if (!pause.getPaused()) {
                    player.move();
                    gc.clearRect((0 - gc.getTransform().getTx()), (0 - gc.getTransform().getTy()), Main.width * Main.scale, Main.height * Main.scale);
                    
                    camera.moveXY((int) player.getMiddleX() - (Main.width / 2), (int) player.getMiddleY() - (Main.height / 2));
                    camera.draw("Background");
                    player.draw();
                    camera.draw("Frontground");
                    gui.draw(l);
                    if (Main.keys.getInput().contains("ESCAPE")) {
                        if (waitTime > 15) {
                            pause.setPaused(true);
                            waitTime = 0;
                        } else {
                            waitTime++;
                        }
                        
                    } else {
                        waitTime = 0;
                    }
                    if (Main.keys.getInput().contains("RIGHT")) {
                        camera.move(Camera.Direction.RIGHT);
                    }
                    if (Main.keys.getInput().contains("LEFT")) {
                        camera.move(Camera.Direction.LEFT);
                    }
                    if (Main.keys.getInput().contains("ADD")) {
                        camera.move(Camera.Direction.UP);
                    }
                    if (Main.keys.getInput().contains("SUBTRACT")) {
                        camera.move(Camera.Direction.DOWN);
                    }
                } else {
                    pause.draw();
                }

            }
        }.start();

        Main.stage.show();
    }
}
