package VR.controller;

import VR.Main;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * MenuController, käsittelee menusivun toiminnot. Käynnistää pelin tai siirtyy
 * options sivulle. Tai kirjautuu ulos.
 *
 * @version 1.0 3 May 2018
 * @author Mikael Kukkamäki
 */
public class MenuController {

    @FXML
    private Label username;

    @FXML
    void goOptions(ActionEvent event) {
        Main.login.options();
    }

    @FXML
    void logout(ActionEvent event) {
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
            Main.login.error();
        }
    }

    @FXML
    void initialize() {
        username.setText(Main.profile.getName());
    }

}
