package DAO;

import model.Item;
import util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopStockDAO {

    private static final int SHOP_ID = 1; // Assuming single shop

    public List<Item> getShopStock() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT i.item_id, i.name, i.category, i.price, ss.quantity " +
                     "FROM shop_stock ss " +
                     "JOIN items i ON ss.item_id = i.item_id " +
                     "WHERE ss.shop_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, SHOP_ID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        rs.getInt("quantity")
                );
                items.add(item);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public void addItemToStock(int itemId, int quantity) {
        String sql = "INSERT INTO shop_stock (shop_id, item_id, quantity) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantity = quantity + ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, SHOP_ID);
            pst.setInt(2, itemId);
            pst.setInt(3, quantity);
            pst.setInt(4, quantity);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeItemFromStock(int itemId, int quantity) {
        String sql = "UPDATE shop_stock SET quantity = quantity - ? WHERE shop_id = ? AND item_id = ? AND quantity >= ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, quantity);
            pst.setInt(2, SHOP_ID);
            pst.setInt(3, itemId);
            pst.setInt(4, quantity);
            int rows = pst.executeUpdate();
            if (rows == 0) {
                System.out.println("Not enough quantity or item not found.");
            } else {
                // Remove if quantity <= 0
                String deleteSql = "DELETE FROM shop_stock WHERE shop_id = ? AND item_id = ? AND quantity <= 0";
                try (PreparedStatement deletePst = conn.prepareStatement(deleteSql)) {
                    deletePst.setInt(1, SHOP_ID);
                    deletePst.setInt(2, itemId);
                    deletePst.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}