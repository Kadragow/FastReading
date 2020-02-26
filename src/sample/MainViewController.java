package sample;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class MainViewController {

    private static String actualSpeedText = "Actual speed: ";

    private List<Text> textList;
    private File file;
    private TextLoader textLoader;
    private int position;
    private boolean isReading;
    private Timeline timeline;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextFlow textFlow;

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
    void onDragDetectSpeedSlider(MouseEvent event) {
        actualSpeedLabel.setText(actualSpeedText + (int)speedSlider.getValue());
    }

    @FXML
    void onDragDoneSpeedSlider(DragEvent event) {
        actualSpeedLabel.setText(actualSpeedText + (int)speedSlider.getValue());
    }

    @FXML
    void onMouseClickedSpeedSlider(MouseEvent event) {
        actualSpeedLabel.setText(actualSpeedText + (int)speedSlider.getValue());
    }

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
        textLoader.loadFile(file);
        for(Text text : textLoader.getTextList()){
            textFlow.getChildren().add(text);
        }
    }

    @FXML
    void onMenuSave(ActionEvent event) {

    }

    @FXML
    void onStartButtonPress(ActionEvent event){
        double timeDelay = 1000/speedSlider.getValue();
        isReading = true;
        boolean isPlaying = false;
        if(timeline!=null){
            try{
                timeline.play();
                isPlaying = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!isPlaying){
            timeline = new Timeline(
                    new KeyFrame(
                            Duration.millis(timeDelay),
                            e -> {
                                if(this.isReading){
                                    textFlow.getChildren().setAll(textLoader.getColoredTextList(position, Color.RED));
                                    position++;
                                    if(position>=textLoader.getColoredTextList(position, Color.RED).size()){
                                        isReading = false;
                                    }
                                }
                            }
                    )
            );
            timeline.setCycleCount( Animation.INDEFINITE );
            timeline.playFromStart();
        }
    }

    @FXML
    void onStopButtonPress(ActionEvent event) {
        timeline.pause();
        isReading = false;
    }

    @FXML
    void onResetButtonPress(ActionEvent event){
        isReading = false;
        position = 0;
        textFlow.getChildren().clear();
        textFlow.getChildren().setAll(textLoader.getTextList());
        timeline.stop();
    }

    @FXML
    void initialize() {
        textList = new ArrayList<>();
        textLoader = new TextLoader();
        scrollPane.setFitToWidth(true);
        isReading = false;
        position = 0;
        //assert speedSlider != null : "fx:id=\"speedSlider\" was not injected: check your FXML file 'mainView.fxml'.";
        //assert actualSpeedLabel != null : "fx:id=\"actualSpeedLabel\" was not injected: check your FXML file 'mainView.fxml'.";
        actualSpeedLabel.setText(actualSpeedText + (int)speedSlider.getValue());

    }
}
