package VR.entities;

//Enemy who walks slowly towards player! Only moves when player is at certain range! Otherwise idles!
import VR.Main;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import tiled.core.Map;

// Might jump!
//If player touches him, he'll be stunned for a moment.
public class Hobo extends Animate {

    private Image image;
    private final Area area;

    public Hobo(Map map, Area area, double x, double y) {
        super(map, area);
        this.area = area;
        this.setXY(x, y);
        setImage("/characters/hobo/Hobo.png");
        setCollision(x, y);
        middleX = image.getWidth() / 2;
        middleY = image.getHeight() / 2;
    }

    public void move(Player player, Area instructions) { //Unique for hobo
        //Instructions is created from the tilemap objects and contains
        //spots where character has to jump for example!
        //if ([INSIDE AREA]) {
        //action = action.JUMPING;
        //}

        if (action != action.JUMPING) {
            down();
        }
        if (action == Action.JUMPING) {
            up(false);
        }
        if (player.getMiddleX() < this.middleX) {
            left();
        }
        if (player.getMiddleX() > this.middleX) {
            right();
        }

        reloadCollision();
        updateMiddles();
    }

    public void draw() {
        //Need images from sprite class! And get action and direction from super!
//        System.out.println(super.face);
//        System.out.println(super.action);
        Main.gc.drawImage(image, x, y);
//        Main.gc.fillRect(collision.x, collision.y, collision.width, collision.height);
    }

    public void setSpawnpoint(int x, int y) {
        this.setXY(x, y);
    }

    public void updateMiddles() {
        this.middleX = this.getX() + (image.getWidth() / 2);
        this.middleY = this.getY() + (image.getHeight() / 2);
    }

    public double getMiddleX() {
        return this.middleX;
    }

    public double getMiddleY() {
        return this.middleY;
    }

    public void reloadCollision() {
        collision.setRect(x, y, image.getWidth(), image.getHeight());
    }

    public void setCollision(double x, double y) {
        collision = new Rectangle2D.Double();
        collision.setRect(x, y, image.getWidth(), image.getHeight());

    }

    public void setImage(String location) {
        if (width == 0 || height == 0) {
            image = new Image(location);
            width = image.getWidth() * scale;
            height = image.getHeight() * scale;
        }
        image = new Image(location, (width), (height), true, false);
    }

    public javafx.scene.image.Image createImage(java.awt.Image image) throws Exception {
        if (!(image instanceof RenderedImage)) {
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            image = bufferedImage;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "png", out);
        out.flush();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return new javafx.scene.image.Image(in);
    }
}
