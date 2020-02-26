package csc1035.project3.lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class stockLookup {
    public static void main(String[] args){
        System.out.println("Beginning Lookup");
        Item test_item = new Item(1, "Gold Ring", 4 );
        List<Item> test_list = new ArrayList<>();
        test_list.add(test_item);
        output(test_list);
        get_data_id();
    }

    public static void get_data_id(){
        List<Item> data = new ArrayList<>();

        Scanner id_scanner = new Scanner(System.in);
        System.out.print("Enter item ID \n>>>  ");
        String searchItem = id_scanner.nextLine();
        System.out.println("Searching for : " + searchItem);

        // Code here to search DB once its made

        // Code here to run output with the list
    }

    public static void output(List<Item> data){
        String align = "| %-15s | %-10s | %-10s |%n";

        System.out.format("+-----------------+------------+------------+%n");
        System.out.format("| Item ID         | Item Name  |  Quantity  |%n");
        System.out.format("+-----------------+------------+------------+%n");
        for (Item item : data) {
            System.out.format(align, item.id, item.name, item.quantity);
        }
        System.out.format("+-----------------+------------+------------+%n");
    }

}
