package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.stock.Temp;
import csc1035.project3.stock.table.Stocks;
import org.hibernate.Session;
import csc1035.project3.transactions.table.Transactions;
import csc1035.project3.table.Relation;
import org.hibernate.SessionFactory;

import java.io.*;
import java.util.ArrayList;

public class Transaction {
    static SessionFactory sessionF = HibernateUtil.getSessionFactory();
    static ArrayList<ArrayList<Integer>> transactionQ = new ArrayList<ArrayList<Integer>>();

    public static void queue(int item, int remove){
        ArrayList<Integer> unitItem = new ArrayList<Integer>();
        unitItem.add(item);
        unitItem.add(remove);
        transactionQ.add(unitItem);
    }

    

    public static void main(String[] args){
        Temp.loadStocks(sessionF);
        Session session = sessionF.openSession();
        session.beginTransaction();
//        Transactions trans = new Transactions();
//        Relation r = new Relation();
//        trans.setId(1);
//        trans.setCustomerName("Hero");
//        trans.setTotalCost(120);
        Stocks stock = (Stocks) session.get(Stocks.class, 2);
        System.out.println(stock);
//        r.setStock(stock);
//        r.setTransaction(trans);
//        r.setQuantity(1);
        stock.setStock((stock.getStock()-1));
//        session.update(stock);
//        session.save(trans);
//        session.save(r);
        session.getTransaction().commit();
        session.close();

    }
}
