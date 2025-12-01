package ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
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
    @FXML private ComboBox<String> categoryField;
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
        populateCategories();
    }

    @FXML
    private void initialize() {
        stockTable.setEditable(true);
        colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colName.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            item.setName(event.getNewValue());
            itemService.updateItemName(item.getItemId(), event.getNewValue());
        });
        colCategory.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCategory()));
        colCategory.setCellFactory(TextFieldTableCell.forTableColumn());
        colCategory.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            item.setCategory(event.getNewValue());
            itemService.updateItemCategory(item.getItemId(), event.getNewValue());
            populateCategories(); // Refresh categories
        });
        colValue.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getValue()));
        colValue.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.IntegerStringConverter()));
        colValue.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            item.setValue(event.getNewValue());
            itemService.updateItemValue(item.getItemId(), event.getNewValue());
        });
        colQuantity.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getQuantity()));
        colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.IntegerStringConverter()));
        colQuantity.setOnEditCommit(event -> {
            Item item = event.getRowValue();
            item.setQuantity(event.getNewValue());
            itemService.updateItemQuantity(item.getItemId(), event.getNewValue());
        });
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

    private void populateCategories() {
        if (itemService != null) {
            categoryField.getItems().setAll(itemService.getDistinctCategories());
        }
    }

    private void addItem() {
        try {
            String name = nameField.getText().trim();
            String category = categoryField.getValue() != null ? categoryField.getValue().trim() : "";
            int value = Integer.parseInt(valueField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            itemService.addNewItem(name, category, value, quantity);
            refreshStock();
            // Refresh categories after adding
            populateCategories();
        } catch (Exception ex) {
            showError("Invalid Input");
        }
    }

    private void changePrice() {
        Item selectedItem = stockTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showError("Please select an item from the table.");
            return;
        }
        TextInputDialog dialog = new TextInputDialog(String.valueOf(selectedItem.getValue()));
        dialog.setTitle("Change Price");
        dialog.setHeaderText("Change price for " + selectedItem.getName());
        dialog.setContentText("Enter new price:");
        dialog.showAndWait().ifPresent(priceStr -> {
            try {
                int newPrice = Integer.parseInt(priceStr.trim());
                if (newPrice <= 0) {
                    showError("Invalid price.");
                    return;
                }
                itemService.changePrice(selectedItem.getName(), newPrice);
                refreshStock();
            } catch (Exception ex) {
                showError("Invalid Input");
            }
        });
    }

    private void discardItem() {
        Item selectedItem = stockTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            showError("Please select an item from the table.");
            return;
        }
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Discard Item");
        dialog.setHeaderText("Discard " + selectedItem.getName());
        dialog.setContentText("Enter quantity to discard:");
        dialog.showAndWait().ifPresent(quantityStr -> {
            try {
                int quantity = Integer.parseInt(quantityStr.trim());
                if (quantity <= 0 || quantity > selectedItem.getQuantity()) {
                    showError("Invalid quantity.");
                    return;
                }
                itemService.discardItem(selectedItem.getItemId(), quantity);
                refreshStock();
            } catch (Exception ex) {
                showError("Invalid Input");
            }
        });
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
