package csc1035.project3.transactions;
import csc1035.project3.HibernateUtil;
import org.hibernate.Session;
import csc1035.project3.transactions.table.Transactions;

public class Transaction {
    public static void main(String[] args){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Transactions first = new Transactions();
        first.setId(1);
        first.setCustomerName("Shamm");
        first.setTotalCost(100_999);
        session.save(first);
        session.getTransaction().commit();
    }
}
