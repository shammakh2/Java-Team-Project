package csc1035.project3.lookup;

import csc1035.project3.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class stockLookup {
    public static void main(String[] args){
        System.out.println("Beginning Lookup");
        /*Item test_item = new Item(1, "Gold Ring", 4 );
        List<Item> test_list = new ArrayList<>();
        test_list.add(test_item);
        test_list.add(test_item);
        output(test_list);*/
        get_data_id();
    }

    public static void get_data_name(){
        List<Item> data = new ArrayList<>();

        Scanner id_scanner = new Scanner(System.in);
        System.out.print("Enter item name \n>>>  ");
        String search_item_name = id_scanner.nextLine();
        System.out.println("Searching for Item: " + search_item_name);

        // Code here to search DB once its made

        // Code here to run output with the list
        output(data);
    }


    public static void get_data_id(){
        List<Item> data = new ArrayList<>();

        Scanner id_scanner = new Scanner(System.in);
        System.out.print("Enter item ID \n>>>  ");
        String search_item_id = id_scanner.nextLine();
        System.out.println("Searching for ID: " + search_item_id);

        // Code here to search DB once its made

        //Read
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List cars = session.createQuery("FROM CAR").list();
            for (Iterator<Item> iterator = cars.iterator(); iterator.hasNext();){
                Item item = iterator.next();
                if(item.getID() == search_item_id){
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

    public static void output(List<Item> data){

        // Function outputs a table containing the results of the database query.

        String align = "| %-15s | %-10s | %-10s |%n";  // Formatting key for table

        System.out.format("+-----------------+------------+------------+%n");
        System.out.format("| Item ID         | Item Name  |  Quantity  |%n");
        System.out.format("+-----------------+------------+------------+%n");
        for (Item item : data) {
            System.out.format(align, item.id, item.name, item.quantity);  // Prints out database row
        }
        System.out.format("+-----------------+------------+------------+%n");
    }

}
