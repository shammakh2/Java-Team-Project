package csc1035.project3.lookup;

import csc1035.project3.HibernateUtil;
import csc1035.project3.insert.Table_Initializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class stockLookup {
    public static void main(String[] args){
        System.out.println("Beginning Lookup");

        Scanner type_scanner = new Scanner(System.in);
        System.out.print("Enter search type\n>>>  ");
        String choice = type_scanner.nextLine();
        if(choice.toLowerCase().equals("id")){
            //get_data_id();
            get_data("id");
        } else if (choice.toLowerCase().equals("name")){
            //get_data_name();
            get_data("name");
        } else if (choice.toLowerCase().equals("category")){
            //get_data_category();
            get_data("category");
        } else {
            System.out.println("Invalid category");
        }
    }
    /**
     * Searches the Stock table for items.
     */
    public static void get_data(String type){
        /*
          List containing results to be outputted later.
         */
        List<Table_Initializer> data = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item "+type+" \n>>>  ");
        /*
          User entered search term
         */
        String search_item = scanner.nextLine();
        System.out.println("Searching for "+type+": " + search_item);
        search_item = search_item.toLowerCase();

        // Read
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List stock = session.createQuery("FROM Stock").list();
            for (Iterator<Table_Initializer> iterator = stock.iterator(); iterator.hasNext();){
                Table_Initializer item = iterator.next();
                if(type.equals("name")) {
                    if (item.getName() != null) {
                        if (item.getName().toLowerCase().equals(search_item) || item.getName().toLowerCase().
                                contains(search_item)) {
                            data.add(item);
                        }
                    } else {
                        /*
                          Warning for if item name is null.
                         */
                        System.out.println("Warning: item ID " + item.getId() + " has no name");
                    }
                } else if (type.equals("id")){
                    int search_item_id = Integer.valueOf(search_item);
                    if(item.getId() == search_item_id){
                        data.add(item);
                    }
                } else if (type.equals("category")){
                    if(item.getName() != null) {
                        if (item.getCategory().toLowerCase().equals(search_item) || item.getCategory().toLowerCase().
                                contains(search_item)) {
                            data.add(item);
                        }
                    } else {
                    /*
                      Warning for if item name is null.
                     */
                        System.out.println("Warning: item ID " + item.getId() + " has no category");
                    }
                }
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        // Output
        output(data);
    }

    /**
     * Outputs the provided data in an ascii table
     */
    private static void output(List<Table_Initializer> data){

        int max_length = 10;
        for(Table_Initializer item: data){
            if(item.getName().length() > max_length){
                max_length = item.getName().length();
            }
        }

        String align = "| %-15s | %-"+max_length+"s | %-10s | %-10s | %-10s | %-10s |%n";  // Formatting key for table

        String row_text = String.format(String.format("%%%ds", max_length + 2), " ").replace(" ", "-");
        String title_text = String.format(String.format("%%%ds", max_length - 9), " ");

        System.out.format("+-----------------+"+row_text+"+------------+------------+------------+------------+%n");
        System.out.format("| Item ID         | Item Name"+title_text+" |  Quantity  | Category   | Cost       | Sell Price |%n");
        System.out.format("+-----------------+"+row_text+"+------------+------------+------------+------------+%n");
        for (Table_Initializer item : data) {
            System.out.format(align, item.getId(), item.getName(), item.getStock(), item.getCategory(), item.getCost(), item.getSell_price());
            // Prints out database row
        }
        System.out.format("+-----------------+"+row_text+"+------------+------------+------------+------------+%n");
    }

}
