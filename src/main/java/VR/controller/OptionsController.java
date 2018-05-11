package VR.controller;

import VR.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * OptionsController, käsittelee options sivun toiminnot. Tallentaa käyttäjän
 * asetukset tietokantaan tai poistaa käyttäjän.
 *
 * @version 1.0 3 May 2018
 * @author Mikael Kukkamäki
 */
public class OptionsController {

    ObservableList<String> resolutionList = FXCollections.observableArrayList("800x600", "832x624", "1024x768", "1152x864", "1280x960", "1440x1080");

    @FXML
    private ChoiceBox<String> resolution;

    @FXML
    private CheckBox fullscreen;

    @FXML
    private Slider volume;

    @FXML
    private TextField playername;

    @FXML
    private Label isSaved;

    @FXML
    void deleteAccount(ActionEvent event) {
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
        
        Main.options.setResolution(Integer.parseInt(resolution.getValue().split("x")[1]));
        Main.options.setFullscreen(fullscreen.isSelected());
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
        resolution.setItems(resolutionList);
        resolution.setValue((int)(Main.options.getResolution()/0.75)+"x"+Main.options.getResolution());
        fullscreen.setSelected(Main.options.isFullscreen());
    }

}
