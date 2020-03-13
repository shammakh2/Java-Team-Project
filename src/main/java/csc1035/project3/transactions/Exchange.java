package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.tables.Table_Initializer;
import csc1035.project3.tables.transrelational.RelationExchange;
import csc1035.project3.tables.transrelational.RelationTransaction;
import csc1035.project3.transactions.interfaces.TransactionFramework;
import csc1035.project3.tables.Exchanges;
import csc1035.project3.tables.Transactions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class Exchange implements TransactionFramework {

    public boolean validate;

    private static SessionFactory sessionF = HibernateUtil.getSessionFactory();

    /**
     * 2 dimensional list of items and the amount that needs to be exchanged.
     */
    private ArrayList<ArrayList<Integer>> itemQ = new ArrayList<ArrayList<Integer>>();

    @Override
    public void queue(int item){
        queue(item,1);
    }

    /**
     * Method that loads up or queues all items to be exchanged and their amounts
     * during 'handshake' phase
     *
     * @param item Id of stock
     * @param amount  Amount to add to Stocks table
     * @see #handshake()
     */
    @Override
    public void queue(int item, int amount) {
        Session session = sessionF.openSession();
        Table_Initializer stock = session.get(Table_Initializer.class, item);
        int remaining = stock.getStock();
        for (ArrayList<Integer> x : itemQ) {
            if (item == x.get(0)) {
                if (validate){
                    if(remaining < (x.get(1)+amount)){
                        System.out.println("You dont have enough quantity of the entered item");
                        return;
                    }
                }
                x.add(1, x.get(1) + amount);
                return;
            }
        }
        if (validate){
            if(remaining < (amount)){
                System.out.println("You dont have enough quantity of the entered item");
                return;
            }
        }
        ArrayList<Integer> unitItem = new ArrayList<Integer>();
        unitItem.add(item);
        unitItem.add(amount);
        itemQ.add(unitItem);
    }


    /**
     * Writes all exchanges to database
     */
    @Override
    public int handshake() {
        Session session = sessionF.openSession();
        session.beginTransaction();
        Transactions transaction = new Transactions();
        transaction.setType("Exchange-Refund");
        Exchanges exchange = new Exchanges();
        exchange.setTransaction(transaction);
        transaction.setExchange(exchange);
        session.save(transaction);
        session.save(exchange);
        ArrayList<Float> cost = new ArrayList<>();
        for (ArrayList<Integer> x : itemQ) {
            RelationExchange re = new RelationExchange();
            Table_Initializer currentS = session.get(Table_Initializer.class, x.get(0));
            re.setQuantity(x.get(1));
            re.setExchanges(exchange);
            re.setStock(currentS);
            cost.add((currentS.getSell_price() * x.get(1)));
            currentS.setStock((currentS.getStock() + x.get(1)));
            exchange.getRelationE().add(re);
            session.save(re);
            session.update(currentS);
        }
        itemQ.clear();
        double sum = 0;
        for (Float i : cost) {
            sum += i;
        }
        transaction.setTotalCost(sum);
        session.update(exchange);
        session.update(transaction);
        session.getTransaction().commit();
        session.close();
        return transaction.getId();
    }

    public int exchangePurchase(Exchanges ex, Session s, double price){
        s.beginTransaction();
        Transactions rePurch = new Transactions();
        rePurch.setType("Exchange-Purchase");
        rePurch.setExchange(ex);
        s.save(rePurch);
        ArrayList<Float> cost = new ArrayList<>();
        for (ArrayList<Integer> x: itemQ){
            RelationTransaction r = new RelationTransaction();
            Table_Initializer currentS = s.get(Table_Initializer.class, x.get(0));
            r.setTransaction(rePurch);
            r.setStock(currentS);
            r.setQuantity(x.get(1));
            cost.add((currentS.getSell_price()*x.get(1)));
            currentS.setStock((currentS.getStock() - x.get(1)));
            rePurch.getRelation().add(r);
            s.save(r);
            s.update(currentS);
        }
        double sum = 0;
        for (Float i: cost){
            sum += i;
        }
        rePurch.setTotalCost(sum);
        ex.setPriceToBePaid(sum - price);
        s.update(rePurch);
        s.update(ex);
        s.getTransaction().commit();
        s.close();
        return rePurch.getId();
    }

}

