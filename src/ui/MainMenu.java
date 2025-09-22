package ui;

import java.util.Scanner;


public class MainMenu {

    public void Start() {
        final Scanner input = new Scanner(System.in);
        System.out.println("Welcome to RPG_SHOP");
        System.out.println("1: Player Menu");
        System.out.println("2: Shopkeep Menu");
        System.out.println("3: Exit\n");
        System.out.println("Select your Option :");


        boolean looprunning = true;
        while (looprunning) { String userinput = input.nextLine().trim();
        switch (userinput) {
            case "1": { looprunning = false;
                PlayerMenu pMenu = new PlayerMenu();
                pMenu.playerMenu();

            }
            ;
            break;
            case "2": { looprunning = false;
                ShopkeeperMenu sMenu = new ShopkeeperMenu();
                sMenu.shopkeeperMenu();
            }
            ;
            break;
            case "3": { looprunning = false;
                System.exit(0);
            }
            ;

            default: {
                System.out.println("SOmething not wrking");
            }
            ;
        }
    }
}}
