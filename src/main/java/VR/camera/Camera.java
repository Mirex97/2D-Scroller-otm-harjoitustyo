package VR.camera;

//Luokkaan muutokset! Siirtää koko canvasia sen sijaan että siirtää itseään!
// Näin saadaan kaikki objektit liikkumaan mukana.
import VR.Main;
import VR.entities.EntitySuper;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.MapObject;
import tiled.core.ObjectGroup;
import tiled.core.Tile;
import tiled.core.TileLayer;

/**
 * Camera luokka, joka käsittelee annetun kentän näyttämisen ruudulla.
 *
 * @version 1.0 3 May 2018
 * @author Mikael Kukkamäki
 * @see VR.entities.EntitySuper
 */
public class Camera extends EntitySuper {

    
    private double WIDTH;
    private double HEIGHT;

    private int mapXMin;
    private int mapXMax;
    private int mapYMin;
    private int mapYMax;
    private int speed;

    private final int gameScale = 2;

    private double middleX; 
    private double middleY; 

    private int tileSize; 
    
    private double tileOffsetX; 
    private double tileOffsetY;
    private int drawTilesWidth; 
    private int drawTilesHeight;

    
    private Map map;
    private HashMap<Integer, Image> tiles;
    private HashMap<Integer, Image> images;
    private double scale;

    
    private GraphicsContext gc;

    /**
     * Direction enum sisältää suunnat joihin kamera liikkuu.
     * Vanha metodi, nyt käytössä vain zoomaamiseen.
     */
    public enum Direction {
        STILL, UP, DOWN, LEFT, RIGHT
    }

    /**
     * Konstruktori, joka asettaa kameran kohtaan (0, 0).
     *
     * @param map kenttä, joka halutaan piirtää.
     * @param size tilesize, joka määrittää tile koon. (.tmx kartta).
     */
    public Camera(Map map, int size) {
        super(0, 0);
        init(map, size);
    }

    /**
     * Konstruktori, joka asettaa kameran kohtaan (x, y).
     *
     * @param map kenttä, joka halutaan piirtää.
     * @param x, x koordinaatti.
     * @param y, y koordinaatti.
     * @param size tilesize, joka määrittää tile koon. (.tmx kartta).
     */
    public Camera(Map map, int x, int y, int size) {
        super(x, y);
        init(map, size);
    }

    /**
     * metodi init osana konstruktoria. tämä asettaa kameran valmiiksi
     * parametrissa annettua map oliota käyttäen.
     *
     * @param map kenttä.
     * @param size tilekoko.
     */
    public void init(Map map, int size) { 
        this.tileSize = size * gameScale;
        gc = Main.gc;
        WIDTH = Main.width;
        HEIGHT = Main.height;

        this.map = map;

        tiles = new HashMap<>();
        images = new HashMap<>();
        mapXMin = 0;
        mapYMin = 0;
        speed = 3;
        mapXMax = map.getWidth() * tileSize;
        mapYMax = map.getHeight() * tileSize;

        scale = 0;

        middleX = (this.x + this.WIDTH) / 2;
        middleY = (this.y + this.HEIGHT) / 2;

        drawTilesWidth = ((int) WIDTH + tileSize) / tileSize;
        drawTilesHeight = ((int) HEIGHT + tileSize) / tileSize;

        tileOffsetX = this.x % tileSize;
        tileOffsetY = this.y % tileSize;
    }

    /**
     * Päivittää oikeat Offsetit. Tämän avulla saadaan sulavuus kameran
     * liikkeeseen.
     */
    public void update() {
        this.tileOffsetX = (int) (0 - gc.getTransform().getTx()) % tileSize;
        this.tileOffsetY = (int) (0 - gc.getTransform().getTy()) % tileSize;
    }

    /**
     * metodi zoom asettaa kameran zoomauksen, joko suuremmaksi tai palauttaa
     * sen. Muuttaa koko canvas olion skaalausta. (Aiheuttaa hieman sumentamista
     * liian lähelle mentäessä).
     *
     * @param dir suunta johon zoomataan. UP on sisään ja DOWN ulos.
     */
    public void zoom(Direction dir) {

        if (dir == Direction.DOWN) {
            if (scale > 0) {
                scale -= 0.01;
            } else {
                scale = 0;
            }

            Main.canvas.setScaleX(scale + 1);
            Main.canvas.setScaleY(scale + 1);
            Main.backgroundCanvas.setScaleX(scale + 1);
            Main.backgroundCanvas.setScaleY(scale + 1);
        } else if (dir == Direction.UP) {
            scale += 0.01;
            Main.canvas.setScaleX(scale + 1);
            Main.canvas.setScaleY(scale + 1);
            Main.backgroundCanvas.setScaleX(scale + 1);
            Main.backgroundCanvas.setScaleY(scale + 1);
        }
        update();

    }

    /**
     * metodi moveXY siirtää kameran kohtaan (x, y).
     * Kameran saa seuraamaan pelaajaa antamalla sen keski x ja y:n.
     *
     * @param x x-koordinaatti johon kamera liikutetaan.
     * @param y y-koordinaatti johon kamera liikutetaan.
     */
    public void moveXY(double x, double y) {
        if (this.mapYMin <= y && y <= (this.mapYMax - HEIGHT) + ((HEIGHT / Math.pow(2, scale)) * scale)) {
            Main.gc.setTransform(gc.getTransform().getMxx(),
                    gc.getTransform().getMyx(),
                    gc.getTransform().getMxy(),
                    gc.getTransform().getMyy(),
                    gc.getTransform().getTx(),
                    (0 - y));
        } else {
            if (y < this.mapYMin) {
                Main.gc.setTransform(gc.getTransform().getMxx(),
                        gc.getTransform().getMyx(),
                        gc.getTransform().getMxy(),
                        gc.getTransform().getMyy(),
                        gc.getTransform().getTx(),
                        0
                );
            } else if (y > (this.mapYMax - HEIGHT) + ((HEIGHT / Math.pow(2, scale)) * scale)) {
                Main.gc.setTransform(gc.getTransform().getMxx(),
                        gc.getTransform().getMyx(),
                        gc.getTransform().getMxy(),
                        gc.getTransform().getMyy(),
                        gc.getTransform().getTx(),
                        (0 - (this.mapYMax - HEIGHT + ((HEIGHT / Math.pow(2, scale)) * scale)))
                );
            }
        }
        if (this.mapXMin <= x && x <= (this.mapXMax - WIDTH) + ((WIDTH / 4) * scale)) {
            Main.gc.setTransform(gc.getTransform().getMxx(),
                    gc.getTransform().getMyx(),
                    gc.getTransform().getMxy(),
                    gc.getTransform().getMyy(),
                    (0 - x),
                    gc.getTransform().getTy());
        } else {
            if (x < this.mapXMin) {
                Main.gc.setTransform(gc.getTransform().getMxx(),
                        gc.getTransform().getMyx(),
                        gc.getTransform().getMxy(),
                        gc.getTransform().getMyy(),
                        0,
                        gc.getTransform().getTy()
                );
            } else if (x > (this.mapXMax - WIDTH) + ((WIDTH / 4) * scale)) {
                Main.gc.setTransform(gc.getTransform().getMxx(),
                        gc.getTransform().getMyx(),
                        gc.getTransform().getMxy(),
                        gc.getTransform().getMyy(),
                        (0 - (this.mapXMax - WIDTH + ((WIDTH / 4) * scale))),
                        gc.getTransform().getTy()
                );
            }
        }
        update();
    }

    

    /**
     * metodi getTileOffsetX palauttaa X:n offsetin.
     * 
     * @return palauttaa offsetin.
     */
    public double getTileOffsetX() {
        return this.tileOffsetX;
    }

    /**
     * metodi getTileOffsetY palauttaa Y:n offsetin.
     * 
     * @return palauttaa offsetin.
     */
    public double getTileOffsetY() {
        return this.tileOffsetY;
    }

    /**
     * metodi drawImages piirtää kentän kuvat canvakselle.
     * Tämä on uusi metodi kentän tulostusta varten.
     * @param layerName tason nimi, joka on määritetty .tmx kartassa.
     */
    public void drawImages(String layerName) {
        ObjectGroup layer = null;
        for (MapLayer meh : map.getLayers()) {
            if (meh.getName().equals(layerName)) {

                layer = (ObjectGroup) meh;
            }
        }
        if (layer == null) {
            System.out.println("Layer not found! DOUBLE CHECK");
            Main.login.error();
        }
        Iterator<MapObject> meh = layer.getObjects();
        while (meh.hasNext()) {
            Image tileImage = null;
            MapObject object = meh.next();
            Tile tile = object.getTile();
            if (images.containsKey(tile.getId())) {
                tileImage = images.get(tile.getId());
            } else {
                try {
                    tileImage = createImage(object.getTile().getImage().getScaledInstance((int) object.getWidth() * this.gameScale, (int) object.getHeight() * gameScale, 1));
                } catch (Exception e) {
                    System.out.println("WAT! Create image error!");
                    Main.login.error();
                }
                images.put(tile.getId(), tileImage);
            }
            gc.drawImage(tileImage, object.getX() * gameScale, (object.getY() - object.getHeight()) * gameScale);

        }
    }

    /**
     * metodi draw piirtää kentän canvakselle (tiles).
     * Tämä on vanha metodi, jota käytetään vielä peli alueen piirtämiseen.
     * Aiheutti pätkintää, ja myös .tmx kartan suurentumista mitä enemmän tile tasoja oli.
     * käyty metodia drawImage mieluummin.
     * @param layerName tason nimi, joka on määritetty .tmx kartassa.
     * @see VR.camera.Camera#drawImages(java.lang.String) 
     */
    public void draw(String layerName) {
        TileLayer layer = null;
        for (MapLayer meh : map.getLayers()) {
            if (meh.getName().equals(layerName)) {
                layer = (TileLayer) meh;
            }
        }
        if (layer == null) {
            System.out.println("Layer not found! DOUBLE CHECK");
            Main.login.error();
        }
        Tile tile = null;
        int tileId;
        Image tileImage = null;
        double theX = (0 - gc.getTransform().getTx());
        double theY = (0 - gc.getTransform().getTy());
        for (int drawY = 0; drawY <= this.drawTilesHeight; drawY++) {
            for (int drawX = 0; drawX <= this.drawTilesWidth; drawX++) {
                int tileX = drawX + (int) ((theX - this.tileOffsetX) / tileSize);
                int tileY = drawY + (int) ((theY - this.tileOffsetY) / tileSize);
                if (layer.getTileAt(tileX, tileY) == null) {
                    continue;
                }
                tile = layer.getTileAt(tileX, tileY);
                tileId = tile.getId();

                if (tiles.containsKey(tileId)) {
                    tileImage = tiles.get(tileId);
                } else {
                    try {
                        tileImage = createImage(tile.getImage().getScaledInstance(tileSize, tileSize, 1));
                    } catch (Exception e) {
                        System.out.println("WAT! Create image error!");
                        Main.login.error();
                    }
                    tiles.put(tileId, tileImage);
                }
                gc.drawImage(tileImage, (tileX * tileSize), (tileY * tileSize));
            }
        }

    }

    
    /**
     * metodi createImage muuttaa java.awt.Image olion javafx.scene.imageksi.
     * @param image kuva joka halutaan muutta. Tässä luokassa se olisi tilesit ja kentän kuvat.
     * @return palauttaa javafx kuvan.
     */
    public javafx.scene.image.Image createImage(java.awt.Image image) throws Exception {
        if (!(image instanceof RenderedImage)) {
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
                    image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();
            image = bufferedImage;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) image, "png", out);
        out.flush();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return new javafx.scene.image.Image(in);
    }
}
