package VR.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * ErrorController, käsittelee virhetapahtuma sivun toiminnot.
 *
 * @version 1.0 3 May 2018
 * @author Mikael Kukkamäki
 */
public class ErrorController {

    @FXML
    void closeApplication(ActionEvent event) {
        System.exit(0);
    }

}
