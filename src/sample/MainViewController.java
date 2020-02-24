package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class MainViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Slider speedSlider;

    @FXML
    private Label actualSpeedLabel;

    @FXML
    void onMenuAbout(ActionEvent event) {

    }

    @FXML
    void onMenuClose(ActionEvent event) {

    }

    @FXML
    void onMenuLoad(ActionEvent event) {

    }

    @FXML
    void onMenuSave(ActionEvent event) {

    }

    @FXML
    void onStartButtonPress(ActionEvent event) {

    }

    @FXML
    void onStopButtonPress(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert speedSlider != null : "fx:id=\"speedSlider\" was not injected: check your FXML file 'mainView.fxml'.";
        assert actualSpeedLabel != null : "fx:id=\"actualSpeedLabel\" was not injected: check your FXML file 'mainView.fxml'.";

    }
}
