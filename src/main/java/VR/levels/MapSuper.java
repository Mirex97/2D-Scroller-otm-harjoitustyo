package VR.levels;

import VR.Main;
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

public class MapSuper {

    private tiled.core.Map map;
    private TileSet setti;
    private TileLayer play;
    private ObjectGroup group;
    private int scale;

    private Area area;
/* Pakko mainita suomeksi! Tästä löytyy tuo .tmx sotku joka ei halua korjaantua millään jar tiedoston kanssa!
    Ainoastaan saan sen esiin java.clas.pathin avulla... (siis mappi!)*/
    public MapSuper(String location) throws Exception {
/* THESE uncommented sections are for testing purposes! Do not remove!*/
//        URL malocation = MapSuper.class.getClassLoader().getResource(location);
//        System.out.println(malocation.getPath());
//        InputStream maplocation = MapSuper.class.getClassLoader().getResourceAsStream(location);
//        try {
//            URL maplocation = MapSuper.class.getClassLoader().getResource(location);
//            InputStream maplocation = MapSuper.class.getClassLoader().getResourceAsStream(location);
//            System.out.println(maplocation.getPath());
//System.out.println(System.getProperties().getProperty("java.class.path").split(";")[0]);
//            map = new TMXMapReader().readMap(malocation.getPath());
        map = new TMXMapReader().readMap(System.getProperties().getProperty("java.class.path").split(";")[0] + "/levels/" + location); //WORKS!!! FOR NORMAL DO NOT TOUCH TMX file!
//        } catch (Exception e) {
//            System.out.println("File not found! Double check correct map location " + location + "!");
//            System.exit(-1);
//        }
//        try {
//            URL tilesetlocation = MapSuper.class.getClassLoader().getResource(tileset);
//            System.out.println(tilesetlocation.getPath());
//            setti = new TMXMapReader().readTileset(tilesetlocation.getPath());
//        } catch (Exception e) {
//            System.out.println("File not found! Double check correct tileset location " + tileset + "!");
//            System.exit(-1);
//        }
//        try {
//            URL maplocation = MapSuper.class.getClassLoader().getResource(location);
//            System.out.println(maplocation.getPath());
//            map = new TMXMapReader().readMap(maplocation.getPath());
//        } catch (Exception e) {
//            System.out.println("File not found! Double check correct tileset.png location " + tilesetpng + "!");
//            System.exit(-1);
//        }
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
            System.out.println("error");
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
