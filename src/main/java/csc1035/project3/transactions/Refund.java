package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.tables.Table_Initializer;
import csc1035.project3.tables.transrelational.RelationTransaction;
import csc1035.project3.transactions.interfaces.TransactionFramework;
import csc1035.project3.tables.Transactions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class Refund implements TransactionFramework {
    private static SessionFactory sessionF = HibernateUtil.getSessionFactory();

    /**
     * 2 dimensional list of items and the amount that needs to be refunded.
     */
    private ArrayList<ArrayList<Integer>> itemQ = new ArrayList<ArrayList<Integer>>();

    @Override
    public void queue(int item){
        queue(item,1);
    }

    /**
     * Method that loads up or queues all items to be returned and their amounts
     * during 'handshake' phase
     *
     * @param item Id of stock
     * @param add Amount to add to Stocks table
     * @see #handshake()
     */
    @Override
    public void queue(int item, int add){
        for (ArrayList<Integer> x : itemQ) {
            if (item == x.get(0)) {
                x.add(1, x.get(1) + add);
                return;
            }
        }
        ArrayList<Integer> unitItem = new ArrayList<Integer>();
        unitItem.add(item);
        unitItem.add(add);
        itemQ.add(unitItem);
    }

    /**
     * Writes all refunds and changes to database
     */
    @Override
    public int handshake(){
        Session session = sessionF.openSession();
        session.beginTransaction();
        Transactions transaction = new Transactions();
        transaction.setId(1);
        transaction.setType("Refund");
        session.save(transaction);
        ArrayList<Float> cost = new ArrayList<>();
        for (ArrayList<Integer> x: itemQ){
            RelationTransaction r = new RelationTransaction();
            Table_Initializer currentS = session.get(Table_Initializer.class, x.get(0));
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
        for (Float i: cost){
            sum += i;
        }
        transaction.setTotalCost(sum);
        session.update(transaction);
        session.getTransaction().commit();
        session.close();
        return transaction.getId();
    }
}
