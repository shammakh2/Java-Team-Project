package csc1035.project3.lookup;

import csc1035.project3.HibernateUtil;
import csc1035.project3.tables.Table_Initializer;
import csc1035.project3.output.output;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.*;

public class stockLookup {
    public List<Table_Initializer> start() {
        HashMap<String, String> values = new HashMap<>();
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
        while (asking) {
            System.out.print("Enter search type\n>>>  ");
            String choice = type_scanner.nextLine();
            if (values.containsKey(choice)) {
                List<Table_Initializer> data = get_data(choice, values.get(choice));
                return data;
            } else if (choice.toLowerCase().equals("help")) {
                for (String key : values.keySet()) {
                    System.out.println(key);
                }
            } else {
                System.out.println("Invalid category");
            }
        }
        return null;
    }

    /**
     * Searches the Stock table for items.
     */
    public List<Table_Initializer> get_data(String type, String term) {
        /*
          List containing results to be outputted later.
         */
        List<Table_Initializer> data = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item " + type + " \n>>>  ");
        /*
          User entered search term
         */
        String search_item = scanner.nextLine();
        System.out.println("Searching for " + type + ": " + search_item);
        search_item = search_item.toLowerCase();

        boolean range_search = false;

        if (search_item.contains("-")) {
            String[] range = search_item.split("-");
            term = term.replace("=", "BETWEEN " + range[0] + " AND " + range[1]);
            range_search = true;
        } else if (search_item.contains("<=")) {
            term = term.replace("=", "<=");
            search_item = search_item.replace("<=", "");
        } else if (search_item.contains(">=")) {
            term = term.replace("=", ">=");
            search_item = search_item.replace(">=", "");
        } else if (search_item.contains("<")) {
            term = term.replace("=", "<");
            search_item = search_item.replace("<", "");
        } else if (search_item.contains(">")) {
            term = term.replace("=", ">");
            search_item = search_item.replace(">", "");
        }

        // Reads data from database using search term
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            String search_term = "";
            if (type.equals("name") || type.equals("category")) {
                search_term = "FROM Stock " + term + "'%" + search_item + "%'";
            } else if (range_search) {
                search_term = "FROM Stock " + term;
            } else {
                search_term = "FROM Stock " + term + search_item;
            }
            List stock = session.createQuery(search_term).list();
            for (Table_Initializer item : (Iterable<Table_Initializer>) stock) {
                data.add(item);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        // Outputs the data in a table
        output.output(data);
        return data;
    }

}
