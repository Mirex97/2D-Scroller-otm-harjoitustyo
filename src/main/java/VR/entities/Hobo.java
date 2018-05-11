package VR.entities;

import VR.Main;
import VR.gui.Timer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import tiled.core.Map;

public class Hobo extends Animate {

    private Image image;
    private final Area area;
    private final int distance = 600;
    private Timer tallyCounter;
    private Random randomizer;
    private boolean movement;

    public Hobo(Map map, Area area, double x, double y) {
        super(map, area);
        this.area = area;
        this.setXY(x, y);
        setImage("/characters/hobo/Hobo.png");
        setCollision(x, y);
        middleX = image.getWidth() / 2;
        middleY = image.getHeight() / 2;
        this.setWalkingSpeed(0.5);
        randomizer = new Random();
        tallyCounter = new Timer(2);
        movement = false;
    }

    public void move(Player player, Area instructions, long l) {
        if (!movement) {
            if (randomizer.nextInt(100) < 3) {
                if (randomizer.nextInt(100) < 10) {
                    action = Action.JUMPING;
                }
                movement = true;
                tallyCounter.setOn(true);
            }
        } else {
            tallyCounter.update(l);
            if (tallyCounter.getEnded()) {
                movement = false;
                tallyCounter.resetAll();
            }
        }

        if (action != action.JUMPING) {
            down();
        }
        if (action == Action.JUMPING) {
            up(false);
        }

        if (movement) {
            if (player.getMiddleX() > (this.x - distance) && player.getMiddleX() < this.x) {
                left();
            }
            if (player.getMiddleX() < (distance + this.x + this.width) && player.getMiddleX() > (this.x + this.width)) {
                right();
            }

        }

        reloadCollision();
        updateMiddles();
    }

    public void draw() {
        Main.gc.drawImage(image, x, y);
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
