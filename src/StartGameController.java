import java.io.IOException;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartGameController {
    Image imageLife0 = new Image(getClass().getResourceAsStream("Image/0.png"));
    Image imageLife1 = new Image(getClass().getResourceAsStream("Image/1.png"));
    Image imageLife2 = new Image(getClass().getResourceAsStream("Image/2.png"));
    Image imageLife3 = new Image(getClass().getResourceAsStream("Image/3.png"));
    Image imageLife4 = new Image(getClass().getResourceAsStream("Image/4.png"));
    Image imageLife5 = new Image(getClass().getResourceAsStream("Image/5.png"));
    Image imageLife6 = new Image(getClass().getResourceAsStream("Image/6.png"));

    @FXML
    private Label hint;
    @FXML
    private Label hint_label;
    @FXML
    private ImageView img;
    @FXML
    private TextField input;
    @FXML
    private Label letter_count;
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

    @FXML
    void CheckInput(ActionEvent event) {
        String inputText = input.getText();
        if(word.contains(inputText)) {
            int index = 0;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(String.valueOf(c).equals(inputText)) {
                    setLetter(index, Character.toString(c));
                }
                index++;
            }
        } else {
            setImage();
        }
    }

    public void setLetter(int index, String inputText) {
        if(index == 0) tf1.setText(inputText);
        else if(index == 1) tf2.setText(inputText);
        else if(index == 2) tf3.setText(inputText);
        else if(index == 3) tf4.setText(inputText);
        else if(index == 4) tf5.setText(inputText);
        else if(index == 5) tf6.setText(inputText);
        else if(index == 6) tf7.setText(inputText);
        else if(index == 7) tf8.setText(inputText);
    }

    public void setImage() {
        if(life == 6) img.setImage(imageLife1);
        else if(life == 5) img.setImage(imageLife2);
        else if(life == 4) img.setImage(imageLife3);
        else if(life == 3) img.setImage(imageLife4);
        else if(life == 2) img.setImage(imageLife5);
        else if(life == 1) img.setImage(imageLife6);
        else if(life == 0) {
            img.setImage(imageLife0);
            hint_label.setText("The word is: " + word);
        }
        life--;
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