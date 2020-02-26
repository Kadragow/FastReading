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
//        if(file!=null){
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//            String loadedText;
//            StringBuilder textToDisplay = new StringBuilder();
//            while ((loadedText = bufferedReader.readLine()) != null){
//                textToDisplay.append(loadedText);
//            }
//            String[] words = textToDisplay.toString().split(" ");
//            for(String word : words){
//                Text text = new Text(word + " ");
//                if(word.equals("Lorem"))
//                    text.setFill(Color.RED);
//
//                textFlow.getChildren().add(text);
//
//
//                //textList.add(new Text(word));
//            }
//            //setBackgroundColors();
//            //textToReadArea.setStyle("-fx-control-inner-background:#000000;");
//            //textToReadArea.setText("gfdsga");
//            //textToReadArea.setStyle("-fx-control-inner-background:#ffffff;");
//            //textToReadArea.setText(textToReadArea.getText() + textToDisplay.toString());
//            //textToReadArea.setStyle("-fx-background-color: blue ");
//
//        }
    }

    @FXML
    void onMenuSave(ActionEvent event) {

    }

    @FXML
    void onStartButtonPress(ActionEvent event) throws InterruptedException {
        isReading = true;
        position = 0;
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(500),
                        e -> {
                            if(this.isReading){
                                textFlow.getChildren().setAll(textLoader.getColoredTextList(position, Color.RED));
                                position++;
                                if(position>50){
                                    isReading = false;
                                }
                            }
                        }
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();
//        while(position < 5){
//            textFlow.getChildren().setAll(textLoader.getColoredTextList(position, Color.RED));
//            Thread.sleep(500);
//            position++;
//        }

    }

    @FXML
    void onStopButtonPress(ActionEvent event) {
        isReading = false;
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
