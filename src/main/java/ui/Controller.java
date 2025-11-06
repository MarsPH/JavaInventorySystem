
package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import service.itemService;

public class Controller {

    @FXML
    private Button playerButton;
    @FXML
    private Button shopButton;
    @FXML
    private Button exitButton;

    @FXML
    private void initialize() {
        playerButton.setOnAction(e -> openPlayerMenu());
        shopButton.setOnAction(e -> openShopkeeperMenu());
        exitButton.setOnAction(e -> ((Stage) exitButton.getScene().getWindow()).close());
    }

    private void openPlayerMenu() {
        // You can add logic later for player menu
        System.out.println("Player Menu clicked.");
    }

    private void openShopkeeperMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShopKeeperMenu.fxml"));
            Parent root = loader.load();

            // Pass your service to controller
            ShopkeeperMenuController controller = loader.getController();
            controller.setService(new itemService());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Shopkeeper Menu");
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
