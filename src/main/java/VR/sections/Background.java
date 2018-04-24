package VR.sections;

import VR.Main;
import VR.entities.EntityCustom;
import javafx.scene.canvas.GraphicsContext;

public class Background {

    private EntityCustom background;
    private EntityCustom backgroundFollow;
    private GraphicsContext back;

    //Background that just moves! Usable for menu and ingame!
    public Background(String location) {
        background = new EntityCustom(location);
        background.setDir(EntityCustom.Dir.LEFT);
        background.setSpeed(1);
        background.setY(-300);
        backgroundFollow = new EntityCustom(location);
        backgroundFollow.setDir(EntityCustom.Dir.LEFT);
        backgroundFollow.setSpeed(1);
        backgroundFollow.setXY(background.getX()+ background.getWidth(), background.getY());
        back = Main.background;
    }
    
    public void setGC(GraphicsContext gc) {
        this.back = gc;
    }
    
    public void draw() {
        back.clearRect(0, 0, Main.width * Main.scale, Main.height * Main.scale);
        if (background.getX()+background.getWidth() < 0) {
            background.setX(backgroundFollow.getX() + backgroundFollow.getWidth());
        }
        if (backgroundFollow.getX()+backgroundFollow.getWidth() < 0) {
            backgroundFollow.setX(background.getX() + background.getWidth());
        }
        background.draw(back);
        backgroundFollow.draw(back);
        
    }

}
