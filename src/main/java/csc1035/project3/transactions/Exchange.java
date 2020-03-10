package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.stock.table.Stocks;
import csc1035.project3.table.RelationExchange;
import csc1035.project3.table.RelationTransaction;
import csc1035.project3.transactions.table.Exchanges;
import csc1035.project3.transactions.table.Transactions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

public class Exchange implements TransactionFramework {

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
     * @param add  Amount to add or remove from Stocks table
     * @see #handshake()
     */
    @Override
    public void queue(int item, int add) {
        ArrayList<Integer> unitItem = new ArrayList<Integer>();
        unitItem.add(item);
        unitItem.add(add);
        itemQ.add(unitItem);
    }

    /**
     * Writes all purchases and changes to database
     */
    @Override
    public void handshake() {
        Session session = sessionF.openSession();
        session.beginTransaction();
        Transactions transaction = new Transactions();
        transaction.setId(2);
        transaction.setCustomerName("Any");
        transaction.setType("Exchange-Refund");
        Exchanges exchange = new Exchanges();
        exchange.setId(2);
        exchange.setTransaction(transaction);
        transaction.setExchange(exchange);
        session.save(transaction);
        session.save(exchange);
        ArrayList<Double> cost = new ArrayList<>();
        for (ArrayList<Integer> x : itemQ) {
            RelationExchange re = new RelationExchange();
            Stocks currentS = session.get(Stocks.class, x.get(0));
            re.setQuantity(x.get(1));
            re.setExchanges(exchange);
            re.setStock(currentS);
            cost.add((currentS.getSell_price() * x.get(1)));
            currentS.setStock((currentS.getStock() + x.get(1)));
            exchange.getRelationE().add(re);
            session.save(re);
            session.update(currentS);
        }
        double sum = 0;
        for (Double i : cost) {
            sum += i;
        }
        transaction.setTotalCost(sum);
        double costPrice = exchangePurchase(exchange, session);
        exchange.setPriceToBePaid(costPrice - sum);
        session.update(exchange);
        session.update(transaction);
        session.getTransaction().commit();
        session.close();
    }

    public double exchangePurchase(Exchanges ex, Session s){

        queue(4,5);
        //Insert Q
        Transactions rePurch = new Transactions();
        rePurch.setCustomerName("Any");
        rePurch.setType("Exchange-Purchase");
        rePurch.setExchange(ex);
        s.save(rePurch);
        ArrayList<Double> cost = new ArrayList<>();
        for (ArrayList<Integer> x: itemQ){
            RelationTransaction r = new RelationTransaction();
            Stocks currentS = s.get(Stocks.class, x.get(0));
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
        for (Double i: cost){
            sum += i;
        }
        rePurch.setTotalCost(sum);
        s.update(rePurch);

        return sum;
    }

}

