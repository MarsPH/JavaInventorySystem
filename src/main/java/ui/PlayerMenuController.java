package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Item;
import service.itemService;
import util.NavigationHelper;

public class PlayerMenuController {

    @FXML private TableView<Item> shopTable;
    @FXML private TableColumn<Item, String> shopColName;
    @FXML private TableColumn<Item, String> shopColCategory;
    @FXML private TableColumn<Item, Integer> shopColValue;
    @FXML private TableColumn<Item, Integer> shopColQuantity;
    @FXML private TableView<Item> inventoryTable;
    @FXML private TableColumn<Item, String> invColName;
    @FXML private TableColumn<Item, String> invColCategory;
    @FXML private TableColumn<Item, Integer> invColValue;
    @FXML private TableColumn<Item, Integer> invColQuantity;
    @FXML private TextField quantityField;
    @FXML private Button buyButton;
    @FXML private Button sellButton;
    @FXML private Button backButton;

    private itemService itemService;
    private final ObservableList<Item> shopData = FXCollections.observableArrayList();
    private final ObservableList<Item> inventoryData = FXCollections.observableArrayList();

    public void setItemService(itemService itemService) {
        this.itemService = itemService;
        refreshShop();
        refreshInventory();
    }

    @FXML
    private void initialize() {
        // Shop table
        shopColName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        shopColCategory.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        shopColValue.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getValue()));
        shopColQuantity.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getQuantity()));
        shopTable.setItems(shopData);

        // Inventory table
        invColName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        invColCategory.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        invColValue.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getValue()));
        invColQuantity.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getQuantity()));
        inventoryTable.setItems(inventoryData);

        buyButton.setOnAction(e -> buyItem());
        sellButton.setOnAction(e -> sellItem());
    }

    @FXML
    private void onBack() {
        backButton.getScene().getWindow().hide();
        NavigationHelper.showMainMenuFrom(backButton);
    }

    private void refreshShop() {
        if (itemService != null) {
            shopData.setAll(itemService.getShopStock());
        }
    }

    private void refreshInventory() {
        if (itemService != null) {
            inventoryData.setAll(itemService.getPlayerInventory());
        }
    }

    private void buyItem() {
        Item selected = shopTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an item from the shop stock to buy.");
            return;
        }
        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            itemService.buyItem(selected.getItemId(), quantity);
            refreshShop();
            refreshInventory();
            showAlert("Purchase successful!");
        } catch (Exception ex) {
            showAlert("Error: " + ex.getMessage());
        }
    }

    private void sellItem() {
        Item selected = inventoryTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an item from your inventory to sell.");
            return;
        }
        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            itemService.sellItem(selected.getItemId(), quantity);
            refreshShop();
            refreshInventory();
            showAlert("Sale successful!");
        } catch (Exception ex) {
            showAlert("Error: " + ex.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
