package VR.handlers;

import VR.Main;
import VR.entities.EntityCustom;
import VR.entities.Sprite;
import VR.util.FrameMaker;
import java.util.ArrayList;
import javafx.scene.image.Image;

public class Imagehandler {

    private EntityCustom playerBox;
    private EntityCustom radar;
    private EntityCustom intercom;
    private ArrayList<Image[]> coin;
    private final int[] coinFrameAmount = {
        6, 8
    };

    public Imagehandler() {
        coin = Main.maker.makeFrames("/objects/Coin.png", coinFrameAmount, 32, 32, 1);

        
        intercom = new EntityCustom("/objects/Intercom.gif");
        playerBox = new EntityCustom("/characters/player/PlayerBox.png");
        radar = new EntityCustom("/objects/Radar.gif");
    }

    public EntityCustom getRadar() {
        return radar;
    }

    public EntityCustom getIntercom() {
        return intercom;
    }
    
    public ArrayList<Image[]> getCoin() {
        return coin;
    }

    public EntityCustom getPlayer() {
        return playerBox;
    }

}
