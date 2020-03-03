package csc1035.project3.warning;

import csc1035.project3.HibernateUtil;
import csc1035.project3.insert.Table_Initializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class warning_quantity {
    public static void warning() {
        List<Table_Initializer> data = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Stock where stock < 5");
            session.getTransaction().commit();
            for (Object i: query.getResultList()) {
                Table_Initializer tmp = (Table_Initializer) i;
                data.add(tmp);
            }

        }catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        output(data);
    }
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
        System.out.println("The quantity of these products are low, you should order some more.");
    }
}