package VR.entities;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import tiled.core.Map;

public class Animate extends EntitySuper {

    protected Rectangle2D.Double collision;
    protected int jumpSpeed = 4;
    protected int runningSpeed = 6;
    protected double walkingSpeed = 3;
    protected int fallingSpeed = 5;

    protected double jumpAmplifier = 0;
    protected double jumpArk = 0.3;
    protected double jumpArkRelease = 0.2;
    protected double fallAmplifier = 0;
    protected double fallStart = 0.2;

    private boolean releasedJump = false;

    protected double width = 0;
    protected double height = 0;
    protected double scale = 2;

    protected Action action;
    protected Action previousAction;
    protected Facing face;
    protected Facing previousFace;
    private final Area area;

    protected double middleX;
    protected double middleY;
    
    public void setWalkingSpeed(double speed) {
        this.walkingSpeed = speed;
    }

    public enum Action {
        IDLE, WALKING, RUNNING, JUMPING, FALLING, CONFUSED
    }

    public enum Facing {
        LEFT, RIGHT
    }

    public enum Dir {
        UP, DOWN, LEFT, RIGHT
    }

    public Animate(Map map, Area area) {
        super(0, 0);
        this.area = area;
        action = Action.FALLING;
        face = Facing.RIGHT;
        previousAction = action;
        previousFace = face;

    }
    
    public void setGravity(int speed, double amplifier) {
        this.fallStart = amplifier;
        this.fallingSpeed = speed;
    }

    public Rectangle2D.Double getCollision() {
        return collision;
    }

    public void down() {

        if (collides(Dir.DOWN)) {
            fallAmplifier = 0;
            action = action.IDLE;
        } else {
            fallAmplifier += this.fallStart;
            this.setY(y + (this.fallingSpeed * fallAmplifier));
            action = action.FALLING;
        }

    }

    public void up(boolean superjump) {
        int superjumping = 3;
        if (superjump) {
            jumpSpeed += superjumping;
        }
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
        if (superjump) {
            jumpSpeed -= superjumping;
        }
    }
    

    public void left() {
        if (action != Action.JUMPING && action != Action.FALLING) {

            action = Action.WALKING;

        }

        if (!collides(Dir.LEFT)) {
            if (face == Facing.RIGHT && action != Action.FALLING
                    && action != Action.JUMPING) {
                face = Facing.LEFT;
            }
            this.setX(x - (walkingSpeed));
        }
    }

    public void leftRUN() {
        if (action != Action.JUMPING && action != Action.FALLING) {
            action = Action.RUNNING;
        }
        if (!collides(Dir.LEFT)) {
            if (face == Facing.RIGHT && action != Action.FALLING
                    && action != Action.JUMPING) {
                face = Facing.LEFT;
            }
            this.setX(x - (runningSpeed));
        }
    }

    public void right() {
        if (action != Action.JUMPING && action != Action.FALLING) {
            action = Action.WALKING;
        }

        if (!collides(Dir.RIGHT)) {
            if (face == Facing.LEFT && action != Action.FALLING
                    && action != Action.JUMPING) {
                face = Facing.RIGHT;
            }
            this.setX(x + (walkingSpeed));
        }
    }

    public void rightRUN() {
        if (action != Action.JUMPING && action != Action.FALLING) {
            action = Action.RUNNING;
        }

        if (!collides(Dir.RIGHT)) {
            if (face == Facing.LEFT && action != Action.FALLING
                    && action != Action.JUMPING) {
                face = Facing.RIGHT;
            }
            this.setX(x + (runningSpeed));
        }
    }

    public boolean collides(Dir direction) {
        Rectangle copy = collision.getBounds();

        if (direction == Dir.DOWN) {
            copy.setLocation((int) copy.getX(), (int) copy.getY() + 1);
            if (area.intersects(copy)) {
                Area collided = new Area(copy);
                collided.intersect(area);
                this.setY((int) collided.getBounds().getMinY()
                        - (int) this.height);
                return true;
            }
            return false;
        }
        if (direction == Dir.UP) {
            copy.setLocation((int) copy.getX(), (int) copy.getY()
                    - this.jumpSpeed);
            if (area.intersects(collision)) {
                return true;
            }
            return false;
        }

        if (direction == Dir.LEFT) {
            copy.setLocation((int) copy.getX() - runningSpeed,
                    (int) copy.getY());
            if (area.intersects(copy)) {
                return true;
            }
            return false;
        }
        if (direction == Dir.RIGHT) {
            copy.setLocation((int) copy.getX() + runningSpeed,
                    (int) copy.getY());
            if (area.intersects(copy)) {
                return true;
            }
            return false;
        }

        return false;

    }

}
