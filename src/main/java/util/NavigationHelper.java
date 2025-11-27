package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public final class NavigationHelper {
    private NavigationHelper() {}

    // Optional: cache the loaded Main Menu to avoid reloading FXML each time.
    private static Parent cachedMainMenu;

    public static void showMainMenuFrom(Node anyNodeInScene) {
        try {
            Stage stage = (Stage) anyNodeInScene.getScene().getWindow();
            if (stage == null) return;
            Parent fresh = FXMLLoader.load(NavigationHelper.class.getResource("/RPGMainMenu.fxml"));
            if (stage.getScene() == null) {
                stage.setScene(new Scene(fresh));
            } else {
                stage.getScene().setRoot(fresh);
            }
            stage.show();

            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            // Consider logging + user-friendly alert
        }
    }

    private static Parent getMainMenuRoot() throws Exception {
        if (cachedMainMenu == null) {
            cachedMainMenu = FXMLLoader.load(
                    NavigationHelper.class.getResource("/RPGMainMenu.fxml")
            );
        }
        return cachedMainMenu;
    }

    // If you want to force reload (not cached), add this:
    public static void showMainMenuFresh(Node anyNodeInScene) {
        try {
            Stage stage = (Stage) anyNodeInScene.getScene().getWindow();
            if (stage == null) return;

            Parent root = FXMLLoader.load(
                    NavigationHelper.class.getResource("/RPGMainMenu.fxml")
            );
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
