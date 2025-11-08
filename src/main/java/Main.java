import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass().getResource("/RPGMainMenu.fxml"));

        Parent root = FXMLLoader.load(getClass().getResource("/RPGMainMenu.fxml"));
        stage.setTitle("JavaFX FXML Sample");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
