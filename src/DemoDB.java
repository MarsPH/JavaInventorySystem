//import java.sql.*;
//public  class DemoDB {
//
//        String url = "jdbc:mysql://localhost:3306/rpg_shop";
//        String user = "root";
//        String password = "canada1234";
//        String sql = "SELECT * FROM players";
//
//        try (Connection conn = DriverManager.getConnection(url, user, password);
//             Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//
//            System.out.println("Connected to database!");
//
//            while (rs.next()) {
//                String name = rs.getString(1); // or column label like "name"
//                System.out.println(name);
//            }
//
//            conn.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
