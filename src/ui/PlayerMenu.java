package ui;

import java.util.Scanner;

public class PlayerMenu {


    public void playerMenu() {

        System.out.println("1: Buy");
        System.out.println("2: Sell");
        System.out.println("3: Back to Main Menu");
        //  System.out.println("4: Exit");

        final Scanner input = new Scanner(System.in);

        boolean looprunning = true;
        while (looprunning) { String userinput = input.nextLine().trim();
            switch (userinput) {
                case "1": {
                    // i will add the functionality to buy the stuff
                    looprunning = false;
                }
                ;
                break;
                case "2": {
                    // functionality to seell will  be added here
                    looprunning = false;
                }
                case "3": {
                    looprunning = false;

                    MainMenu mainMenu = new MainMenu();
                    mainMenu.Start();

                }
                ;
                break;
                default: {
                    System.out.println("Incorrect input");
                }
            }
        }

    }
}