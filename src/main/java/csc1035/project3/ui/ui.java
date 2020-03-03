package csc1035.project3.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ui {
    public static ArrayList<String> options = new ArrayList<>(Arrays.asList("lookup", "insert - unique", "insert - csv", "exit"));
    public static void main(String[] args){
        boolean running = true;
        System.out.println("Welcome");
        while(running) {
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
            String[] arguments = new String[]{"123"};  // use if running a main method.
            if (choice.equals("lookup")) {
                csc1035.project3.lookup.stockLookup.main(arguments);
            }
            if (choice.equals("insert - unique")) {
                csc1035.project3.insert.Input.user();
            }
            if (choice.equals("insert - csv")) {
                csc1035.project3.insert.Input.CSV();
            }
            if (choice.equals("exit")) {
                running = false;
                System.out.println("Exiting program, goodbye.");
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
