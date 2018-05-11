package VR.mapitems;


import java.awt.Rectangle;
import java.util.ArrayList;

public class Text {

    private Rectangle collision;
    private String label;
    private ArrayList<String> characters;
    private ArrayList<String> messages;
    private String type;
    private int time;
    
    public Text(String label, ArrayList<String> characters, ArrayList<String> messages, Rectangle rect, String type) {
        this.label = label;
        this.characters = characters;
        this.messages = messages;        
        this.collision = rect;
        this.type = type;
        this.time = -1;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    
    
    public String getType() {
        return type;
    }

    public Rectangle getCollision() {
        return collision;
    }
    
    public String getLabel() {
        return label;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }
    
    
}
