import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainMenuController {

    @FXML
    void changeSceneToGameStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StartGame.fxml"));
        Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
        window.setTitle("Hangman");
        window.setScene(new Scene(root, 800, 650));
        window.show();
    }

    @FXML
    void changeSceneToHighScore(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HighScore.fxml"));
        Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
        window.setTitle("Hangman");
        window.setScene(new Scene(root, 800, 650));
        window.show();
    }
    
    @FXML
    void exitGame(ActionEvent event) throws IOException {
        Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
        window.close();
    }
}