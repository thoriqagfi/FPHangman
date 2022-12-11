import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartGameController {
    Image imageLife0 = new Image(getClass().getResourceAsStream("Image/end.png"));
    Image imageLife1 = new Image(getClass().getResourceAsStream("Image/1.png"));
    Image imageLife2 = new Image(getClass().getResourceAsStream("Image/2.png"));
    Image imageLife3 = new Image(getClass().getResourceAsStream("Image/3.png"));
    Image imageLife4 = new Image(getClass().getResourceAsStream("Image/4.png"));
    Image imageLife5 = new Image(getClass().getResourceAsStream("Image/5.png"));
    Image imageLife6 = new Image(getClass().getResourceAsStream("Image/6.png"));

    @FXML
    private TextField hint;
    @FXML
    private ImageView img;
    @FXML
    private TextField input;
    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private TextField tf5;
    @FXML
    private TextField tf6;
    @FXML
    private TextField tf7;
    @FXML
    private TextField tf8;
    @FXML
    private TextField tf9;
    @FXML
    private TextField tf10;

    String[] data = {
        "MEXICO COUNTRY",
        "CANADA COUNTRY",
        "DOCTOR PROFESSION",
        "FOOTBALL SPORT",
        "TEACHER PROFESSION",
        "LEOPARD ANIMAL",
        "BICYCLE TRANSPORT",
        "SALMON FISH",
        "SPARROW BIRD",
        "PARROTS BIRD",
        "EAGLE BIRD",
        "TRAIN TRANSPORT",
        "SHIP TRANSPORT",
        "ENGINEER PROFESSION",
        "BANKER PROFESSION",
        "CRICKET SPORT",
        "BASKETBALL SPORT",
        "HOCKEY SPORT",
        "TENNIS SPORT",
        "SWIMMING sport",
        "CAR TRANSPORT",
        "BUS TRANSPORT",
        "PLANE TRANSPORT",
        "BICYCLE TRANSPORT",
        "INDONESIA COUNTRY",
        "AUSTRALIA COUNTRY",
        "JAPAN COUNTRY",
        "CHINA COUNTRY",
        "THAILAND COUNTRY",
        "ENGLAND COUNTRY",
        "FRANCE COUNTRY",
        "GERMANY COUNTRY",
        "ITALY COUNTRY",
        "SPAIN COUNTRY",
    };

    int random = new Random().nextInt(data.length);
    String wordHint = data[random];
    String[] split = wordHint.split(" ", 2);
    String word = split[0];
    String hintWord = split[1];
    int life = 6;
    int letterSize = word.length();
    boolean[] isAnswered = new boolean[11];

    public void initialize(){
        setHint();
    }

    public void setHint(){
        String hintText = hintWord + ", " + String.valueOf(letterSize) + " letters";
        hint.setText(hintText);

        for (int i = 0; i < 10; i++) {
            if (i < letterSize) {
                setLetter(i + 1, "___");
            } else {
                setLetter(i + 1, " ");
            }
        }
    }

    @FXML
    void CheckInput(ActionEvent event) {
        String inputText = input.getText();
        inputText = inputText.toUpperCase();
        if(word.contains(inputText)) {
            for(int i = 0; i < letterSize; i++) {
                char c = word.charAt(i);
                if(String.valueOf(c).equals(inputText)) {
                    setLetter(i + 1, Character.toString(c));
                    isAnswered[i + 1] = true;
                }
            }
        } else {
            life--;
            setImage();
        }
    }

    @FXML
    void HintLetter(ActionEvent event) {
        for(int i = 1; i <= letterSize; i++) {
            if(!isAnswered[i]) {
                char hint_letter = word.charAt(i-1);
                for(int j = 0; j < letterSize; j++) {
                    char c = word.charAt(j);
                    if(hint_letter == c) {
                    setLetter(j + 1, Character.toString(c));
                    isAnswered[j + 1] = true;
                    }
                }
            break;
            }
        }
    }

    public void setLetter(int index, String inputText) {
        if (index == 1) tf1.setText(inputText);
        else if (index == 2) tf2.setText(inputText);
        else if (index == 3) tf3.setText(inputText);
        else if (index == 4) tf4.setText(inputText);
        else if (index == 5) tf5.setText(inputText);
        else if (index == 6) tf6.setText(inputText);
        else if (index == 7) tf7.setText(inputText);
        else if (index == 8) tf8.setText(inputText);
        else if (index == 9) tf9.setText(inputText);
        else if (index == 10) tf10.setText(inputText);
    }

    public void setImage() {
        if (life == 6) img.setImage(imageLife6);
        else if (life == 5) img.setImage(imageLife5);
        else if (life == 4) img.setImage(imageLife4);
        else if (life == 3) img.setImage(imageLife3);
        else if (life == 2) img.setImage(imageLife2);
        else if (life == 1) img.setImage(imageLife1);
        else if (life == 0) {
            img.setImage(imageLife0);
            hint.setText("The word is: " + word);
        }
    }

    @FXML
    void changeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartGame.fxml"));
        Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
        window.setTitle("Hangman");
        window.setScene(new Scene(root, 800, 650));
        window.show();
    }

}