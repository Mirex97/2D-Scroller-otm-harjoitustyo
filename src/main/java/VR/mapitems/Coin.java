
package VR.mapitems;

import VR.Main;
import VR.entities.EntitySuper;
import java.awt.Rectangle;
import javafx.scene.image.Image;


public class Coin extends EntitySuper {
    private int points;
    private Rectangle collision;
    private Image image;
    
    public Coin(int x, int y)  {
        super(x, y);
        image = Main.images.getCoin();
    }
    
    public void draw() {
        Main.gc.drawImage(image, x, y);
    }
}
