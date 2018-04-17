package VR.controller;

import VR.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class OptionsController {

    @FXML
    private Slider volume;

    @FXML
    private TextField playername;

    @FXML
    private Label isSaved;

    @FXML
    void deleteAccount(ActionEvent event) {
        //Player should be logged in and all data should be found in Main!
        try {
            Main.optionsdao.delete(Main.profile.getId());
            Main.profilesdao.delete(Main.profile.getId());
        } catch (Exception e) {
            Main.login.error();
        }
        Main.profile = null;
        Main.options = null;
        Main.login.login();
    }

    @FXML
    void returnToMenu(ActionEvent event) {
        Main.login.menu();
    }

    @FXML
    void saveOptions(ActionEvent event) {
        if (playername.getText().length() >= 1 && playername.getText().length() <= 14) {
            Main.options.setPlayername(playername.getText());
        }
        Main.options.setVolume(volume.getValue());
        try {
            Main.optionsdao.saveOrUpdate(Main.options);
        } catch (Exception e) {
            Main.login.error();
        }
        isSaved.setVisible(true);
    }

    @FXML
    void initialize() {
        volume.setValue(Main.options.getVolume());
        playername.setText(Main.options.getPlayername());
        isSaved.setVisible(false);
    }

}
