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

        if (info.equalsIgnoreCase("name"))
            try {
                String id;
                String name;
                System.out.println("\n" + "Enter the ID of the object you want to change: ");
                id = scanner.nextLine();
                int v = Integer.parseInt(id);
                System.out.println("\n" + "Enter the new name of the product: ");
                name = scanner.nextLine();
                session.beginTransaction();
                Table_Initializer table_initializer = (session.get(Table_Initializer.class, v));
                table_initializer.setName(name);
                session.update(table_initializer);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session != null) session.getTransaction().rollback();
                System.out.println("Not a valid entry");
            } finally {
                session.close();
            }
        else if (info.equalsIgnoreCase("category"))
            try {
                String id;
                String category;
                System.out.println("\n" + "Enter the ID of the object you want to change: ");
                id = scanner.nextLine();
                int v = Integer.parseInt(id);
                System.out.println("\n" + "Enter the new category of the product: ");
                category = scanner.nextLine();
                session.beginTransaction();
                Table_Initializer table_initializer = (session.get(Table_Initializer.class, v));
                table_initializer.setCategory(category);
                session.update(table_initializer);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session != null) session.getTransaction().rollback();
                System.out.println("Not a valid entry");
            } finally {
                session.close();
            }
        else if (info.equalsIgnoreCase("perishable"))
            try {
                String id;
                String perishable;
                System.out.println("\n" + "Enter the ID of the object you want to change: ");
                id = scanner.nextLine();
                int v = Integer.parseInt(id);
                System.out.println("\n" + "Enter if the product is perishable: true or false: ");
                perishable = scanner.nextLine();
                if (perishable.equalsIgnoreCase("true") || perishable.equalsIgnoreCase("false")){
                boolean b = Boolean.parseBoolean(perishable);
                session.beginTransaction();
                Table_Initializer table_initializer = (session.get(Table_Initializer.class, v));
                table_initializer.setPerishable(b);
                session.update(table_initializer);
                session.getTransaction().commit();}
                else System.out.println("Not a valid entry.");
            } catch (Exception e) {
                if (session != null) session.getTransaction().rollback();
                System.out.println("Not a valid entry");
            } finally {
                session.close();
            }
        else if (info.equalsIgnoreCase("cost"))
            try {
                String id;
                String cost;
                System.out.println("\n" + "Enter the ID of the object you want to change: ");
                id = scanner.nextLine();
                int v = Integer.parseInt(id);
                System.out.println("\n" + "Enter the new name of the product: ");
                cost = scanner.nextLine();
                float f = Float.parseFloat(cost);
                session.beginTransaction();
                Table_Initializer table_initializer = (session.get(Table_Initializer.class, v));
                table_initializer.setCost(f);
                session.update(table_initializer);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session != null) session.getTransaction().rollback();
                System.out.println("Not a valid cost");
            } finally {
                session.close();

            }
        else if (info.equalsIgnoreCase("quantity"))
            try {
                String id;
                String quantity;
                System.out.println("\n" + "Enter the ID of the object you want to change: ");
                id = scanner.nextLine();
                int v = Integer.parseInt(id);
                System.out.println("\n" + "Enter the new quantity of the product: ");
                quantity = scanner.nextLine();
                int i = Integer.parseInt(quantity);
                session.beginTransaction();
                Table_Initializer table_initializer = (session.get(Table_Initializer.class, v));
                table_initializer.setStock(i);
                session.update(table_initializer);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session != null) session.getTransaction().rollback();
                System.out.println("Not a valid entry");
            } finally {
                session.close();
            }
        else if (info.equalsIgnoreCase("sell price"))
            try {
                String id;
                String sell_price;
                System.out.println("\n" + "Enter the ID of the object you want to change: ");
                id = scanner.nextLine();
                int v = Integer.parseInt(id);
                System.out.println("\n" + "Enter the new sell price of the product: ");
                sell_price = scanner.nextLine();
                float f = Float.parseFloat(sell_price);
                session.beginTransaction();
                Table_Initializer table_initializer = (session.get(Table_Initializer.class, v));
                table_initializer.setSell_price(f);
                session.update(table_initializer);
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session != null) session.getTransaction().rollback();
                System.out.println("Not a valid sell price");
            } finally {
                session.close();
            }
        else System.out.println("Not a valid entry please try again");
    }
}
