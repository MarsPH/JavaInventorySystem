import com.sun.jdi.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //scanner for the input
        Scanner input = new Scanner(System.in);
        Map<String, Item> map = new HashMap<String, Item>();//key is the name of the item
        Item item = new Item("axe", 2);// example of adding an item to the map

        boolean running = true;
        Item ItemToPut;

        char charChoiceToClose = 'c';

        do {
            try {
                String InputName = "";
                System.out.print("Enter item name:");
                InputName = input.nextLine();


                System.out.print("Enter item value: ");
                int InputValue = Integer.parseInt(input.nextLine()); // parse into int


                ItemToPut = new Item(InputName, InputValue);

                map.put(InputName, ItemToPut);

                System.out.println(map.get(InputName).name + " " + map.get(InputName).Value + " Added");

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("Press C to Exit");
            char choice = input.nextLine().trim().toLowerCase().charAt(0);
            if (choice == charChoiceToClose) {
                running = false;
            }

        } while (running);


        for (String key : map.keySet()) {
            System.out.println("Item Name: " + key);
            System.out.println("Value: " + map.get(key).Value);
        }






        //System.out.println(map.get(InputName).name + " " + map.get("axe").Value);


    }
}

/*
         Stack<Item> ItemStack = new Stack<>();
         Stack<Item> ItemStackShown = new Stack<>();

         Item item = new Item("axe", 2);
         ItemStack.push(item);

        item = new Item("sword", 5);
        ItemStack.push(item);

         //loops through stack and pop one by one
         while (!ItemStack.isEmpty()) {
             Item itemToShow = ItemStack.pop();
             System.out.println(itemToShow.name + " with a value of " +itemToShow.Value);
             ItemStackShown.push(itemToShow);
         }
         while (!ItemStackShown.isEmpty()) {
             ItemStack.push(ItemStackShown.pop());
         }

         String ItemNameToLookFor = "axe";
        int search = ItemStack.search(ItemNameToLookFor);
        System.out.println("Price of " + ItemNameToLookFor + " is " + search);
        */