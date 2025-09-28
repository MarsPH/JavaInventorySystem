package ui;

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
                case "1" -> addItem();
                case "2" -> {
                    // somethingg to Change prices
                }
                case "3" -> {
                    // same here to discard items
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

    private void addItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        int price = scanner.nextInt();
        scanner.nextLine(); // flush newline

        itemService.addNewItem(name,"Weapon",price);
    }
}
