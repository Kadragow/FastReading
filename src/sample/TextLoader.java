package sample;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextLoader {
    private File file;
    private List<Text> textList;

    public TextLoader(){
        textList = new ArrayList<>();
    }

    public TextLoader(File file) {
        textList = new ArrayList<>();
        this.file = file;
    }

    public List<Text> getTextList(){
        return textList;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void loadFile(File file) throws IOException {
        this.file = file;
        if(file!=null){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String loadedText;
            StringBuilder textToDisplay = new StringBuilder();
            while ((loadedText = bufferedReader.readLine()) != null){
                textToDisplay.append(loadedText);
            }
            String[] words = textToDisplay.toString().split(" ");
            for(String word : words){
                Text text = new Text(word + " ");
                textList.add(text);
            }
        }
    }

    public List<Text> getColoredTextList(int wordIndex, Color textColor){
        List<Text> tmpTextList = new ArrayList<>();
        for (int i = 0; i < textList.size(); i++) {
            Text newText = new Text(textList.get(i).getText());
            if(i == wordIndex)
            {
                newText.setFill(textColor);
            }
            tmpTextList.add(newText);
        }
        tmpTextList.get(wordIndex).setFill(textColor);
        return tmpTextList;
    }
}
