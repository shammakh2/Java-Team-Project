package csc1035.project3.insert;

import java.io.*;
import java.util.*;

public class Input {

    public static void CSV() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter data file path (normally path is test_data.csv) or press the enter key to skip: ");
            String path = scanner.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] newArray = line.split(",");
                int id = Integer.parseInt(newArray[0]);
                boolean b = Boolean.parseBoolean(newArray[3]);
                float cost = Float.parseFloat(newArray[4]);
                int stock = Integer.parseInt(newArray[5]);
                float sell_price = Float.parseFloat(newArray[6]);
                Table_Initializer entry = new Table_Initializer(id, newArray[1], newArray[2], b, cost, stock, sell_price);
                entry.setId(id);
                entry.setName(newArray[1]);
                entry.setCategory(newArray[2]);
                entry.setPerishable(b);
                entry.setCost(cost);
                entry.setStock(stock);
                entry.setSell_price(sell_price);
                New_product.create(entry);
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("Not a valid path." + "\n" + ex.toString());
        }
    }

    public static void user() {
        try {
            String info;
            System.out.println("\n" + "Enter product details: ID,Name,Category,Perishable(true or false),cost,stock,sell price");
            Scanner scanner = new Scanner(System.in);
            info = scanner.nextLine();
            String[] arrInfo = info.split(",");
            int id = Integer.parseInt(arrInfo[0]);
            boolean b = Boolean.parseBoolean(arrInfo[3]);
            float cost = Float.parseFloat(arrInfo[4]);
            int stock = Integer.parseInt(arrInfo[5]);
            float sell_price = Float.parseFloat(arrInfo[6]);
            Table_Initializer userEntry = new Table_Initializer(id, arrInfo[1], arrInfo[2], b, cost, stock, sell_price);
            userEntry.setId(id);
            userEntry.setName(arrInfo[1]);
            userEntry.setCategory(arrInfo[2]);
            userEntry.setPerishable(b);
            userEntry.setCost(cost);
            userEntry.setStock(stock);
            userEntry.setSell_price(sell_price);
            New_product.create(userEntry);
        }catch (Exception e) {
            System.out.println("Not a valid entry." + "\n" + e.toString());
        }
    }
}
