import com.sun.jdi.Value;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //scanner for the input
        Scanner input = new Scanner(System.in);
        Map<String, Item> map = new HashMap<String, Item>(); // key is the name of the item
        Item item = new Item("axe", 2); // example of adding an item to the map

        boolean running = true; // loop control, it stays true until there's a problem

        Item ItemToPut; // temporary reference created to pass to Item later in the loop (for validation)

        char charChoiceToContinue = 'c';

        do {
            try {
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

                // adds the Item to the map, with the key of its name, so it can be accessed with its name
                map.put(InputName, ItemToPut);

                System.out.println(map.get(InputName).name + " " + map.get(InputName).Value + " Added");

            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

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
}
