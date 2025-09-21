
import com.sun.jdi.Value;

import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //scanner for the input
        //Map<String, Item> map = new HashMap<String, Item>(); // key is the name of the item
        //Item item = new Item("axe", 2); // example of adding an item to the map




        char charChoiceToContinue = 'c';

        do {

                Item item = Main.askUserForItem();
                // adds the Item to the map, with the key of its name, so it can be accessed with its name
                //map.put(InputName, ItemToPut);



            //System.out.println(map.get(InputName).name + " " + map.get(InputName).Value + " Added");

            System.out.println("Write C to Continue, Write other keys to quit");

            String line = input.nextLine().trim().toLowerCase();
            char choice = line.isEmpty() ? ' ' : line.charAt(0);

            if (choice == charChoiceToContinue) {
                running = true;
            } else {
                running = false;
            }

        } while (running);

        System.out.println("Items:");
        System.out.println("---------------------");
        for (String key : map.keySet()) {
            System.out.println("Item Name: " + key);
            System.out.println("Value: " + map.get(key).Value);
            System.out.println("---------------------");
        }

         Stack<Item> ItemStack = new Stack<>();
        Stack<Item> ItemStackShown = new Stack<>();

//         Item item = new Item("axe", 2);
//         ItemStack.push(item);

        item = new Item("sword", 5);
         ItemStack.push(item);

        // loops through stack and pop one by one
        while (!ItemStack.isEmpty()) {
            Item itemToShow = ItemStack.pop();
           System.out.println(itemToShow.name + " with a value of " + itemToShow.Value);
             ItemStackShown.push(itemToShow);
         }
         while (!ItemStackShown.isEmpty()) {
             ItemStack.push(ItemStackShown.pop());
         }

         String ItemNameToLookFor = "axe";
         int search = ItemStack.search(ItemNameToLookFor);
         System.out.println("Price of " + ItemNameToLookFor + " is " + search);
    }
    public static Item askUserForItem(){
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
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input");
            return null;
        }
    }

    public static void saveItemToDatabase(Item item){
        String url = "jdbc:mysql://localhost:3306/rpg_shop";
        String user = "root";
        String password = "1234";
        String sql = "SELECT name, price FROM items;\n";

        try(Connection conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            PreparedStatement pst = conn.prepareStatement("insert into items (name, category, price) values (?, ?, ?)");
        )
        {

            pst.setString(1, item.getName());
            pst.setString(2,"Weapon");
            pst.setInt(3, item.getValue());
            pst.executeUpdate();
            System.out.println("Connected to database!");

            ResultSet rs1 = st.executeQuery("select * from items;");
            while (rs1.next()) {
                String name = rs1.getString("name"); // or column label like "name"
                String price = rs1.getString("price");
                System.out.print(name + " : " + price + "\n");
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


