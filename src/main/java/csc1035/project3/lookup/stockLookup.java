package csc1035.project3.lookup;

import csc1035.project3.HibernateUtil;
import csc1035.project3.insert.Table_Initializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.*;

public class stockLookup {
    public static void main(String[] args){
        HashMap<String, String> values = new HashMap<String, String>();
        values.put("id", "WHERE product_ID = ");
        values.put("name", "WHERE product_name LIKE ");
        values.put("category", "WHERE product_category LIKE ");
        values.put("quantity", "WHERE product_stock = ");
        values.put("cost", "WHERE product_cost = ");
        values.put("sell price", "WHERE product_sell_price = ");
        values.put("perishable", "Where product_perishable = ");

        System.out.println("Beginning Lookup");

        boolean asking = true;
        Scanner type_scanner = new Scanner(System.in);
        while (asking){
            System.out.print("Enter search type\n>>>  ");
            String choice = type_scanner.nextLine();
            if(values.keySet().contains(choice)){
                get_data(choice, values.get(choice));
                asking = false;
            } else if(choice.toLowerCase().equals("help")){
                for(String key : values.keySet()){
                    System.out.println(key);
                }
            } else {
                System.out.println("Invalid category");
            }
        }
    }
    /**
     * Searches the Stock table for items.
     */
    public static void get_data(String type, String term){
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
            String search_term = "";
            if(type.equals("name") || type.equals("category")){
                search_term = "FROM Stock " + term + "'%"+ search_item +"%'";
            } else {
                search_term = "FROM Stock " + term + search_item;
            }
            List stock = session.createQuery(search_term).list();
            for (Iterator<Table_Initializer> iterator = stock.iterator(); iterator.hasNext();){
                Table_Initializer item = iterator.next();
                data.add(item);
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
