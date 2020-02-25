package sample;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainViewController {

    private File file;


    @FXML
    private TextArea textToReadArea;

    @FXML
    private AnchorPane anchorPane;

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
    void onMenuLoad(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Chose your text file");
        file = fileChooser.showOpenDialog(anchorPane.getScene().getWindow());
        if(file!=null){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String loadedText;
            StringBuilder textToDisplay = new StringBuilder();
            while ((loadedText = bufferedReader.readLine()) != null){
                textToDisplay.append(loadedText);
            }
            textToReadArea.setText(textToDisplay.toString());
        }
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
