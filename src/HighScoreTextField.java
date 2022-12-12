import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class HighScoreTextField extends TextField {
	
	public HighScoreTextField(String text) {
		// set size, WIP
		prefWidth(50);
		prefHeight(50);
		
		setFocusTraversable(false);
		setEditable(false);
		setMouseTransparent(true);
		setStyle("-fx-background-color: rgba(0,0,0,0);");
		setAlignment(Pos.CENTER);
		setText(text);
		setFont();
	}
	
	private void setFont() {
		setFont(Font.font("System", 15));
	}
}
