package VR.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class EntityCustom extends EntitySuper {

    private int timer = 0;
    private boolean timerON = false;
    private String location;
    private Image image;
    private double speed;
    private boolean draw = true;

    public enum Dir {
        UP, DOWN, LEFT, RIGHT, STILL
    }
    private Dir direction = Dir.STILL;
    private double scale;
    private double width = 0;
    private double height = 0;
    private double opacity = 1;

    
    /*Create EntityCustom with specified location for image**/
    public EntityCustom(String location) {
        super(0, 0);
        this.location = location;
        setImage(location);
        scale = 1;
        speed = 1;
    }

    /**Create EntityCustom with specified location and x, y*/
    public EntityCustom(String location, double x, double y) {
        super(x, y);
        this.location = location;
        setImage(location);
        speed = 1;
        scale = 1;
    }

    //**Constructor for EntityCustom, Location for image location, x, y, speed, scale*/
    public EntityCustom(String location, double x, double y, double speed, double scale) {
        super(x, y);
        this.location = location;
        this.scale = scale;
        this.speed = speed;
        setImage(location);
    }

    /**Set timer on with specified time*/
    public void setTimer(int time) {
        this.timerON = true;
        this.timer = time;
    }
    
    /**Returns true or false if timer is on*/
    public boolean getTimer() {
        return this.timerON;
    }

    /**Sets opacity of image.*/
    public void setOpacity(double opacity) {
        this.opacity = opacity;
    }

    /**Gets opacity of image*/
    public double getOpacity() {
        return this.opacity;
    }

    /**Sets image as drawable or not*/
    public void setDraw(boolean set) {
        this.draw = set;
    }
    
    /**Gets if image is drawable*/
    public boolean getDraw() {
        return this.draw;
    }
    
    /**Get Width*/
    public double getWidth() {
        return width * scale;
    }

    /**Get Height*/
    public double getHeight() {
        return height * scale;
    }

    /**Get scale*/
    public double getScale() {
        return this.scale;
    }

    /**Get Speed*/
    public double getSpeed() {
        return this.speed;
    }

    /**Set Direction*/
    public void setDir(Dir dir) {
        this.direction = dir;
    }

    /**Set speed*/
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**Set scale, also reloads image*/
    public void setScale(double scale) {
        this.scale = scale;
        reloadImage();
    }

    /**Set image with new location*/
    public void setImage(String location) {
        if (width == 0 || height == 0) {
            image = new Image(location);
            width = image.getWidth();
            height = image.getHeight();
        }
        image = new Image(location, (width * scale), (height * scale), true, true);
    }
    
    
    /**Reloads image*/
    public void reloadImage() {
        image = new Image(location, (width * scale), (height * scale), true, true);
    }

    /**Draws image on GraphicsContext gc*/
    public void draw(GraphicsContext gc) {
        if (!timerON) {
            gc.setGlobalAlpha(opacity);
        } else {
            if (timer <= 0) {
                timerON = false;
            }
            timer -= 1;
        }
        switch (this.direction) {
            case UP:
                this.setY(this.getY() - speed);
                gc.drawImage(image, this.getX(), this.getY());
                break;
            case DOWN:
                this.setY(this.getY() + speed);
                gc.drawImage(image, this.getX(), this.getY());
                break;
            case LEFT:
                this.setX(this.getX() - speed);
                gc.drawImage(image, this.getX(), this.getY());
                break;
            case RIGHT:
                this.setX(this.getX() + speed);
                gc.drawImage(image, this.getX(), this.getY());
                break;
            default:
                gc.drawImage(image, this.getX(), this.getY());
                break;
        }
        gc.setGlobalAlpha(1.0);

    }

}
