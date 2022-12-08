import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StartGame.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("Hangman");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        launch(args);
        System.out.println("Hello, World!");
    }
}
