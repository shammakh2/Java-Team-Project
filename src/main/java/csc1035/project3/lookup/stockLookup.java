package csc1035.project3.lookup;

import csc1035.project3.HibernateUtil;
import csc1035.project3.insert.Table_Initializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.*;

public class stockLookup {
    public static void main(String[] args){
        ArrayList<String> valid = new ArrayList<>(Arrays.asList("id", "name", "category", "quantity", "cost",
                "sell price", "perishable"));

        System.out.println("Beginning Lookup");

        Scanner type_scanner = new Scanner(System.in);
        System.out.print("Enter search type\n>>>  ");
        String choice = type_scanner.nextLine();
        if(valid.contains(choice)){
            get_data(choice);
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
                } else if (type.equals("quantity") || type.equals("stock")){
                    int search_item_stock = Integer.valueOf(search_item);
                    if(item.getStock() == search_item_stock){
                        data.add(item);
                    }
                } else if (type.equals("cost")){
                    int search_item_cost = Integer.valueOf(search_item);
                    if(item.getCost() == search_item_cost){
                        data.add(item);
                    }
                } else if (type.equals("sell price")){
                    int search_item_sell = Integer.valueOf(search_item);
                    if(item.getSell_price() == search_item_sell){
                        data.add(item);
                    }
                } else if (type.equals("perishable")){
                    if(Boolean.toString(item.getPerishable()).equals(search_item)){
                        data.add(item);
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
