package csc1035.project3.warning;

import csc1035.project3.HibernateUtil;
import csc1035.project3.insert.Table_Initializer;
import csc1035.project3.output.output;
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
        if (!data.isEmpty()){
            output.output(data);
            System.out.println("The quantity of these products are low, you should order some more.");
        }
    }
}