package service;

import DAO.itemDAOimpl;
import com.google.protobuf.Value;
import model.Item;
import java.util.List;

public class itemService {
    private itemDAOimpl itemDAO;

    public itemService(itemDAOimpl itemDAO) {
        this.itemDAO = itemDAO;
    }

    public itemService() {
        this.itemDAO = new itemDAOimpl();
    }

    public List<Item> listAllItems() {
        return itemDAO.getAllItems();
    }

    public void addNewItem(String name,String category,int price) {
        Item item = new Item(name,category , price);
        itemDAO.saveItemToDatabase(item);
    }

    public void discardItem(String name)
    {
        Item item= new Item(name);
        itemDAO.dicardItem(item);
    }

    public void changePrice(String name, int Value){
        Item item = new Item(name,Value);
        itemDAO.changePrice(item);
    }
}
