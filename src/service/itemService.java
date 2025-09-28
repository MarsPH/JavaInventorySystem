package service;

import DAO.itemDAO;
import model.Item;
import java.util.List;

public class itemService {
    private final itemDAO itemDAO;

    public itemService(itemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public List<Item> listAllItems() {
        return itemDAO.getAllItems();
    }

    public void addNewItem(String name, int price) {
        Item item = new Item(name, price);
        itemDAO.saveItemToDatabase(item);
    }
}
