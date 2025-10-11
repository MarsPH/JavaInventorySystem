package ui;

import model.Item;
import service.itemService;  // renamed to proper class name

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
            System.out.println("2: Change prices");
            System.out.println("3: Discard Items");
            System.out.println("4: See Stock");
            System.out.println("5: Back to Main Menu");

            String userinput = scanner.nextLine().trim();
            switch (userinput) {
                case "1" -> addItem(askUserForItem());
                case "2" -> {
                    // somethingg to Change prices
                }
                case "3" -> {
                    discardItem(askUserforItemName());
                }
                case "4" -> viewItems();
                case "5" -> looprunning = false;  // just exit
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

    public void discardItem(Item itemtoDiscard) {
        itemService.discardItem(itemtoDiscard.getName());
    }

    public Item askUserforItemName() {
        while (true) {
            try {
                Item itemtoDiscard;
                String InputName;
                System.out.print("Enter the name of the item to discard: ");
                InputName = scanner.nextLine().trim();
                new Item(InputName);
                itemtoDiscard = new Item(InputName);

                return itemtoDiscard;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }
    }
}
