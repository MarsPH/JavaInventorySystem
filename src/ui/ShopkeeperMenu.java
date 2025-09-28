package ui;
import DAO.itemDAO;
import service.itemService;

import java.util.Scanner;

public class ShopkeeperMenu {

    private itemService itemSer;
    final Scanner scanner = new Scanner(System.in);

    public ShopkeeperMenu(itemService itemService) {
    }


    public void show() {




        System.out.println("1: Add Stock");
        System.out.println("2: Change prices");
        System.out.println("3: Discard Items");
        System.out.println("4: See Stock");
        System.out.println("5: Back to Main Menu");


        boolean looprunning = true;
        while (looprunning) {String userinput = scanner.nextLine().trim();
            switch (userinput) {
                case "1": {

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
                   viewItems();


                }
                case "5": {
                    looprunning = false;
                    MainMenu mainMenu = new MainMenu(itemSer);
                    mainMenu.show();
                }
                break;
                default: {
                    System.out.println("Invalid Input");
                }
            }

        }
    } private void viewItems() {
        itemSer.listAllItems().forEach(System.out::println);
    }

    private void addItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        int price = scanner.nextInt();

        itemSer.addNewItem(name, price);
    }
}
