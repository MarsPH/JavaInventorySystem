import ui.MainMenu;

import java.sql.*;
import java.util.*;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/rpg_shop";
    private static final String user = "root";
    private static final String password = "1234";
    private static final String sql = "SELECT name, price FROM items;\n";

    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
       mainMenu.Start();

        //if any exception is run to, it will be set to false
        boolean running = true;

        while (running) {

            Item item = askUserForItem();// gets the input from the user to set data to the database + validation
            saveItemToDatabase(item);// set the data from created item from input into the database
            running = askUserToContinue();// asks if the user want to add another item
        }
        printAllItems();// after the inputs, it shows the table of items

    }



    public static Item askUserForItem() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);

                Item ItemToPut; // temporary reference created to pass to Item later in the loop (for validation)
                String InputName;
                System.out.print("Enter item name:");
                InputName = input.nextLine(); // gets the nextline input from the user

                // validates it here, so if constructor has problem with InputName,
                // the user will notify now other than later
                new Item(InputName, 0);

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
             PreparedStatement pst = conn.prepareStatement("insert into items (name, category, price) values (?, ?, ?)")
        ) {
            pst.setString(1, item.getName()); //sets the first ? as the name of the item to == name
            pst.setString(2, "Weapon");// On default sets this as category
            pst.setInt(3, item.getValue());// sets the price of the item as the price
            pst.executeUpdate();//runs the command
            System.out.println("Connected to database!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printAllItems() {
        try (Connection conn = DriverManager.getConnection(url, user, password);
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

    public static boolean askUserToContinue() {
        char charChoiceToContinue = 'c';
        Scanner input = new Scanner(System.in);


        System.out.println("Write C to Continue, Write other keys to quit");

        String line = input.nextLine().trim().toLowerCase();
        char choice = line.isEmpty() ? ' ' : line.charAt(0);

        return choice == charChoiceToContinue;
    }
}


