
package VR.mapitems;

import VR.Main;
import VR.entities.EntitySuper;
import java.awt.Rectangle;
import java.util.Random;
import javafx.scene.image.Image;


public class Coin extends EntitySuper {
    private int points;
    private Rectangle.Double collision;
    private Image image;
    private boolean remove;
    private int value;
    
    public Coin(double x, double y)  {
        super(x, y);
        remove = false;
        image = Main.images.getCoin();
        collision = new Rectangle.Double(x, y, image.getWidth(), image.getHeight());
        Random rand = new Random();
        //Coin is worth normally 3 but might get randomly extra value!
        value = 3 + rand.nextInt(5);
    }
    
    public int getValue() {
        return value;
    }    
    
    
    public void setRemove(boolean remove) {
        this.remove = remove;
    }
    
    public boolean getRemove() {
        return remove;
    }
    
    public Rectangle.Double getCollision() {
        return collision;
    }
    
    public void draw() {
        Main.gc.drawImage(image, x, y);
    }
}
