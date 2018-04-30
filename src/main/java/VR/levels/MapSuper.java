package VR.levels;

import VR.Main;
import VR.util.XmlWriterUtil;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URL;
import tiled.core.MapLayer;
import tiled.core.ObjectGroup;
import tiled.core.Tile;
import tiled.core.TileLayer;
import tiled.core.TileSet;
import tiled.io.TMXMapReader;
import tiled.util.TileCutter;

public class MapSuper {

    private tiled.core.Map map;
    private TileSet setti;
    private TileLayer play;
    private ObjectGroup group;
    private int scale;

    private Area area;

    public MapSuper(String location) throws Exception {
        String here = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/') + "/levels/" + location;
//        System.out.println(System.getProperties().getProperty("java.class.path").split(";")[0] + "/levels/" + location);
//        map = new TMXMapReader().readMap(System.getProperties().getProperty("java.class.path").split(";")[0] + "/levels/" + location);
        if (!Main.xmlWriter.getSystem() && !Main.xmlWriter.getFixed()) {
            Main.xmlWriter.fixTileset(here);
        }
        
        map = new TMXMapReader().readMap(here);

        for (MapLayer layer : map.getLayers()) {
            if (layer.getName().equals("Play")) {
                this.play = (TileLayer) layer;
                break;
            }
        }
        if (this.play == null) {
            System.out.println("No 'Play' layer inside map! CREATE IT!");
            System.exit(-1);
        }
        scale = 2;

        updateBoundary();
    }

    public void updateBoundary() {
        Tile tile = null;

        try {
            area = new Area();
        } catch (Exception e) {
            System.out.println("updateBoundary error!");
            Main.login.error();
        }

        for (int y = 0; y <= map.getHeight(); y++) {
            for (int x = 0; x <= map.getWidth(); x++) {
                if (play.getTileAt(x, y) == null) { //Get tile at x and y! If null go to next!
                    continue;
                }
                tile = play.getTileAt(x, y);

                int width = tile.getWidth() * scale;
                int height = tile.getHeight() * scale;

                Rectangle2D tileBound = new Rectangle();
                tileBound.setRect(x * width, y * height, width, height);
                Area rectangleArea = new Area(tileBound);
                area.add(rectangleArea);
            }
        }
    }
    

    public tiled.core.Map getMap() {
        return this.map;
    }

    public Area getBoundary() {
        return this.area;
    }

}
