

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML private TextField nameField;
    @FXML private Label messageLabel;

    @FXML
    private void onSubmit() {
        String name = nameField.getText();
        messageLabel.setText((name == null || name.isBlank())
                ? "Please enter a name."
                : "Hello, " + name + "!");
    }

    @FXML
    private void onAbout() {
        messageLabel.setText("JavaFX FXML sample UI");
    }
}
