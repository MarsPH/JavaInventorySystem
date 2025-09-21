
import com.sun.jdi.Value;

import java.sql.*;
import java.util.*;

public class Main {
    private static String url = "jdbc:mysql://localhost:3306/rpg_shop";
    private static String user = "root";
    private static String password = "1234";
    private static String sql = "SELECT name, price FROM items;\n";

    public static void main(String[] args) {

        //scanner for the input
        //Map<String, Item> map = new HashMap<String, Item>(); // key is the name of the item
        //Item item = new Item("axe", 2); // example of adding an item to the map

        boolean running = true;

        while (running) {

            Item item = Main.askUserForItem();
            saveItemToDatabase(item);
            running = askUserToContinue();
        }
        printAllItems();

    }

    public static Item askUserForItem() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);

                Item ItemToPut; // temporary reference created to pass to Item later in the loop (for validation)
                String InputName = "";
                System.out.print("Enter item name:");
                InputName = input.nextLine(); // gets the nextline input from the user

                // validates it here, so if constructor has problem with InputName,
                // the user will notify now other than later
                ItemToPut = new Item(InputName, 0);

                System.out.print("Enter item value: ");
                int InputValue = Integer.parseInt(input.nextLine()); // parse into int because the value is int

                // try to construct Item, if no exception thrown, then it means it's good
                ItemToPut = new Item(InputName, InputValue);
                return ItemToPut;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }

    }

    public static void saveItemToDatabase(Item item) {

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement st = conn.createStatement();
             PreparedStatement pst = conn.prepareStatement("insert into items (name, category, price) values (?, ?, ?)");
        ) {
            pst.setString(1, item.getName());
            pst.setString(2, "Weapon");
            pst.setInt(3, item.getValue());
            pst.executeUpdate();
            System.out.println("Connected to database!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printAllItems() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql);
        ) {
            while (rs.next()) {
                String name = rs.getString("name"); // or column label like "name"
                String price = rs.getString("price");
                System.out.print(name + " : " + price + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean askUserToContinue() {
        char charChoiceToContinue = 'c';
        Scanner input = new Scanner(System.in);

        System.out.println("Write C to Continue, Write other keys to quit");

        String line = input.nextLine().trim().toLowerCase();
        char choice = line.isEmpty() ? ' ' : line.charAt(0);

        if (choice == charChoiceToContinue) {
            return true;
        } else {
            return false;
        }
    }
}


