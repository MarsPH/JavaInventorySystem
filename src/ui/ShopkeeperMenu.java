package ui;
import DAO.ItemDAO;

import java.util.Scanner;

public class ShopkeeperMenu {

    public void shopkeeperMenu() {

        final Scanner input = new Scanner(System.in);

        System.out.println("1: Add Stock");
        System.out.println("2: Change prices");
        System.out.println("3: Discard Items");
        System.out.println("4: See Stock");
        System.out.println("5: Back to Main Menu");


        boolean looprunning = true;
        while (looprunning) {String userinput = input.nextLine().trim();
            switch (userinput) {
                case "1": {
                    // functionality to add stock
                    looprunning = false;
                }
                break;
                case "2": {
                    // some more vnnsvm
                    looprunning = false;
                }
                break;
                case "3": {
                    //jvsjfjfksdvnbjdfnvj
                    looprunning = false;
                }
                break;
                case"4":{
                    ItemDAO itemDAO =new ItemDAO();
                    itemDAO.printAllItems();


                }
                case "5": {
                    looprunning = false;
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.Start();
                }
                break;
                default: {
                    System.out.println("Invalid Input");
                }
            }

        }
    }
}
