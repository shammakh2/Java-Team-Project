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
        get_data_name();
    }

    public static void get_data_name(){
        List<Table_Initializer> data = new ArrayList<>();

        Scanner id_scanner = new Scanner(System.in);
        System.out.print("Enter item name \n>>>  ");
        String search_item_name = id_scanner.nextLine();
        System.out.println("Searching for Item: " + search_item_name);

        // Code here to search DB once its made

        //Read
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List stock = session.createQuery("FROM Stock").list();
            for (Iterator<Table_Initializer> iterator = stock.iterator(); iterator.hasNext();){
                Table_Initializer item = iterator.next();
                if(item.getName() instanceof String) {
                    if (item.getName().equals(search_item_name)) {
                        data.add(item);
                    }
                } else {
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

        // Code here to run output with the list
        output(data);
    }


    public static void get_data_id(){
        List<Table_Initializer> data = new ArrayList<>();

        Scanner id_scanner = new Scanner(System.in);
        System.out.print("Enter item ID \n>>>  ");
        int search_item_id = Integer.valueOf(id_scanner.nextLine());
        System.out.println("Searching for ID: " + search_item_id);

        // Code here to search DB once its made

        //Read
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

        // Code here to run output with the list
        output(data);
    }

    public static void output(List<Table_Initializer> data){

        // Function outputs a table containing the results of the database query.

        String align = "| %-15s | %-10s | %-10s |%n";  // Formatting key for table

        System.out.format("+-----------------+------------+------------+%n");
        System.out.format("| Item ID         | Item Name  |  Quantity  |%n");
        System.out.format("+-----------------+------------+------------+%n");
        for (Table_Initializer item : data) {
            System.out.format(align, item.getId(), item.getName(), item.getStock());  // Prints out database row
        }
        System.out.format("+-----------------+------------+------------+%n");
    }

}
