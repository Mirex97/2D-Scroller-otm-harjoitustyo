package VR.entities;

import VR.Main;
import VR.camera.Camera;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.TileLayer;

public final class Player extends Animate {

    private ArrayList<String> input;
    private Image image;
    private final Area area;

    private boolean freeRoam;

    public Player(Map map, Area area) {
        super(map, area);
        this.area = area;

        input = Main.keys.getInput();
        setSpawnpoint(10, 10);
        setImage("/characters/player/turnaround.gif");
        setCollision(x, y);
        middleX = image.getWidth() / 2;
        middleY = image.getHeight() / 2;
    }

    public void move() {
        if (input.contains("W") && action != action.FALLING) {
            action = action.JUMPING;
        }
        if (action != action.JUMPING) {
            down();
        }
        if (action == Action.JUMPING) {
            up();
        }

        if (input.contains("A") && !input.contains("D")) {
            if (input.contains("SHIFT")) {
                leftRUN();
            } else {
                left();
            }
        }
        if (input.contains("D") && !input.contains("A")) {
            if (input.contains("SHIFT")) {
                rightRUN();
            } else {
                right();
            }
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
        collision.setBounds(x, y, (int) image.getWidth(), (int) image.getHeight());
    }

    public void setCollision(int x, int y) {
        collision = new Rectangle();
        collision.setBounds(x, y, (int) image.getWidth(), (int) image.getHeight());

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
