package DAO;
import model.Item;
import util.DBconnection;


import java.sql.*;
import java.util.Scanner;

public class ItemDAO {
    private static final String sql = "SELECT name, price FROM items;\n";
    public void printAllItems() {
        try (Connection conn = DBconnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)
        ) {
            while (rs.next()) {
                String name = rs.getString("name"); // or column label like "name" - USED Label
                String price = rs.getString("price");
                System.out.print(name + " : " + price + "\n");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveItemToDatabase(Item item) {

        try (Connection conn = DBconnection.getConnection() ;
             PreparedStatement pst = conn.prepareStatement("insert into items (name, category, price) values (?, ?, ?)")
        ) {
            pst.setString(1, item.getName()); //sets the first ? as the name of the item to == name
            pst.setString(2, "Weapon");// On default sets this as category
            pst.setInt(3, item.getValue());// sets the price of the item as the price
            pst.executeUpdate();//runs the command
            System.out.println("Item Added Successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
