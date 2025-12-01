package service;

import DAO.itemDAOimpl;
import DAO.PlayerInventoryDAO;
import DAO.ShopStockDAO;
import com.google.protobuf.Value;
import model.Item;
import patterns.ItemBuilder;
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
        addNewItem(name, category, price, 10); // Default quantity 10
    }

    public void addNewItem(String name,String category,int price, int quantity) {
        Item item = new ItemBuilder().name(name).category(category).value(price).build();
        itemDAO.saveItemToDatabase(item);
        // To get the id, we can query the item by name
        List<Item> allItems = itemDAO.getAllItems();
        Item addedItem = allItems.stream().filter(i -> i.getName().equals(name)).findFirst().orElse(null);
        if (addedItem != null) {
            shopStockDAO.addItemToStock(addedItem.getItemId(), quantity);
        }
    }

    public void discardItem(String name)
    {
        Item item= new ItemBuilder().name(name).build();
        itemDAO.dicardItem(item);
    }

    public void changePrice(String name, int Value){
        Item item = new ItemBuilder().name(name).value(Value).build();
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

        // Check gold
        int totalPrice = shopItem.getValue() * quantity;
        int currentGold = playerInventoryDAO.getPlayerGold();
        if (currentGold < totalPrice) {
            throw new IllegalArgumentException("Not enough gold.");
        }
        playerInventoryDAO.updatePlayerGold(currentGold - totalPrice);

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

        // Check gold
        int totalPrice = playerItem.getValue() * quantity;
        int currentGold = playerInventoryDAO.getPlayerGold();
        playerInventoryDAO.updatePlayerGold(currentGold + totalPrice);

        // Remove from player inventory
        playerInventoryDAO.removeItemFromInventory(itemId, quantity);

        // Add to shop stock
        shopStockDAO.addItemToStock(itemId, quantity);

        // TODO: Record transaction
    }

    public int getPlayerGold() {
        return playerInventoryDAO.getPlayerGold();
    }
}
