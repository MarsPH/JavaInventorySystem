import DAO.itemDAO;
import ui.MainMenu;
import  model.Item;
import service.*;

import java.util.*;

public class Main {

    //private static final String sql = "SELECT name, price FROM items;\n";

    public static void main(String[] args) {
        itemDAO itemDAO = new itemDAO();
        itemService itemService = new itemService(itemDAO);
        MainMenu menu = new MainMenu(itemService);
        menu.show();

/*
        //if any exception is run to, it will be set to false
        boolean running = true;

        while (running) {

            Item item = askUserForItem();// gets the input from the user to set data to the database + validation
            itemDAO.saveItemToDatabase(item);// set the data from created item from input into the database
            running = askUserToContinue();// asks if the user want to add another item
        }
       // itemDAO.printAllItems();// after the inputs, it shows the table of items

    }*/



 /*   public static Item askUserForItem() {
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





    public static boolean askUserToContinue() {
        char charChoiceToContinue = 'c';
        Scanner input = new Scanner(System.in);


        System.out.println("Write C to Continue, Write other keys to quit");

        String line = input.nextLine().trim().toLowerCase();
        char choice = line.isEmpty() ? ' ' : line.charAt(0);

        return choice == charChoiceToContinue;
    }
}
*/

    }}