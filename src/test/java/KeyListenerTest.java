/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static VR.Main.scene;
import VR.keyhandlers.Keylistener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.testfx.framework.junit.ApplicationTest;


public class KeyListenerTest extends ApplicationTest {
    
    Keylistener listener;   
    Stage stage;
    Scene scene;
    Group root;
    
    
    @Override
    public void start(Stage stage) {
        root = new Group();
        scene = new Scene(root, 1024, 768, false, SceneAntialiasing.DISABLED);
        listener = new Keylistener(scene);
        this.stage = stage;
        stage.setTitle("Keylistener test!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
    }
    
   
    
     @Test
     public void KeyPressFound() {
        push(KeyCode.ENTER);
        assertTrue("No input found...", !listener.getInput().contains("ENTER"));
     }
     
}
