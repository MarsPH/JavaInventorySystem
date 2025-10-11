package DAO;

import java.util.List;

import model.Item;

public interface itemDAO {
    List<Item> getAllItems();

    void saveItemToDatabase(Item item);

    void dicardItem(Item item);
}
