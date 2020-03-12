package csc1035.project3.receipt;

import csc1035.project3.insert.Input;
import csc1035.project3.lookup.stockLookup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class tempShopBasket {
    static int items = 0;
    public static ArrayList<String> options = new ArrayList<>(Arrays.asList("new item", "view current cost","checkout", "abandon"));


    public static void main(String[] args) {
        menu();
    }

    public static void checkItemInStock(){
        // We need to make sure the item exists and is in stock.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the item.");
        System.out.print(">>>  ");
        String choice = "";
        choice = scanner.nextLine().toLowerCase();
        stockLookup test = new stockLookup();
        test.remoteLookup(choice);
    }

    public static void menu(){
        if(items == 0){
            System.out.println("Basket is currently empty.");
        }
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        String choice = "";
        while (!valid) {
            System.out.println("Please enter desired command or type 'help' for list of commands.");
            System.out.print(">>>  ");
            choice = scanner.nextLine().toLowerCase();
            if (options.contains(choice)) {
                valid = true;
            } else if (choice.equals("help")) {
                 help();
            } else {
                System.out.println("Invalid Choice, please try again");
            }
        }
            switch (choice){
                case "new item":
                    checkItemInStock();
                    // Next Item
                    break;
                case "view current cost":
                    System.out.println("b");
                    // View Current Cost
                    break;
                case "checkout":
                    System.out.println("c");

                    // Checkout
                    break;
                case "abandon":
                    System.out.println("d");

                    // Abandon
                    break;

        }

    }
    private static void help(){
        for(String option : options){
            System.out.println(option);
        }
    }
}
