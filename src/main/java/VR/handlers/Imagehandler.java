
package VR.handlers;

import javafx.scene.image.Image;


public class Imagehandler {
    private Image coin;
    private Image playerBox;
    
    public Imagehandler() {
        coin = new Image("objects/coin.gif");
        playerBox = new Image("characters/player/PlayerBox.png");
    }
    
    public Image getCoin() {
        return coin;
    }
    
    public Image getPlayer() {
        return playerBox;
    }
    
    
    
    
    
}
