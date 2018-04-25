package VR.handlers;

import java.util.Properties;

import java.util.ArrayList;
import tiled.core.ObjectGroup;
import VR.mapitems.Text;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.MapObject;

public class Messagehandler {

    private ObjectGroup group;
    private ArrayList<Text> texts;

    public Messagehandler(Map map) {
        this.group = findTexts(map);
        this.texts = new ArrayList<>();
        findTextsFromGroup();
    }

    public ObjectGroup findTexts(Map map) {
        for (MapLayer layer : map.getLayers()) {
            if (layer.getName().equals("Texts")) {
                return (ObjectGroup) layer;
            }
        }
        System.out.println("Texts not found!");
        return null;
    }

    public void removeText(Text text) {
        texts.remove(text);
    }

    public ArrayList<Text> getTexts() {
        return texts;
    }

    public void findTextsFromGroup() {
        Iterator<MapObject> meh = group.getObjects();
        int scale = 2;
        while (meh.hasNext()) {
            MapObject object = meh.next();
            Rectangle collision = new Rectangle((int) (object.getX() * scale),
                    (int) (object.getY() * scale),
                    (int) (object.getWidth() * scale),
                    (int) (object.getHeight() * scale));
            String label = object.getName();
            ArrayList<String> characters = new ArrayList<>();
            ArrayList<String> messages = new ArrayList<>();
            Set<Object> wat = object.getProperties().keySet();
            ArrayList<String> test = new ArrayList<>();
            for (Object hah : wat) {
                test.add((String) hah);
            }
            Collections.sort(test);
            for (String testing : test) {
                characters.add(testing.split(":")[0]);
                messages.add(object.getProperties().getProperty(testing));
                
            }
//            Collections.reverse(characters);
//            Collections.reverse(messages);

            Text text = new Text(label, characters, messages, collision, object.getType());
            texts.add(text);
        }
    }
}
