package VR.entities;

import VR.Main;
import java.awt.Graphics;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import tiled.core.Map;

public final class Player extends Animate {

    private ArrayList<String> input;
    private Image image;
    private final Area area;

    private ArrayList<Image[]> sprites;
    private final int[] frameAmount = {
        6, 6, 5, 12, 12, 7, 7, 6, 6, 8, 8
    };
    private static final int WALKINGRIGHT = 0;
    private static final int WALKINGLEFT = 1;
    private static final int TURNAROUND = 2;
    private static final int IDLELEFT = 3;
    private static final int IDLERIGHT = 4;
    private static final int JUMPLEFT = 5;
    private static final int JUMPRIGHT = 6;
    private static final int FALLLEFT = 7;
    private static final int FALLRIGHT = 8;
    private static final int CONFLEFT = 9;
    private static final int CONFRIGHT = 10;

    private boolean turning = false;

    private Sprite sprite;

    public Player(Map map, Area area) {
        super(map, area);
        this.area = area;

        this.sprite = new Sprite();
        this.sprite.setDelay(100);
        sprites = new ArrayList<>();

        input = Main.keys.getInput();
        setSpawnpoint(10, 10);
        setImageLocation("/characters/player/turnaround.gif");
        setCollision(x, y);
        middleX = image.getWidth() / 2;
        middleY = image.getHeight() / 2;

        makeFrames();
        this.sprite.setFrames(sprites.get(FALLRIGHT));
        this.sprite.setDelay(100);
        this.sprite.setLoop(4);

        setImage(this.sprite.getImage());

    }

    public void makeFrames() {
        BufferedImage sheet = null;
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream("/characters/player/Mikael.png"));
        } catch (Exception e) {
            System.out.println("ERROR player image not found");
            Main.login.error();
//            e.printStackTrace();
//            System.exit(-1);
        }

        int cwidth = 32;
        int cheight = 64;
        for (int i = 0; i < frameAmount.length; i++) {
            Image[] images = new Image[frameAmount[i]];
            for (int x = 0; x < frameAmount[i]; x++) {
                try {
                    images[x] = createImage(sheet.getSubimage(x * cwidth, i * cheight, cwidth, cheight)
                            .getScaledInstance((int) (cwidth * scale), (int) (cheight * scale), 1));
                } catch (Exception ex) {
                    System.out.println("If you see this check ArrayList! Are the frame amounts correct!");
                }
            }
            sprites.add(images);
        }

    }

    public void move() {

        if (input.contains("W") && action != action.FALLING) {
            action = action.JUMPING;
        }
        if (action != action.JUMPING) {
            down();
        }
        if (action == Action.JUMPING) {
            up();
        }

        if (input.contains("A") && !input.contains("D")) {
            if (input.contains("SHIFT")) {
                leftRUN();
            } else {
                left();
            }
        }
        if (input.contains("D") && !input.contains("A")) {
            if (input.contains("SHIFT")) {
                rightRUN();
            } else {
                right();
            }
        }

        reloadCollision();
        updateMiddles();
    }

    public void changeActionFrames() {

        if (super.face == face.LEFT) {
            if (previousFace != face.LEFT && !turning || previousFace == face.LEFT && turning) {
                this.sprite.setFrames(sprites.get(TURNAROUND));
                this.sprite.setDelay(50);
                turning = true;
            } else if (previousFace != face.LEFT && turning && this.sprite.hasPlayedOnce() || previousAction != action) {
                previousFace = face.LEFT;
                turning = false;

                //HERE ALL ACTIONS!
                if (super.action == Action.WALKING) {
                    this.sprite.setFrames(sprites.get(WALKINGLEFT));
                    this.sprite.setDelay(100);
                }
                if (super.action == Action.IDLE) {
                    this.sprite.setFrames(sprites.get(IDLELEFT));
                    this.sprite.setDelay(100);
                }
                if (super.action == Action.JUMPING) {
                    this.sprite.setFrames(sprites.get(JUMPLEFT));
                    this.sprite.setDelay(100);
                    this.sprite.setLoop(3);
                }
                if (super.action == Action.FALLING) {
                    this.sprite.setFrames(sprites.get(FALLLEFT));
                    this.sprite.setDelay(100);
                    this.sprite.setLoop(4);
                }

            }
            this.previousAction = this.action;
        }
        if (super.face == face.RIGHT) {
            if (previousFace != face.RIGHT && !turning || previousFace == face.RIGHT && turning) {
                this.sprite.setFrames(sprites.get(TURNAROUND));
                this.sprite.setReverse();
                this.sprite.setDelay(50);
                turning = true;
            } else if (previousFace != face.RIGHT && turning && this.sprite.hasPlayedOnce() || previousAction != action) {
                previousFace = face.RIGHT;
                turning = false;

                //HERE ALL ACTIONS
                if (super.action == Action.WALKING) {
                    this.sprite.setFrames(sprites.get(WALKINGRIGHT));
                    this.sprite.setDelay(100);
                }
                if (super.action == Action.IDLE) {
                    this.sprite.setFrames(sprites.get(IDLERIGHT));
                    this.sprite.setDelay(100);
                }
                if (super.action == Action.JUMPING) {
                    this.sprite.setFrames(sprites.get(JUMPRIGHT));
                    this.sprite.setDelay(100);
                    this.sprite.setLoop(3);
                }
                if (super.action == Action.FALLING) {
                    this.sprite.setFrames(sprites.get(FALLRIGHT));
                    this.sprite.setDelay(100);
                    this.sprite.setLoop(4);
                }

            }
            this.previousAction = this.action;
        }
    }

    public void draw() {
        //Need images from sprite class! And get action and direction from super!
        this.sprite.update();
        changeActionFrames();

        setImage(this.sprite.getImage());
        Main.gc.drawImage(image, x, y);
//        Main.gc.fillRect(collision.x, collision.y, collision.width, collision.height);
    }

    public void setSpawnpoint(int x, int y) {
        this.setXY(x, y);
    }

    public void updateMiddles() {
        this.middleX = this.getX() + (width / 2);
        this.middleY = this.getY() + (height / 2);
    }

    public double getMiddleX() {
        return this.middleX;
    }

    public double getMiddleY() {
        return this.middleY;
    }

    public void reloadCollision() {
        collision.setRect(x, y, width, height);
    }

    public void setCollision(double x, double y) {
        collision = new Rectangle2D.Double();
        collision.setRect(x, y, image.getWidth(), image.getHeight());

    }

    public void setImageLocation(String location) {
        if (width == 0 || height == 0) {
            image = new Image(location);
            width = image.getWidth() * scale;
            height = image.getHeight() * scale;
        }
        image = new Image(location, (width), (height), true, false);
    }

    public void setImage(Image img) {
        if (width == 0 || height == 0) {
            image = img;
            width = image.getWidth() * scale;
            height = image.getHeight() * scale;
        }

        image = img;

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
