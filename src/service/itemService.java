package service;

import DAO.itemDAOimpl;
import model.Item;
import java.util.List;

public class itemService {
    private final itemDAOimpl itemDAO;

    public itemService(itemDAOimpl itemDAO) {
        this.itemDAO = itemDAO;
    }

    public List<Item> listAllItems() {
        return itemDAO.getAllItems();
    }

    public void addNewItem(String name,String category,int price) {
        Item item = new Item(name,category , price);
        itemDAO.saveItemToDatabase(item);
    }
}
