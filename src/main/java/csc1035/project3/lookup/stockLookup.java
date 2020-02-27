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
            get_data_id();
        } else if (choice.toLowerCase().equals("name")){
            get_data_name();
        }
    }

    /**
     * Searches the Stock table for item names that contain or match a search term.
     */
    public static void get_data_name(){
        /**
         * List containing results to be outputted later.
         */
        List<Table_Initializer> data = new ArrayList<>();

        Scanner name_scanner = new Scanner(System.in);
        System.out.print("Enter item name \n>>>  ");
        /**
         * User entered search term
         */
        String search_item_name = name_scanner.nextLine();
        System.out.println("Searching for Item: " + search_item_name);
        search_item_name = search_item_name.toLowerCase();

        // Read
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List stock = session.createQuery("FROM Stock").list();
            for (Iterator<Table_Initializer> iterator = stock.iterator(); iterator.hasNext();){
                Table_Initializer item = iterator.next();
                if(item.getName() instanceof String) {
                    if (item.getName().toLowerCase().equals(search_item_name) || item.getName().toLowerCase().
                            contains(search_item_name)) {
                        data.add(item);
                    }
                } else {
                    /**
                     * Warning for if item name is null.
                     */
                    System.out.println("Warning: item ID " + item.getId() + " has no name");
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
     * Searches the Stock table for item ids that match a search term.
     */
    public static void get_data_id(){
        /**
         * List containing results to be outputted later.
         */
        List<Table_Initializer> data = new ArrayList<>();

        Scanner id_scanner = new Scanner(System.in);
        System.out.print("Enter item ID \n>>>  ");
        /**
         * User entered search term
         */
        int search_item_id = Integer.valueOf(id_scanner.nextLine());
        System.out.println("Searching for ID: " + search_item_id);

        // Read
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List stock = session.createQuery("FROM Stock").list();
            for (Iterator<Table_Initializer> iterator = stock.iterator(); iterator.hasNext();){
                Table_Initializer item = iterator.next();
                if(item.getId() == search_item_id){
                    data.add(item);
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
    public static void output(List<Table_Initializer> data){
        /**
         * Formatting key for table
         */
        String align = "| %-15s | %-10s | %-10s |%n";

        System.out.format("+-----------------+------------+------------+%n");
        System.out.format("| Item ID         | Item Name  |  Quantity  |%n");
        System.out.format("+-----------------+------------+------------+%n");
        for (Table_Initializer item : data) {
            System.out.format(align, item.getId(), item.getName(), item.getStock());  // Prints out database row
        }
        System.out.format("+-----------------+------------+------------+%n");
    }

}
