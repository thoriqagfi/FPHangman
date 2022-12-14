import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
    private TextField textScore;
    @FXML
    private HBox wordHBox;
    @FXML
    private Button buttonCheck;
    @FXML
    private TextField hint;
    @FXML
    private ImageView img;
    @FXML
    private TextField input;
    @FXML
    private Button buttonHint;
    @FXML
    private Button buttonGetNewWord;
    
    ArrayList<TextField> wordHBoxField = new ArrayList<TextField>();
    
    ArrayList<Image> imageLife = new ArrayList<Image>(
        Arrays.asList(
            imageLife0,
            imageLife1,
            imageLife2,
            imageLife3,
            imageLife4,
            imageLife5,
            imageLife6
        )
    );

    ArrayList<String> dataAnswered = new ArrayList<String>();

    ArrayList<String> data = new ArrayList<String>(
        Arrays.asList(
            "MEXICO COUNTRY",
            "CANADA COUNTRY",
            "DOCTOR PROFESSION",
            "FOOTBALL SPORT",
            "TEACHER PROFESSION",
            "LEOPARD ANIMAL",
            "BICYCLE TRANSPORT",
            "SALMON FISH",
            "CATFISH FISH",
            "GOLDFISH FISH",
            "CLOWNFISH FISH",
            "BUTTERFLYFISH FISH",
            "SARDINE FISH",
            "DOLPHIN FISH",
            "TUNA FISH",
            "KOI FISH",
            "PIRANHA FISH",
            "SHARK FISH",
            "SPARROW BIRD",
            "PARROTS BIRD",
            "EAGLE BIRD",
            "TRAIN TRANSPORT",
            "ARCHITECT PROFESSION",
            "SHIP TRANSPORT",
            "ENGINEER PROFESSION",
            "BANKER PROFESSION",
            "CRICKET SPORT",
            "BASKETBALL SPORT",
            "HOCKEY SPORT",
            "TENNIS SPORT",
            "SWIMMING SPORT",
            "AUTHOR PROFESSION",
            "CHEF PROFESSION",
            "DESIGNER PROFESSION",
            "jUDGE PROFESSION",
            "POLICE PROFESSION",
            "LAWYER PROFESSION",
            "SCIENTIST PROFESSION",
            "ENTERPRENEUR PROFESSION",
            "CAR TRANSPORT",
            "BUS TRANSPORT",
            "PLANE TRANSPORT",
            "BICYCLE TRANSPORT",
            "SWITZERLAND COUNTRY",
            "AFGHANISTAN COUNTRY",
            "TURKMENISTAN COUNTRY",
            "LIECHTENSTEIN CONUTRY",
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
            "NETHERLANDS COUNTRY",
            "DETECTIVE PROFESSION"
        )
    );

    int countHint, life, letterSize, score, random;

    String wordHint, word, hintWord;
    String[] split = new String[2];

    boolean[] isAnswered = new boolean[11];

    public void initialize() {
        initializeVariable();
        initializeWordTextField();
        setHint();
        setScore();
    }

    void initializeVariable() {
        // fetch new word
        random = new Random().nextInt(data.size());
        wordHint = data.get(random);
        split = wordHint.split(" ", 2);
        word = split[0];
        hintWord = split[1];

        // life
        life = 6;

        // score
        score = 0;
        
        // letter length
        letterSize = word.length();

        // isAnswered array
        isAnswered = new boolean[11];

        // hint count
        countHint = 0;
    }

    public void initializeWordTextField() {
        // clear HBox and wordHBox field
        wordHBox.getChildren().clear();
        wordHBoxField.clear();

        // create new TextField for every letter in word
        for (int i = 0; i < word.length(); i++) {
            wordHBoxField.add(new WordTextField("___"));
            }
        
        // add all the textField to the HBox
        wordHBox.getChildren().addAll(wordHBoxField);
    }

    public boolean isFinished(int wordLength) {
        for (int i = 0; i < wordLength; i++) {
            if (wordHBoxField.get(i).getText().charAt(0) != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    void finishedState() {
        buttonCheck.setText("NEXT");
        buttonCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextWord();
            }
        });
    }

    void nextWord() {
        // delete old word to another arraylist
        dataAnswered.add(data.get(random));
        data.remove(random);

        // get a new word
        random = new Random().nextInt(data.size());
        wordHint = data.get(random);
        split = wordHint.split(" ", 2);
        word = split[0];
        hintWord = split[1];
        letterSize = word.length();
        Arrays.fill(isAnswered, false);

        setHint();

        // reset hint count and make hint button visible
        countHint = 0;
        buttonHint.setVisible(true);

        // reset check button
        buttonCheck.setText("CHECK");
        buttonCheck.setOnAction(event -> CheckInput(event));
        
        // set hint again
        setHint();

        // make button get new word visible
        buttonGetNewWord.setVisible(true);

        // initialize all word text field
        initializeWordTextField();
    }
    
    public void setHint(){
        String hintText = hintWord + ", " + String.valueOf(letterSize) + " letters";
        hint.setText(hintText);
    }

    public void setScore() {
        String showScore = "Score: " + String.valueOf(score);
        textScore.setText(showScore);
    }

    void deleteAnsweredWord() {
        dataAnswered.add(data.get(random));
        data.remove(random);
    }

    @FXML
    void CheckInput(ActionEvent event) {
        String inputText = input.getText();
        inputText = inputText.toUpperCase();
        if(word.contains(inputText)) {
            for(int i = 0; i < letterSize; i++) {
                char c = word.charAt(i);
                if(String.valueOf(c).equals(inputText)) {
                    wordHBoxField.get(i).setText(inputText);
                    isAnswered[i] = true;
                }
            }
            
            if (isFinished(word.length())) {
                buttonGetNewWord.setVisible(false);
                buttonHint.setVisible(false);
                score++;
                setScore();
                hint.setText("You are correct!");
                finishedState();
            }
        } else {
            life--;
            setImage(life);
            
            if (life == 0) {
                saveUserScore();
                gameOver();
            }
        }

        input.selectAll();
        input.requestFocus();
        
        input.setText("");
    }

    private void gameOver() {
        hint.setText("The word is: " + word);
        buttonCheck.setText("NEW");
        buttonCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("StartGame.fxml"));
                    Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
                    window.setTitle("Hangman");
                    window.setScene(new Scene(root, 800, 650));
                    window.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        buttonGetNewWord.setVisible(false);
        buttonHint.setVisible(false);
    }

    @FXML
    void HintLetter(ActionEvent event) {
        countHint++;
        for (int i = 0; i < letterSize; i++) {
            if (!isAnswered[i]) {
                char hint_letter = word.charAt(i);
                for (int j = 0; j < letterSize; j++) {
                    char c = word.charAt(j);
                    if (hint_letter == c) {
                        wordHBoxField.get(j).setText(Character.toString(hint_letter));
                        isAnswered[j] = true;
                    }
                }

            break;
            }
        }
        if (isFinished(word.length())) {
            buttonGetNewWord.setVisible(false);
            buttonHint.setVisible(false);
            score++;
            setScore();
            hint.setText("You are correct!");
            finishedState();
        }
    }

    @FXML
    void hideHintLetter(MouseEvent event) {
        if (countHint == 3) {
            buttonHint.setVisible(false);
        }
    }
    
    void saveUserScore()  {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        String userScore = "Date: " + dtf.format(now) + ", score: " + score;
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/HighScoresList.txt", true));

            writer.newLine();
            writer.write(userScore);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setImage(int life) {
        img.setImage(imageLife.get(life));
    }

    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
        window.setTitle("Hangman");
        window.setScene(new Scene(root, 800, 650));
        window.show();
    }
    
    @FXML
    void getNewWord(ActionEvent event) throws IOException {
        countHint = 0;
        buttonHint.setVisible(true);
        random = new Random().nextInt(data.size());
        String wordHint = data.get(random);
        split = wordHint.split(" ", 2);
        word = split[0];
        hintWord = split[1];
        isAnswered = new boolean[11];
        letterSize = word.length();

        initializeWordTextField();
        setHint();
    }
}