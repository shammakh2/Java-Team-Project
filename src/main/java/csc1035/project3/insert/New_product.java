package csc1035.project3.insert;
import csc1035.project3.HibernateUtil;
import csc1035.project3.tables.Table_Initializer;
import org.hibernate.HibernateException;
import org.hibernate.Session;


public class New_product {
    public static void create (Table_Initializer tableInitializer){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Table_Initializer origin = session.get(Table_Initializer.class, tableInitializer.getId());
            session.beginTransaction();
            if (origin == null) {
                Table_Initializer table_initializer = new Table_Initializer();
                table_initializer.setId(tableInitializer.getId());
                table_initializer.setName(tableInitializer.getName());
                table_initializer.setCategory(tableInitializer.getCategory());
                table_initializer.setPerishable(tableInitializer.getPerishable());
                table_initializer.setCost(tableInitializer.getCost());
                table_initializer.setStock(tableInitializer.getStock());
                table_initializer.setSell_price(tableInitializer.getSell_price());
                session.save(table_initializer);
            }else{
                origin.setId(tableInitializer.getId());
                origin.setName(tableInitializer.getName());
                origin.setCategory(tableInitializer.getCategory());
                origin.setPerishable(tableInitializer.getPerishable());
                origin.setCost(tableInitializer.getCost());
                origin.setStock(tableInitializer.getStock());
                origin.setSell_price(tableInitializer.getSell_price());
                session.update(origin);
            }
            session.getTransaction().commit();
        }catch (HibernateException e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            assert session != null;
            session.close();
        }
    }
}
