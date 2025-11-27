package ui;

import java.util.Scanner;

import service.itemService;


public class MainMenu {

    private final ShopkeeperMenu shopkeeperMenu;
    private final Scanner scanner = new Scanner(System.in);


    public MainMenu(itemService itemService) {
        this.shopkeeperMenu = new ShopkeeperMenu(itemService); // FIX
    }

    public void show() {

        while (true) {
            System.out.println("Welcome to RPG_SHOP");
            System.out.println("1: Shopkeep Menu");
            System.out.println("2: Exit\n");
            System.out.println("Select your Option :");
            String userinput = scanner.nextLine().trim();
            switch (userinput) {
                case "1": {
                    shopkeeperMenu.show();
                };
                break;
                case "2": {
                    System.exit(0);
                };
                default: {
                    System.out.println("SOmething not wrking");
                }
                ;
            }
        }
    }
}
