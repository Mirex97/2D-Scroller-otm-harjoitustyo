/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VR.util;

import VR.Main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;


public class FrameMaker {
    
    
    
    public ArrayList<Image[]> makeFrames(String path, int[] frameAmount, int cwidth, int cheight, double scale) {
        ArrayList<Image[]> sprites = new ArrayList<>();
        BufferedImage sheet = null;
        try {
            sheet = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.out.println("ERROR player image not found");
            Main.login.error();
        }
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
        
        
        return sprites;

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
