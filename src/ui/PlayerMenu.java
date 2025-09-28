package ui;

import service.itemService;
import java.util.Scanner;

public class PlayerMenu {
    private final itemService itemService;
    private final Scanner scanner = new Scanner(System.in);

    public PlayerMenu(itemService itemService) {
        this.itemService = itemService;
    }

    public void show() {
        System.out.println("--- Player Menu ---");
        System.out.println("1. View Items");
        System.out.println("2. Back");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> itemService.listAllItems().forEach(System.out::println);
            case 2 -> { return; }
        }
    }
}
