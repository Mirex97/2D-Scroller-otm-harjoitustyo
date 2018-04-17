package VR.handlers;

import VR.mapitems.Coin;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Iterator;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.MapObject;
import tiled.core.ObjectGroup;

public class MapObjecthandler {

    private ObjectGroup group;
    private ArrayList<Coin> coins;
    private Area switchArea;
    private double playerX;
    private double playerY;

    public MapObjecthandler(Map map) {
        this.group = findObjects(map);
        this.switchArea = new Area();
        coins = new ArrayList<>();
        findObjectsFromGroup();
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void findObjectsFromGroup() { //Find all objects and set them to corresponding spots!
        // This can be used regardless if you need the components!
        Iterator<MapObject> meh = group.getObjects();
        while (meh.hasNext()) {
            MapObject object = meh.next();
            if (object.getName().equals("Player")) {
                playerX = object.getX();
                playerY = object.getY();
            }
            if (object.getName().equals("Switch")) {
                Rectangle rect = new Rectangle((int) object.getX() * 2, (int) object.getY() * 2, (int) object.getWidth() * 2, (int) object.getHeight() * 2);
                this.switchArea.add(new Area(rect));
            }
            if (object.getName().equals("Coin")) {
                coins.add(new Coin((int) object.getX() * 2, (int) object.getY() * 2));
            }

        }
    }

    public ObjectGroup findObjects(Map map) {

        for (MapLayer layer : map.getLayers()) {
            if (layer.getName().equals("MapObjects")) {
                return (ObjectGroup) layer;
            }
        }
        System.out.println("MapObjects not found!");
        return null;
    }
}
