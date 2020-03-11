package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.stock.table.Stocks;
import csc1035.project3.table.RelationTransaction;
import csc1035.project3.transactions.interfaces.TransactionFramework;
import csc1035.project3.transactions.table.Transactions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class Refund implements TransactionFramework {
    private static SessionFactory sessionF = HibernateUtil.getSessionFactory();

    /**
     * 2 dimensional list of items and the amount that needs to be refunded.
     */
    private ArrayList<ArrayList<Integer>> itemQ = new ArrayList<ArrayList<Integer>>();

    /**
     * Method that loads up or queues all items to be returned and their amounts
     * during 'handshake' phase
     *
     * @param item Id of stock
     * @param remove Amount to add to Stocks table
     * @see #handshake()
     */
    @Override
    public void queue(int item, int remove){
        ArrayList<Integer> unitItem = new ArrayList<Integer>();
        unitItem.add(item);
        unitItem.add(remove);
        itemQ.add(unitItem);
    }

    /**
     * Writes all refunds and changes to database
     */
    @Override
    public void handshake(){
        Session session = sessionF.openSession();
        session.beginTransaction();
        Transactions transaction = new Transactions();
        transaction.setId(1);
        transaction.setCustomerName("Any");
        transaction.setType("Refund");
        session.save(transaction);
        ArrayList<Double> cost = new ArrayList<>();
        for (ArrayList<Integer> x: itemQ){
            RelationTransaction r = new RelationTransaction();
            Stocks currentS = session.get(Stocks.class, x.get(0));
            r.setTransaction(transaction);
            r.setStock(currentS);
            r.setQuantity(x.get(1));
            cost.add((currentS.getSell_price()*x.get(1)));
            currentS.setStock((currentS.getStock() + x.get(1)));
            transaction.getRelation().add(r);
            session.save(r);
            session.update(currentS);
        }
        double sum = 0;
        for (Double i: cost){
            sum += i;
        }
        transaction.setTotalCost(sum);
        session.update(transaction);
        session.getTransaction().commit();
        session.close();
    }
}
