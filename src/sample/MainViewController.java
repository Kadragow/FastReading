package sample;

import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.control.*;
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
    private int acceleration;

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
    private TextField speedTextField;

    @FXML
    private TextField fontSize;

    @FXML
    void onDragDetectSpeedSlider(MouseEvent event) {
        updateSpeedLabel();
    }

    @FXML
    void onDragDoneSpeedSlider(DragEvent event) {
        updateSpeedLabel();
    }

    @FXML
    void onMouseClickedSpeedSlider(MouseEvent event) {
        updateSpeedLabel();
    }

    @FXML
    void onSpeedTextField(ActionEvent event) {
        double value = Double.valueOf(speedTextField.getText());
        if(value>25){
            value = 25;
            speedTextField.setText("25");
        }
        speedSlider.setValue(value);
    }

    @FXML
    void onFontSize(ActionEvent event) {
        textLoader.changeFontSize(Integer.valueOf(fontSize.getText()));
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
        if (timeline!=null){
            timeline.stop();
        }
        acceleration = 101;
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(timeDelay),
                        e -> {
                            if(this.isReading){
                                textFlow.getChildren().setAll(textLoader.getColoredTextList(position, Color.RED));
                                position++;
                                double scrollPos = position * 1.0/textLoader.getTextList().size();
                                //todo: correct offset
                                //double scrollOffset = position <= 100 ? scrollPos/acceleration : scrollPos;
                                //System.out.println(scrollPos + " : " + scrollOffset + " : " + acceleration);
                                if(acceleration!=1)
                                    acceleration--;
                                scrollPane.setVvalue(scrollPos);
                                if(position>=textLoader.getTextList().size()){
                                    isReading = false;
                                    timeline.stop();
                                    timeline = null;
                                }
                            }
                        }
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
    }

    @FXML
    void onStopButtonPress(ActionEvent event) {
        if(timeline!=null)
            timeline.stop();
        timeline = null;
        isReading = false;
    }

    @FXML
    void onResetButtonPress(ActionEvent event){
        isReading = false;
        position = 0;
        textFlow.getChildren().clear();
        textFlow.getChildren().setAll(textLoader.getTextList());
        if(timeline!=null)
            timeline.stop();
        timeline = null;
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
        updateSpeedLabel();

    }

    private void updateSpeedLabel(){
        DecimalFormat df = new DecimalFormat("#.00");
        speedTextField.setText(df.format(speedSlider.getValue()));
        //actualSpeedLabel.setText(actualSpeedText + df.format(speedSlider.getValue()));
    }
}
