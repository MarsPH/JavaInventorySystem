/*
import java.sql.*;
public  class DemoDB {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/rpg_shop";
            String user = "root";
            String password = "1234";
            String sql = "Select * from items";

            try(Connection conn = DriverManager.getConnection(url, user, password);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(sql)) {

                System.out.println("Connected to database!");

                while (rs.next()) {
                    String name = rs.getString(2); // or column label like "name"
                    System.out.println(name);
                }

                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
 */
