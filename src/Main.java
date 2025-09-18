import com.sun.jdi.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map<String, Item> map = new HashMap<String, Item>();
        Item item = new Item("axe", 2);

        boolean inputTrue = true;
        Item ItemToPut;

        char charInputToClose = 'c';

        do{
            String InputName;
            System.out.println("Enter item name:");
            InputName = input.nextLine();

            System.out.println("Enter item value: ");
            int InputValue = input.nextInt();


            ItemToPut = new Item(InputName, InputValue);

            map.put(InputName, ItemToPut);

            System.out.println(map.get(InputName).name + " " + map.get(InputName).Value + " Added");
            //System.out.println("Press C to Exit");

            //input.nextLine(); // clears the newline
            //charInputToClose = input.next().charAt(0);


        } while (true);

        /*
        for (String key : map.keySet()) {
            System.out.println("Key: " + key);
            System.out.println("Value: " + map.get(key).Value);
        }

         */




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