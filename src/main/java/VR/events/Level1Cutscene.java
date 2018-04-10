
package VR.events;

import VR.Main;
import VR.entities.EntityCustom;
import VR.sections.section;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public class Level1Cutscene implements section {

    private GraphicsContext gc;
    private EntityCustom hahmo;
    private Stage stage;
    private boolean ready;

    public Level1Cutscene(Stage stage, GraphicsContext gc) {
        ready = false;
        this.stage = stage;
        this.gc = gc;
        hahmo = new EntityCustom("characters/player/turnaround.gif", 100, 100, 1, 3);
        hahmo.setDir(EntityCustom.Dir.STILL);
    }
    
    public boolean getReady() {
        return ready;
    }

    @Override
    public void animate() {
        new AnimationTimer() {
            int time = 0;

            @Override
            public void handle(long currentNanoTime) {
                gc.clearRect(0, 0, 1024, 768);
                if (time < 500) {
                    hahmo.draw(gc);
                    time++;
                } else {
                    ready = true;
                    Main.menu.animate();
                    stop();
                }
            }

        }.start();
        
        stage.show();

    }

}
