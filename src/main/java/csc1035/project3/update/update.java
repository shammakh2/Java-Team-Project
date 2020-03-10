package csc1035.project3.update;

import csc1035.project3.HibernateUtil;
import csc1035.project3.insert.Table_Initializer;
import org.hibernate.Session;

import java.util.Scanner;

public class update {

    public void modify() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String info;
        System.out.println("\n" + "Enter what you want to change: name, category, perishable, cost, quantity, sell price: ");
        Scanner scanner = new Scanner(System.in);
        info = scanner.nextLine();

        try {
            String id;
            String new_data;
            System.out.println("\n" + "Enter the ID of the object you want to change: ");
            id = scanner.nextLine();
            int v = Integer.parseInt(id);
            System.out.println("\n" + "Enter the new " + info + " of the product: ");
            new_data = scanner.nextLine();
            session.beginTransaction();
            Table_Initializer table_initializer = (session.get(Table_Initializer.class, v));
            switch (info.toLowerCase()) {
                case "name":
                    table_initializer.setName(new_data);
                    break;
                case "category":
                    table_initializer.setCategory(new_data);
                    break;
                case "perishable":
                    table_initializer.setPerishable(Boolean.parseBoolean(new_data));
                    break;
                case "cost":
                    table_initializer.setCost(Float.parseFloat(new_data));
                    break;
                case "quantity":
                    table_initializer.setCost(Integer.parseInt(new_data));
                    break;
                case "sell price":
                    table_initializer.setSell_price(Float.parseFloat(new_data));
                    break;
                default:
                    System.out.println("Not a valid option");
                    break;

            }

            session.update(table_initializer);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
            System.out.println("Not a valid entry");
        } finally {
            session.close();
        }
    }
}
