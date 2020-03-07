package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.stock.Temp;
import csc1035.project3.stock.table.Stocks;
import org.hibernate.Session;
import csc1035.project3.transactions.table.Transactions;
import csc1035.project3.table.Relation;
import org.hibernate.SessionFactory;
import java.util.ArrayList;

public class Transaction implements Connection {
    static SessionFactory sessionF = HibernateUtil.getSessionFactory();
    static ArrayList<ArrayList<Integer>> itemQ = new ArrayList<ArrayList<Integer>>();

    public void queue(int item, int remove){
        ArrayList<Integer> unitItem = new ArrayList<Integer>();
        unitItem.add(item);
        unitItem.add(remove);
        itemQ.add(unitItem);
    }

    public void handshake(){
        Session session = sessionF.openSession();
        session.beginTransaction();
        Transactions transaction = new Transactions();
        transaction.setCustomerName("Any");
        ArrayList<Double> cost = new ArrayList<>();
        for (ArrayList<Integer> x: itemQ){
            Relation r = new Relation();
            Stocks currentS = (Stocks) session.get(Stocks.class, x.get(0));
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
        Temp.loadStocks(sessionF);
        Transaction t = new Transaction();
        t.queue(1,2);
        t.queue(4,5);
        t.queue(9,1);
        t.handshake();


//        Session session = sessionF.openSession();
//        session.beginTransaction();
//        Transactions trans = new Transactions();
//        Relation r = new Relation();
//        trans.setId(1);
//        trans.setCustomerName("Hero");
//        trans.setTotalCost(120);
//        Stocks stock = (Stocks) session.get(Stocks.class, 2);
//        System.out.println(stock);
//        r.setStock(stock);
//        r.setTransaction(trans);
//        r.setQuantity(1);
//        stock.setStock((stock.getStock()-1));
//        session.update(stock);
//        session.save(trans);
//        session.save(r);
//        session.getTransaction().commit();
//        session.close();
    }
}
