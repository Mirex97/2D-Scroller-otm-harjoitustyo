package VR.sections;

import VR.Main;
import static VR.Main.bgMusic;
import VR.entities.EntityCustom;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import VR.sections.Section;
import javafx.scene.paint.Color;

public class CompanyFade implements Section {

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
        bgMusic.play();

        new AnimationTimer() {
            double appear = 0.0;
            int hold = 60;
            boolean fade = false;
            boolean finished = false;

            @Override
            public void handle(long l) {
                if (Main.delta.deltaTime(l)) {
                    if (!bgMusic.isRunning()) {
                        Main.bgMusic.changeClip(Main.musicloader.getMusic("MainMiddle"));
                    }
                    bgMusic.play();
                    gc.clearRect(0, 0, Main.width * Main.scale, Main.height * Main.scale);
                    if (finished) {
                        Main.intro.animate();
                        stop();
                    }
                    if (companyName.getDraw()) {
                        companyName.setOpacity(appear);
                        gc.setFill(Color.WHITE);
                        gc.setGlobalAlpha(appear);
                        gc.fillRect(0, 0, Main.width * Main.scale, Main.height * Main.scale);
                        gc.setGlobalAlpha(1.0);
                        companyName.draw(gc);
                    }

                    if (appear < 1 && fade == false) {
                        appear += 0.01;
                    } else {
                        fade = true;
                    }
                    if (fade) {
                        Main.backGround.draw();
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
            }

        }.start();

        Main.stage.show();

    }

}
