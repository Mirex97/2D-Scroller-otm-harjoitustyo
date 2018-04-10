package VR.sections;

import VR.Main;
import VR.entities.EntityCustom;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class CompanyFade implements section {

    private GraphicsContext gc;
    private EntityCustom companyName;

    public CompanyFade() {
        gc = Main.gc;
        companyName = new EntityCustom("menu/Company.png", 0, 0, 1, 4);
        companyName.setX((Main.width / 2) - (int) (companyName.getWidth() / 2));
        companyName.setY((Main.height / 2) - (int) (companyName.getHeight() / 2));
        companyName.setDir(EntityCustom.Dir.STILL);
    }

    @Override
    public void animate() {
        
        new AnimationTimer() {
            double appear = 0.0;
            int hold = 60;
            boolean fade = false;
            boolean finished = false;

            @Override
            public void handle(long l) {
                gc.clearRect(0, 0, Main.width * Main.scale, Main.height * Main.scale);
                if (finished) {
                    Main.intro.animate();
                    stop();
                }
                if (companyName.getDraw()) {
                    companyName.setOpacity(appear);
                    companyName.draw(gc);
                }

                if (appear < 1 && fade == false) {
                    appear += 0.01;
                } else {
                    fade = true;
                }
                if (fade) {
                    if (hold <= 0) {
                        if (appear > 0 && fade == true) {
                            appear -= 0.01;
                        } else {
                            finished = true;
                        }
                    } else {
                        hold--;
                    }
                }

            }

        }.start();

        Main.stage.show();

    }

}
