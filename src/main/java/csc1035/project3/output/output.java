package csc1035.project3.output;

import csc1035.project3.insert.Table_Initializer;

import java.util.List;

public class output {
    public static void output(List<Table_Initializer> data){

        int max_length = 10;
        for(Table_Initializer item: data){
            if(item.getName().length() > max_length){
                max_length = item.getName().length();
            }
        }

        String align = "| %-15s | %-"+max_length+"s | %-10s | %-10s | %-10s | %-10s | %-10s |%n";  // Formatting key for table

        String row_text = String.format(String.format("%%%ds", max_length + 2), " ").replace(" ", "-");
        String title_text = String.format(String.format("%%%ds", max_length - 9), " ");

        System.out.format("+-----------------+"+row_text+"+------------+------------+------------+------------+------------+%n");
        System.out.format("| Item ID         | Item Name"+title_text+" |  Quantity  | Category   | Cost       | Sell Price | Perishable |%n");
        System.out.format("+-----------------+"+row_text+"+------------+------------+------------+------------+------------+%n");
        for (Table_Initializer item : data) {
            System.out.format(align, item.getId(), item.getName(), item.getStock(), item.getCategory(), item.getCost(), item.getSell_price(), item.getPerishable());
            // Prints out database row
        }
        System.out.format("+-----------------+"+row_text+"+------------+------------+------------+------------+------------+%n");
    }

}
