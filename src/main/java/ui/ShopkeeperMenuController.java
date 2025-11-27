package ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.stage.Stage;
import model.Item;
import service.itemService;
import util.NavigationHelper;

public class ShopkeeperMenuController {

    @FXML private TableView<Item> stockTable;
    @FXML private TableColumn<Item, String> colName;
    @FXML private TableColumn<Item, String> colCategory;
    @FXML private TableColumn<Item, Integer> colValue;
    @FXML private TableColumn<Item, Integer> colQuantity;
    @FXML private TextField nameField;
    @FXML private TextField categoryField;
    @FXML private TextField valueField;
    @FXML private TextField quantityField;
    @FXML private Button addBtn;
    @FXML private Button changeBtn;
    @FXML private Button discardBtn;
    @FXML private Button backBtn;

    private itemService itemService;
    private final ObservableList<Item> stockData = FXCollections.observableArrayList();

    public void setService(itemService service) {
        this.itemService = service;
        refreshStock();
    }

    @FXML
    private void initialize() {
        colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        colCategory.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        colValue.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getValue()));
        colQuantity.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getQuantity()));
        stockTable.setItems(stockData);

        addBtn.setOnAction(e -> addItem());
        changeBtn.setOnAction(e -> changePrice());
        discardBtn.setOnAction(e -> discardItem());

    }
    @FXML
    private void onBack() {
        // Close the window; replace with navigation if using a scene router
        backBtn.getScene().getWindow().hide();
        NavigationHelper.showMainMenuFrom(backBtn);}

    private void refreshStock() {
        if (itemService != null) {
            stockData.setAll(itemService.getShopStock());
        }
    }

    private void addItem() {
        try {
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            int value = Integer.parseInt(valueField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            itemService.addNewItem(name, category, value, quantity);
            refreshStock();
        } catch (Exception ex) {
            showError("Invalid Input");
        }
    }

    private void changePrice() {
        try {
            String name = nameField.getText().trim();
            int value = Integer.parseInt(valueField.getText().trim());
            itemService.changePrice(name, value);
            refreshStock();
        } catch (Exception ex) {
            showError("Invalid Input");
        }
    }

    private void discardItem() {
        try {
            String name = nameField.getText().trim();
            itemService.discardItem(name);
            refreshStock();
        } catch (Exception ex) {
            showError("Invalid Input");
        }
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
