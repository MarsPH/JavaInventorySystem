package DAO;

import model.Item;
import util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class itemDAOimpl implements itemDAO {

    private static final String SELECT_ALL = "SELECT item_id, name, category, price FROM items";

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try (Connection conn = DBconnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL)) {

            while (rs.next()) {
                Item item = new Item(
                        rs.getInt("item_id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("price"),
                        0 // quantity not used here
                );
                items.add(item);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public void saveItemToDatabase(Item item) {
        String sql = "INSERT INTO items (name,category, price) VALUES (?,?, ?)";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, item.getName());
            pst.setString(2, item.getCategory());
            pst.setInt(3, item.getValue());
            pst.executeUpdate();

            System.out.println("Item added successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dicardItem(Item item) {
        String sql = "DELETE FROM items WHERE name =?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, item.getName());
            int rows = pst.executeUpdate();
            if (rows == 0) {
                System.out.println("No item found with that name.");
            } else {
                System.out.println(rows + "Item Deleted Successfully , i think soo");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void changePrice(Item item) {
        String sql = "UPDATE items SET price = ? WHERE name = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, item.getValue());
            pst.setString(2,item.getName());

            int rows = pst.executeUpdate();
            if (rows == 0) {
                System.out.println("No item found with that name.");
            } else {
                System.out.println(rows + "Item price changed Successfully , i think soo");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getDistinctCategories() {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT DISTINCT category FROM items";
        try (Connection conn = DBconnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                categories.add(rs.getString("category"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categories;
    }

    public void updateItemName(int itemId, String newName) {
        String sql = "UPDATE items SET name = ? WHERE item_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, newName);
            pst.setInt(2, itemId);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateItemCategory(int itemId, String newCategory) {
        String sql = "UPDATE items SET category = ? WHERE item_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, newCategory);
            pst.setInt(2, itemId);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateItemValue(int itemId, int newValue) {
        String sql = "UPDATE items SET price = ? WHERE item_id = ?";
        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, newValue);
            pst.setInt(2, itemId);
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
