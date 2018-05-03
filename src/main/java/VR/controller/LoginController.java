package VR.controller;

import VR.Main;
import VR.profile.Options;
import VR.profile.Profile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * LoginController, käsittelee kirjautumissivun toiminnot.
 * Käyttää tietokantoja ja täällä niihin myös lisätään tiedot.
 *
 * @version 1.0 3 May 2018
 * @author Mikael Kukkamäki
 */
public class LoginController {

    @FXML
    private TextField errors;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Button button;

    @FXML
    void addUser(ActionEvent event) {
        Profile prof = new Profile(-1, username.getText(), password.getText());

        try {
            prof = Main.profilesdao.saveOrUpdate(prof);
        } catch (Exception e) {
            System.out.println("ERROR");
            Main.login.error();
        }
        if (prof == null) {
            errors.setVisible(true);
            errors.setText("Username already taken!");
        } else {
            errors.setVisible(false);
            Options option = new Options(-1, prof.getId(), 1, "Player");
            try {
                option = Main.optionsdao.saveOrUpdate(option);
            } catch (Exception e) {
                System.out.println("ERROR");
                Main.login.error();
            }
            prof = null;
            option = null;
            button.fire();
        }
        
    }

    @FXML
    void checkUser(ActionEvent event) {

        Profile prof = null;
        Options option = null;
        try {
            prof = Main.profilesdao.findWithUsername(username.getText(), password.getText());
        } catch (Exception e) {
            System.out.println("Error");
            Main.login.error();
        }

        if (prof != null) {
            Main.profile = prof;
            try {
                option = Main.optionsdao.findOne(prof.getId());
            } catch (Exception e) {
                System.out.println("Error");
                Main.login.error();
            }
            if (option == null) {
                System.out.println("PERKELE!!!!");
            }
            Main.options = option;
            Main.login.menu();
        } else {
            errors.setVisible(true);
            errors.setText("No such username or password!");
        }

    }

}
