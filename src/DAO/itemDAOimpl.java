package DAO;
import model.Item;
import util.DBconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class itemDAOimpl implements itemDAO {

    private static final String SELECT_ALL = "SELECT name, category, price FROM items";

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try (Connection conn = DBconnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT_ALL)) {

            while (rs.next()) {
                Item item = new Item(
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getInt("price")
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
}
