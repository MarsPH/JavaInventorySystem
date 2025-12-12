package DAO;

import model.Item;
import util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerInventoryDAO {

    private static final int PLAYER_ID = 1; //  single player for now

    public List<Item> getPlayerInventory() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT i.item_id, i.name, i.category, i.price, pi.quantity " +
                     "FROM player_inventory pi " +
                     "JOIN items i ON pi.item_id = i.item_id " +
                     "WHERE pi.player_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, PLAYER_ID);
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

    public void addItemToInventory(int itemId, int quantity) {
        String sql = "INSERT INTO player_inventory (player_id, item_id, quantity) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE quantity = quantity + ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, PLAYER_ID);
            pst.setInt(2, itemId);
            pst.setInt(3, quantity);
            pst.setInt(4, quantity);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeItemFromInventory(int itemId, int quantity) {
        String sql = "UPDATE player_inventory SET quantity = quantity - ? WHERE player_id = ? AND item_id = ? AND quantity >= ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, quantity);
            pst.setInt(2, PLAYER_ID);
            pst.setInt(3, itemId);
            pst.setInt(4, quantity);
            int rows = pst.executeUpdate();
            if (rows == 0) {
                System.out.println("Not enough quantity or item not found.");
            } else {
                // Remove if quantity <= 0
                String deleteSql = "DELETE FROM player_inventory WHERE player_id = ? AND item_id = ? AND quantity <= 0";
                try (PreparedStatement deletePst = conn.prepareStatement(deleteSql)) {
                    deletePst.setInt(1, PLAYER_ID);
                    deletePst.setInt(2, itemId);
                    deletePst.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getPlayerGold() {
        String sql = "SELECT gold FROM players WHERE player_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, PLAYER_ID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("gold");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void updatePlayerGold(int gold) {
        String sql = "UPDATE players SET gold = ? WHERE player_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, gold);
            pst.setInt(2, PLAYER_ID);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}