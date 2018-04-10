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

public final class Player extends EntitySuper {

    private Rectangle collision;
    private double maxSpeed; //minSpeed = 0 = action.Still;
    private Action action;
    private Facing face;
    private Momentum momentum;
    private final boolean animationPlay; //Pitää saada jaettua hahmon framet!
    private final int jumpSpeed = 4; //Jos pelaaja saavuttaa maximi korkeuden niin alkaa pudota!
    private final int runningSpeed = 6; //Running speed!
    private final int walkingSpeed = 3; //Walking speed!
    private final int fallingSpeed = 5; //Get gravitation from map!
    private int currentSpeed = 5; //Current moving speed!

    //Amplifiers for jumping, falling and walking/running!
    private double jumpAmplifier = 0;
    private double jumpArk = 0.3;
    private double jumpArkRelease = 0.2;

    private double fallAmplifier = 0;
    private double fallStart = 0.2;
    private double moveAmplifier = 1;
    private double maxMoveAmp = 7;
    private double moveArk = 0.2;

    boolean releasedJump = false;

    private double WIDTH = 0;
    private double HEIGHT = 0;
    private double scale = 2;

    private double middleX;
    private double middleY;

    private ArrayList<String> input;
    private Image image;
    private Camera camera;
    private final Area area;

    private boolean freeRoam;

    public enum Action {
        IDLE, WALKING, RUNNING, JUMPING, FALLING
    }

    //Enum joka määrittää mihin suuntaan pelaaja katsoo. Tämä on defaulttina oikealle!
    public enum Facing {
        LEFT, RIGHT
    }

    //Taas enumi!
    public enum Momentum {
        LEFT, STILL, RIGHT
    }

    public enum Dir {
        UP, DOWN, LEFT, RIGHT
    }

    public Player(Map map, Camera camera, Area area) {
        super(0, 0);

        this.camera = camera;
        this.area = area;
        animationPlay = false;
        action = Action.FALLING;
        face = Facing.RIGHT;
        momentum = Momentum.STILL;
        input = Main.keys.getInput();
        setSpawnpoint(10, 10);
        setImage("/characters/player/turnaround.gif");
        setCollision(x, y);
        middleX = image.getWidth() / 2;
        middleY = image.getHeight() / 2;
    }

    public void down() {
        
        if (collides(Dir.DOWN)) {
            fallAmplifier = 0;
            action = action.IDLE;
        } else {
            fallAmplifier += this.fallStart;
            this.setY(y + (this.fallingSpeed * (int) fallAmplifier));
            action = action.FALLING;
        }

    }

    public void up() {
        if (this.releasedJump) {
            jumpAmplifier -= this.jumpArkRelease;
            if (jumpAmplifier <= 0) {
                jumpAmplifier = 0;
                releasedJump = false;
                action = Action.FALLING;
            }
        } else {
            jumpAmplifier += this.jumpArk;
            if (jumpAmplifier >= jumpSpeed) {
                releasedJump = true;
            }
        }

        if (collides(Dir.UP)) {
            Area collided = new Area(collision);
            collided.intersect(area);
            this.setY((int) collided.getBounds().getMaxY());
            jumpAmplifier = 0;
            action = action.FALLING;
        } else {
            action = action.JUMPING;
            if (jumpAmplifier <= 0) {
                action = Action.FALLING;
            }
            this.setY(y - (this.jumpSpeed * (int) jumpAmplifier));
        }
    }

    public void left() {
        if (action != Action.JUMPING && action != Action.FALLING) {
            if (input.contains("SHIFT")) {
                action = Action.RUNNING; //ONLY FOR ANIMATION!
            } else {
                action = Action.WALKING; //ONLY FOR ANIMATION!
            }
        }

        if (collides(Dir.LEFT)) {
            momentum = Momentum.STILL;
        } else {
            if (face == Facing.RIGHT && action != Action.FALLING && action != Action.JUMPING) {
                face = Facing.LEFT;
            }
            momentum = Momentum.LEFT;
            if (input.contains("SHIFT")) {
                this.setX(x - (runningSpeed));
            } else {
                this.setX(x - (walkingSpeed));
            }
        }

    }

    public void right() {
        if (action != Action.JUMPING && action != Action.FALLING) {
            if (input.contains("SHIFT")) {
                action = Action.RUNNING;
            } else {
                action = Action.WALKING;
            }
        }

        if (collides(Dir.RIGHT)) {
            momentum = Momentum.STILL;
        } else {
            if (face == Facing.LEFT && action != Action.FALLING && action != Action.JUMPING) {
                face = Facing.RIGHT;
            }
            momentum = Momentum.RIGHT;
            if (input.contains("SHIFT")) {
                this.setX(x + (runningSpeed));
            } else {
                this.setX(x + (walkingSpeed));
            }
        }
    }

    public void move() {
        if (action == Action.IDLE) {
            momentum = Momentum.STILL;
        }

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
            left();
        }
        if (input.contains("D") && !input.contains("A")) {
            right();
        }
        

        
        reloadCollision();
        updateMiddles();
    }

    public void draw() {
        Main.gc.drawImage(image, x, y);
//        Main.gc.fillRect(collision.x, collision.y, collision.width, collision.height);

    }

    public void setSpawnpoint(int x, int y) {
        this.setXY(x, y);
    }

    public void normalCollide() {

        if (area.intersects(collision)) {
            System.out.println("yep");
        }
    }

    public boolean collides(Dir direction) {
        Rectangle copy = collision.getBounds();

        if (direction == Dir.DOWN) {
            copy.setLocation((int) copy.getX(), (int) copy.getY() + 1);
            if (area.intersects(copy)) {
                Area collided = new Area(copy);
                collided.intersect(area);
                this.setY((int) collided.getBounds().getMinY() - (int) this.HEIGHT);
                return true;
            }
            return false;
        }
        if (direction == Dir.UP) {
            copy.setLocation((int) copy.getX(), (int) copy.getY() - this.jumpSpeed);
            if (area.intersects(collision)) {
                return true;
            }
            return false;
        }

        if (direction == Dir.LEFT) {
            copy.setLocation((int) copy.getX() - runningSpeed, (int) copy.getY());
            if (area.intersects(copy)) {
                return true;
            }
            return false;
        }
        if (direction == Dir.RIGHT) {
            copy.setLocation((int) copy.getX() + runningSpeed, (int) copy.getY());
            if (area.intersects(copy)) {
                return true;
            }
            return false;
        }

        return false;

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
        if (WIDTH == 0 || HEIGHT == 0) {
            image = new Image(location);
            WIDTH = image.getWidth() * scale;
            HEIGHT = image.getHeight() * scale;
        }
        image = new Image(location, (WIDTH), (HEIGHT), true, false);
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
