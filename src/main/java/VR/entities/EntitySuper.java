
package VR.entities;


public class EntitySuper {
    protected double x;
    protected double y;
    
    
    public EntitySuper() {
        this.x = 0;
        this.y = 0;
    }
    
    public EntitySuper(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    
}
