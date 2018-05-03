package VR.mapitems;

//Handles all texts and makes them appear in the gui!

import java.awt.Rectangle;
import java.util.ArrayList;

public class Text {

    private Rectangle collision;
    private String label; //Topic of the message.
    private ArrayList<String> characters; //Character who talks!
    private ArrayList<String> messages; //Message to be displayed
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
