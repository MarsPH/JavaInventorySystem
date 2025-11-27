package service;

import DAO.itemDAOimpl;
import DAO.PlayerInventoryDAO;
import DAO.ShopStockDAO;
import com.google.protobuf.Value;
import model.Item;
import java.util.List;

public class itemService {
    private itemDAOimpl itemDAO;
    private PlayerInventoryDAO playerInventoryDAO;
    private ShopStockDAO shopStockDAO;

    public itemService(itemDAOimpl itemDAO) {
        this.itemDAO = itemDAO;
        this.playerInventoryDAO = new PlayerInventoryDAO();
        this.shopStockDAO = new ShopStockDAO();
    }

    public itemService() {
        this.itemDAO = new itemDAOimpl();
        this.playerInventoryDAO = new PlayerInventoryDAO();
        this.shopStockDAO = new ShopStockDAO();
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

    public List<Item> getPlayerInventory() {
        return playerInventoryDAO.getPlayerInventory();
    }

    public List<Item> getShopStock() {
        return shopStockDAO.getShopStock();
    }

    public void buyItem(int itemId, int quantity) {
        // Check if shop has enough
        List<Item> shopStock = getShopStock();
        Item shopItem = shopStock.stream().filter(i -> i.getItemId() == itemId).findFirst().orElse(null);
        if (shopItem == null || shopItem.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough items in shop stock.");
        }

        // Add to player inventory
        playerInventoryDAO.addItemToInventory(itemId, quantity);

        // Remove from shop stock
        shopStockDAO.removeItemFromStock(itemId, quantity);

        // TODO: Record transaction
    }

    public void sellItem(int itemId, int quantity) {
        // Check if player has enough
        List<Item> playerInventory = getPlayerInventory();
        Item playerItem = playerInventory.stream().filter(i -> i.getItemId() == itemId).findFirst().orElse(null);
        if (playerItem == null || playerItem.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough items in player inventory.");
        }

        // Remove from player inventory
        playerInventoryDAO.removeItemFromInventory(itemId, quantity);

        // Add to shop stock
        shopStockDAO.addItemToStock(itemId, quantity);

        // TODO: Record transaction
    }
}
