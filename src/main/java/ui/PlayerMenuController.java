package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.itemService;
import util.NavigationHelper;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMenuController {

    @FXML private Button viewItemsButton;
    @FXML private Button backButton;
    @FXML private TextField filterField;
    @FXML private ListView<String> itemsListView;
    @FXML private Label selectedLabel;

    private itemService itemService; // inject from your app/bootstrap
    private final ObservableList<String> allItems = FXCollections.observableArrayList();
    private final ObservableList<String> filteredItems = FXCollections.observableArrayList();

    public void setItemService(itemService itemService) {
        this.itemService = itemService;
    }

    @FXML
    private void initialize() {
        itemsListView.setItems(filteredItems);
        itemsListView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) ->
                selectedLabel.setText(newV == null ? "None" : newV));
        filterField.textProperty().addListener((obs, oldV, newV) -> applyFilter(newV));
    }

    @FXML
    private void onViewItems() {
        if (itemService == null) {
            // Optional: show alert
            return;
        }
        List<String> items = itemService.listAllItems()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
        allItems.setAll(items);
        applyFilter(filterField.getText());
    }

    @FXML
    private void onBack() {
        // Close the window; replace with navigation if using a scene router
        backButton.getScene().getWindow().hide();
        NavigationHelper.showMainMenuFrom(backButton);}

    @FXML
    private void onClearFilter() {
        filterField.clear();
    }

    private void applyFilter(String query) {
        String q = query == null ? "" : query.trim().toLowerCase();
        if (q.isEmpty()) {
            filteredItems.setAll(allItems);
        } else {
            filteredItems.setAll(
                    allItems.stream()
                            .filter(s -> s.toLowerCase().contains(q))
                            .collect(Collectors.toList())
            );
        }
    }
}
