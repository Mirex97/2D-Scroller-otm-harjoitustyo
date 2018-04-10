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
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import tiled.core.Map;
import tiled.core.MapLayer;
import tiled.core.ObjectGroup;
import tiled.core.Tile;
import tiled.core.TileLayer;

public class Camera extends EntitySuper {

    //Kameran sijainti + width ja height = kuvan alue!
    private int WIDTH; //width = Main.Width;
    private int HEIGHT; // height = Main.height;

    //kartan (tiles) koko ettei mennä niitten yli!
    private int mapXMin;
    private int mapXMax;
    private int mapYMin;
    private int mapYMax;
    private int speed;

    private int middleX; // hyödyllinen jos halutaan siirtää kamera tiettyyn kohtaan!
    private int middleY; // esimerkiksi cutscenet!

    private int tileSize; //Tämä on 32*32!!!
    //offset asettaa luettavan tilen ohi riippuen x:n mukaan.
    private int tileOffsetX; //offset tmx kartan tiles:ien lukemiseen.
    private int tileOffsetY;
    private int drawTilesWidth; //tmx kartan tiles:ien määrä jotka pitää piirtää kartalle.
    private int drawTilesHeight;

    //TMX kartan luku!
    private Map map;
    private HashMap<Integer, Image> tiles;
    private double scale;
    private ObjectGroup group;

    //Piirtäminen
    private GraphicsContext gc;

    //Enum
    public enum Direction {
        STILL, UP, DOWN, LEFT, RIGHT
    }

    public Camera(Map map, int size) {
        super(0, 0);
        init(map, size);
    }

    public Camera(Map map, int x, int y, int size) {
        super(x, y);
        init(map, size);
    }

    

    public void init(Map map, int size) { //Osa konstruktoria, rikkoo toiston!
        this.tileSize = size; //<-- Jos pelissä käytettäänkin suurempaa tilekokoa! Vaihda tämä.
        // Voi myös laskea tilekoon tmx:stä mutta käy se näinkin!
        gc = Main.gc;
        WIDTH = Main.width;
        HEIGHT = Main.height;

        this.map = map;
        group = new ObjectGroup(map);
        

        tiles = new HashMap<>();
        mapXMin = 0; //Kartan minimi ja maximi aina 0!
        mapYMin = 0;
        speed = 3; // <-- saatetaan tarvita! Todennäköisesti ehkä ei.
        mapXMax = map.getWidth() * tileSize;
        mapYMax = map.getHeight() * tileSize;

        scale = 0;

        middleX = (this.x + this.WIDTH) / 2; //Tarvitsee vielä korjauksen!
        middleY = (this.y + this.HEIGHT) / 2;

        drawTilesWidth = (WIDTH + tileSize) / tileSize; //Piirrettävien tiles:ien leveys!
        drawTilesHeight = (HEIGHT + tileSize) / tileSize; //Piirrettävien tiles:ien korkeus!

        tileOffsetX = this.x % tileSize; //Getterit ja setterit! Tarvitaan oikeaan hahmojen sijoittamiseen!
        tileOffsetY = this.y % tileSize; // Nyt totesin että tämä saattaa olla väärin!
        // Käytetään ehkä vain kameran x ja y koordinaatteja sijoittamaan objektit!
        //Tarvitaan varmaan kaksi offsettiä! Tai sitten ei.
    }

    public void update() {
        this.tileOffsetX = (int) (0 - gc.getTransform().getTx()) % tileSize;
        this.tileOffsetY = (int) (0 - gc.getTransform().getTy()) % tileSize;
    }

    public void followPlayer() {
        //TODO
    }

    public void move(Direction dir) {

        if (dir == Direction.DOWN) {
            if (scale > 0) {
                scale -= 0.01;
            } else {
                scale = 0;
            }

            Main.canvas.setScaleX(scale + 1);
            Main.canvas.setScaleY(scale + 1);
        } else if (dir == Direction.UP) {
            scale += 0.01;
            Main.canvas.setScaleX(scale + 1);
            Main.canvas.setScaleY(scale + 1);
        } else if (dir == Direction.RIGHT) {
            Main.gc.translate(x - speed, y);
        } else if (dir == Direction.LEFT) {
            Main.gc.translate(x + speed, y);
        }
        update();

    }

    public void moveXY(int x, int y) {
//        this.setXY(x, y);
        if (this.mapYMin <= y && y <= (this.mapYMax - HEIGHT)+ ((HEIGHT / Math.pow(2, scale)) * scale)) {
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
    
    public ObjectGroup getGroup() {
        return this.group;
    }

    public long getTileOffsetX() {
        return this.tileOffsetX;
    }

    public long getTileOffsetY() {
        return this.tileOffsetY;
    }

    public void draw(String layerName) {
        TileLayer layer = null;
        for (MapLayer meh : map.getLayers()) {
            if (meh.getName().equals(layerName)) {
                layer = (TileLayer) meh;
            }
        }
        if (layer == null) {
            System.out.println("Layer not found! DOUBLE CHECK");
            System.exit(-1);
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
                        tileImage = createImage(tile.getImage().getScaledInstance(16, 16, 1));
                    } catch (Exception e) {
                        System.out.println("TILE ERROR");
                        System.exit(-1);
                    }
                    tiles.put(tileId, tileImage);
                }
                gc.drawImage(tileImage, (tileX * tileSize), (tileY * tileSize));
            }
        }

    }
    

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
