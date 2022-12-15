import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HighScoreController {

    @FXML
    private VBox HighScoreVBox;

    public void initialize() {
        showHighScoreList();
    }

    void showHighScoreList() {
        try {
            File txt = new File("./src/HighScoresList.txt");
            try (Scanner myReader = new Scanner(txt)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    HighScoreTextField scoreTextField = new HighScoreTextField(data);
                    HighScoreVBox.getChildren().add(scoreTextField);
                }
            }
        } catch (FileNotFoundException e) {
           e.printStackTrace(); 
        }
    }


    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
        window.setTitle("Hangman");
        window.setScene(new Scene(root, 800, 650));
        window.show();
    }
    
}