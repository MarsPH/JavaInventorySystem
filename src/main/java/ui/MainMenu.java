package ui;

import java.util.Scanner;

import service.itemService;


public class MainMenu {

    private final PlayerMenu playerMenu;
    private final ShopkeeperMenu shopkeeperMenu;
    private final Scanner scanner = new Scanner(System.in);


    public MainMenu(itemService itemService) {
        this.playerMenu = new PlayerMenu(itemService);       // FIX
        this.shopkeeperMenu = new ShopkeeperMenu(itemService); // FIX
    }

    public void show() {

        while (true) {
            System.out.println("Welcome to RPG_SHOP");
            System.out.println("1: Player Menu");
            System.out.println("2: Shopkeep Menu");
            System.out.println("3: Exit\n");
            System.out.println("Select your Option :");
            String userinput = scanner.nextLine().trim();
            switch (userinput) {
                case "1": {
                    playerMenu.show();

                };
                break;
                case "2": {
                    shopkeeperMenu.show();
                };
                break;
                case "3": {
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
