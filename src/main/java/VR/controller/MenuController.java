package VR.controller;

import VR.Main;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {

    @FXML
    private Label username;

    @FXML
    void goOptions(ActionEvent event) {
        Main.login.options();
    }

    @FXML
    void logout(ActionEvent event) {
        //Set everything to null!
        Main.profile = null;
        Main.options = null;
        Main.saves = new ArrayList<>();
        Main.login.login();
    }

    @FXML
    void startGame(ActionEvent event) {
        try { 
            Main.startGAME();
        } catch (Exception e) {
            System.out.println("Error START GAME");
            e.printStackTrace();
            Main.login.error();
        }
    }
    
    @FXML
    void initialize() {
        username.setText(Main.profile.getName());
    }

}

