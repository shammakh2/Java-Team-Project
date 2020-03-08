package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.stock.Temp;
import csc1035.project3.stock.table.Stocks;
import org.hibernate.Session;
import csc1035.project3.transactions.table.Transactions;
import csc1035.project3.table.Relation;
import org.hibernate.SessionFactory;
import java.util.ArrayList;

/**
 * Implementation of TransactionFramework that deals with purchases in the EPOS system.
 * @author Shammakh
 */
public class Purchase implements TransactionFramework {

    private static SessionFactory sessionF = HibernateUtil.getSessionFactory();

    /**
     * 2 dimensional list of items and the amount that needs to be purchased.
     */
    private ArrayList<ArrayList<Integer>> itemQ = new ArrayList<ArrayList<Integer>>();

    /**
     * Method that loads up or queues all items to be purchased and their amounts
     * during 'handshake' phase
     *
     * @param item Id of stock
     * @param remove Amount to add or remove from Stocks table
     * @see #handshake()
     */
    public void queue(int item, int remove){
        ArrayList<Integer> unitItem = new ArrayList<Integer>();
        unitItem.add(item);
        unitItem.add(remove);
        itemQ.add(unitItem);
    }

    /**
     * Writes all purchases and changes to database
     */
    public void handshake(){
        Session session = sessionF.openSession();
        session.beginTransaction();
        Transactions transaction = new Transactions();
        transaction.setCustomerName("Any");
        ArrayList<Double> cost = new ArrayList<>();
        for (ArrayList<Integer> x: itemQ){
            Relation r = new Relation();
            Stocks currentS = session.get(Stocks.class, x.get(0));
            r.setTransaction(transaction);
            r.setStock(currentS);
            r.setQuantity(x.get(1));
            cost.add((currentS.getSell_price()*x.get(1)));
            currentS.setStock((currentS.getStock() - x.get(1)));
            transaction.getRelation().add(r);
            session.save(r);
            session.update(currentS);
        }
        double sum = 0;
        for (Double i: cost){
            sum += i;
        }
        transaction.setTotalCost(sum);
        session.save(transaction);
        session.getTransaction().commit();
        session.close();
    }



    public static void main(String[] args){
        Temp.loadStocks();
        Purchase t = new Purchase();
        t.queue(1,2);
        t.queue(4,5);
        t.queue(9,1);
        t.handshake();
    }
}
