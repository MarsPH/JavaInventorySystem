package ui;

import model.Item;
import service.itemService;

import java.util.Scanner;

public class ShopkeeperMenu {
    private final itemService itemService;
    private final Scanner scanner = new Scanner(System.in);

    public ShopkeeperMenu(itemService itemService) {
        this.itemService = itemService;
    }

    public void show() {
        boolean looprunning = true;
        while (looprunning) {
            System.out.println("\n--- Shopkeeper Menu ---");
            System.out.println("1: Add Stock");
            System.out.println("2: Change Prices");
            System.out.println("3: Discard Items");
            System.out.println("4: See Stock");
            System.out.println("5: Back to Main Menu");

            String userinput = scanner.nextLine().trim();
            switch (userinput) {
                case "1" -> addItem(askUserForItem());
                case "2" -> changePrice(askUserForPriceChange());
                case "3" -> discardItem(askUserForItemName());
                case "4" -> viewItems();
                case "5" -> looprunning = false;
                default -> System.out.println("Invalid Input");
            }
        }
    }

    private void viewItems() {
        itemService.listAllItems().forEach(System.out::println);
    }

    private void addItem(Item itemToAdd) {
        itemService.addNewItem(itemToAdd.getName(), itemToAdd.getCategory(), itemToAdd.getValue());
    }

    private String askUserForName() {
        System.out.print("Enter item name: ");
        return scanner.nextLine().trim();
    }

    private String askUserForCategory() {
        System.out.print("Enter item category: ");
        return scanner.nextLine().trim();
    }

    private int askUserForValue() {
        System.out.print("Enter item value: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number, defaulting to 0.");
            return 0;
        }
    }

    public Item askUserForItem() {
        String name = askUserForName();
        int value = askUserForValue();
        String category = askUserForCategory();
        return new Item(name, category, value);
    }

    public Item askUserForItemName() {
        String name = askUserForName();
        return new Item(name);
    }

    public Item askUserForPriceChange() {
        String name = askUserForName();
        int value = askUserForValue();
        return new Item(name, "", value); // category placeholder if constructor requires it
    }

    public void discardItem(Item itemToDiscard) {
        itemService.discardItem(itemToDiscard.getName());
    }

    public void changePrice(Item itemForPriceChange) {
        itemService.changePrice(itemForPriceChange.getName(), itemForPriceChange.getValue());
    }
}


/*

    public Item askUserForItem() {
        while (true) {
            try {
                Item ItemToPut; // temporary reference created to pass to Item later in the loop (for validation)
                String InputName;
                System.out.print("Enter item name:");
                InputName = scanner.nextLine(); // gets the nextline input from the user

                // validates it here, so if constructor has problem with InputName,
                // the user will notify now other than later
                new Item(InputName, "", 0);

                System.out.print("Enter item value: ");
                int InputValue = Integer.parseInt(scanner.nextLine()); // parse into int because the value is int

                // validates it here, so if constructor has problem with InputValue,
                // the user will notify now other than later
                new Item(InputName, "", InputValue);

                System.out.print("Enter item Category: ");
                String InputCategory = scanner.nextLine();

                // try to construct Item, if no exception thrown, then it means it's good
                ItemToPut = new Item(InputName, InputCategory, InputValue);
                return ItemToPut;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }
    }


    public Item askUserforItemName() {
        while (true) {
            try {
                Item itemtoDiscard;
                String InputName;
                System.out.print("Enter the name of the item ");
                InputName = scanner.nextLine().trim();
                new Item(InputName);
                itemtoDiscard = new Item(InputName);

                return itemtoDiscard;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }
    }*/
