package VR;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static VR.Main.scene;
import VR.handlers.Keylistener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

public class KeyListenerTest extends ApplicationTest {

    Keylistener listener;
    Stage stage;
    Scene scene;
    Group root;

    
    @Override
    public void start(Stage stage) {
        root = new Group();
        scene = new Scene(root, 400, 200, false, SceneAntialiasing.DISABLED);
        listener = new Keylistener(scene);
        this.stage = stage;
        stage.setTitle("Keylistener test!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    @Test
    public void KeyPressFound() {
        press(KeyCode.ESCAPE);
        assertTrue("No input found..." + listener.getInput().size(), listener.getInput().contains("ESCAPE"));
    }
    
    @Test
    public void noDoubles() {
        press(KeyCode.ESCAPE);
        press(KeyCode.ESCAPE);
        assertTrue("There were two same inputs! -> " + listener.getInput().size(), listener.getInput().size() == 1);
    }
    
    @Test
    public void releaseRemoves() {
        press(KeyCode.ESCAPE);
        assertTrue("No input found..." + listener.getInput().size(), listener.getInput().contains("ESCAPE"));
        release(KeyCode.ESCAPE);
        assertTrue("Did not release..." + listener.getInput().size(), !listener.getInput().contains("ESCAPE"));
    }

}
