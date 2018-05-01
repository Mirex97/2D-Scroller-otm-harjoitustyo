package VR.handlers;

import VR.Main;
import VR.entities.Sprite;
import VR.util.FrameMaker;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class Imagehandler {

    private Image playerBox;
    private ArrayList<Image[]> coin;
    private final int[] coinFrameAmount = {
        6, 8
    };

    public Imagehandler() {
        coin = Main.maker.makeFrames("/objects/Coin.png", coinFrameAmount, 32, 32, 1);

        playerBox = new Image("/characters/player/PlayerBox.png");
    }

    public ArrayList<Image[]> getCoin() {
        return coin;
    }

    public Image getPlayer() {
        return playerBox;
    }

}
