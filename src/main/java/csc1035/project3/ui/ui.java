package csc1035.project3.ui;
import csc1035.project3.insert.Input;
import csc1035.project3.lookup.stockLookup;
import csc1035.project3.update.update;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ui {
    public static ArrayList<String> options = new ArrayList<>(Arrays.asList("lookup", "insert - unique", "insert - csv", "update", "exit"));
    public static void main(String[] args) throws FileNotFoundException {
        boolean running = true;
        System.out.println("Welcome");
        while(running) {
            System.out.println("Type \"help\" to see options");
            System.out.println("What would you like to do?");
            Scanner scanner = new Scanner(System.in);
            boolean valid = false;
            String choice = "";
            while (!valid) {
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
            /*
            * Please add your functions below.
            */

            switch (choice){
                case "lookup":
                    stockLookup stock = new stockLookup();
                    stock.start();
                    break;
                case "insert - unique":
                    Input in_user = new Input();
                    in_user.user();
                    break;
                case "insert - csv":
                    Input in_csv = new Input();
                    in_csv.CSV();
                    break;
                case "update":
                    update update = new update();
                    update.modify();
                    break;
                case "shop":
                    // Insert function to begin shopping
                case "exit":
                    running = false;
                    csc1035.project3.warning.warning_quantity.warning();
                    System.out.println("Exiting program, goodbye.");
                    break;
            }
        }

    }

    /**
     *  Outputs a help message for the ui
     */
    private static void help(){
        for(String option : options){
            System.out.println(option);
        }
    }
}
