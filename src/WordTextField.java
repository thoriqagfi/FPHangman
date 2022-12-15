import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class WordTextField extends TextField {
	
	public WordTextField(String text) {
		// set size
		prefWidth(40);
		prefHeight(40);
		setMaxWidth(40);
		setMaxHeight(40);

		setFocusTraversable(false);
		setEditable(false);
		setMouseTransparent(true);
		setStyle("-fx-background-color: rgba(0,0,0,0);");
		setAlignment(Pos.CENTER);
		setText(text);
		setFont();
	}

	private void setFont() {
		setFont(Font.font("System", 11));
	}
}
