package csc1035.project3.insert;
import csc1035.project3.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class New_product {
    public static void create (Table_Initializer Table_Initializer){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Table_Initializer table_initializer = new Table_Initializer();
            table_initializer.setName(Table_Initializer.getName());
            table_initializer.setCategory(Table_Initializer.getCategory());
            table_initializer.setPerishable(Table_Initializer.getPerishable());
            table_initializer.setCost(Table_Initializer.getCost());
            table_initializer.setStock(Table_Initializer.getStock());
            table_initializer.setSell_price(Table_Initializer.getSell_price());
            session.save(table_initializer);
            session.getTransaction().commit();
        }catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            assert session != null;
            session.close();
        }
    }
}
