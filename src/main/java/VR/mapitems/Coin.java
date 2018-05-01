package VR.mapitems;

import VR.Main;
import VR.entities.EntitySuper;
import VR.entities.Sprite;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Coin extends EntitySuper {

    private static final int ROTATE = 0;
    private static final int DESTROY = 1;
    private int points;
    private Rectangle.Double collision;
    private ArrayList<Image[]> frames;
    private boolean destroy;
    private boolean remove;
    private int value;
    private double floatingValue;
    private Sprite sprite;

    public Coin(double x, double y) {
        super(x, y);
        floatingValue = 0;
        destroy = false;
        remove = false;
        sprite = new Sprite();
        sprite.setDelay(100);
        frames = Main.images.getCoin();
        sprite.setFrames(frames.get(ROTATE));

        collision = new Rectangle.Double(x, y, 32, 32);
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

    public boolean getDestroy() {
        return destroy;
    }

    public Rectangle.Double getCollision() {
        return collision;
    }

    public void destroy() {
        this.sprite.setFrames(frames.get(DESTROY));
        this.sprite.setLeave();
        this.destroy = true;
    }

    public void draw() {
        if (this.destroy) {
            Main.gc.setFont(Font.font("Impact", 20));
            Main.gc.fillText("" + value, x + 16, y + 16 - floatingValue);
            floatingValue++;

            if (this.sprite.hasPlayedOnce()) {
                this.remove = true;
            } else {
                this.sprite.update();
            }
        } else {
            this.sprite.update();
        }
        if (!this.remove) {
            Main.gc.drawImage(this.sprite.getImage(), x, y);
        }

    }
}
