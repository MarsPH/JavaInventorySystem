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

        map.put(item.name,item);
        System.out.println(map.get("axe").name + " " + map.get("axe").Value);

        System.out.print("Enter item name: ");
        String InputName = input.nextLine();

        System.out.print("Enter item value: ");
        int InputValue = input.nextInt();

        Item item1 = new Item(InputName, InputValue);
        map.put(InputName, item1);

        System.out.println(map.get(InputName).name + " " + map.get(InputName).Value);




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