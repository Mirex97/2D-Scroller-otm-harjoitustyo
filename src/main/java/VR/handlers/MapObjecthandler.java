package VR.handlers;

import VR.entities.EntitySuper;
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
    private Area lifts;
    private ArrayList<Coin> coins;
    private ArrayList<EntitySuper> hoboSpawns;
    private ArrayList<EntitySuper> conSpawns;
    private Area switchArea;
    private Area hide;
    private Area victory;
    private Area killzone;
    
    private double playerX;
    private double playerY;

    public MapObjecthandler(Map map) {
        this.group = findObjects(map);
        this.switchArea = new Area();
        this.hide = new Area();
        this.lifts = new Area();
        this.victory = new Area();
        this.killzone = new Area();
        coins = new ArrayList<>();
        hoboSpawns = new ArrayList<>();
        conSpawns = new ArrayList<>();
        findObjectsFromGroup();
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }
    
    public ArrayList<EntitySuper> getConductors() {
        return this.conSpawns;
    }
    public ArrayList<EntitySuper> getHobos() {
        return this.hoboSpawns;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }
    public Area getLifts() {
        return lifts;
    }
    public Area getKillZone() {
        return killzone;
    }
    public Area getHide() {
        return hide;
    }
    public Area getVictory() {
        return victory;
    }
    
    public void removeCoin(Coin coin) {
        coins.remove(coin);
    }

    public void findObjectsFromGroup() {
        Iterator<MapObject> meh = group.getObjects();
        while (meh.hasNext()) {
            MapObject object = meh.next();
            if (object.getName().equals("Player")) {
                playerX = object.getX();
                playerY = object.getY();
            }
            if (object.getName().equals("Enemy")) {
                hoboSpawns.add(new EntitySuper(object.getX() * 2, object.getY() * 2));
            }
            if (object.getName().equals("Conductor")) {
                conSpawns.add(new EntitySuper(object.getX() * 2, object.getY() * 2));
            }
            if (object.getName().equals("Switch")) {
                Rectangle rect = new Rectangle((int) object.getX() * 2, (int) object.getY() * 2, (int) object.getWidth() * 2, (int) object.getHeight() * 2);
                this.switchArea.add(new Area(rect));
            }
            if (object.getName().equals("WIN")) {
                Rectangle rect = new Rectangle((int) object.getX() * 2, (int) object.getY() * 2, (int) object.getWidth() * 2, (int) object.getHeight() * 2);
                this.victory.add(new Area(rect));
            }
            if (object.getName().equals("Jump")) {
                Rectangle rect = new Rectangle((int) object.getX() * 2, (int) object.getY() * 2, (int) object.getWidth() * 2, (int) object.getHeight() * 2);
                this.lifts.add(new Area(rect));
            }
            if (object.getName().equals("out")) {
                Rectangle rect = new Rectangle((int) object.getX() * 2, (int) object.getY() * 2, (int) object.getWidth() * 2, (int) object.getHeight() * 2);
                this.hide.add(new Area(rect));
            }
            if (object.getName().equals("KILLZONE")) {
                Rectangle rect = new Rectangle((int) object.getX() * 2, (int) object.getY() * 2, (int) object.getWidth() * 2, (int) object.getHeight() * 2);
                this.killzone.add(new Area(rect));
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
