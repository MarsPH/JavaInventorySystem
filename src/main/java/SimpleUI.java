import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleUI extends Application {
    @Override
    public void start(Stage stage) {
        Label label = new Label("Enter your name:");
        TextField input = new TextField();
        Button button = new Button("Submit");
        Label output = new Label();

        button.setOnAction(e -> {
            String name = input.getText().trim();
            if (name.isEmpty()) {
                output.setText("No input.");
            } else {
                output.setText("Hello, " + name + ".");
            }
        });

        VBox layout = new VBox(10, label, input, button, output);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-font-size: 14;");

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Simple JavaFX UI");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
