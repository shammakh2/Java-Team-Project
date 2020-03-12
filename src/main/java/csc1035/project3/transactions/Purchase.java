package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.tables.Table_Initializer;
import csc1035.project3.transactions.interfaces.TransactionFramework;
import org.hibernate.Session;
import csc1035.project3.tables.Transactions;
import csc1035.project3.tables.transrelational.RelationTransaction;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implementation of TransactionFramework that deals with purchases in the EPOS system.
 * @author Shammakh
 */
public class Purchase implements TransactionFramework {

    private static SessionFactory sessionF = HibernateUtil.getSessionFactory();
    private Scanner scanner = new Scanner(System.in);

    /**
     * 2 dimensional list of items and the amount that needs to be purchased.
     */
    private ArrayList<ArrayList<Integer>> itemQ = new ArrayList<ArrayList<Integer>>();

    @Override
    public void queue(int item){
        queue(item,1);
    }

    /**
     * Method that loads up or queues all items to be purchased and their amounts
     * during 'handshake' phase
     *
     * @param item Id of stock
     * @param remove Amount to remove from Stocks table
     * @see #handshake()
     */

    @Override
    public void queue(int item, int remove){
        Session session = sessionF.openSession();
        Table_Initializer stock = session.get(Table_Initializer.class, item);
        int remaining = stock.getStock();
            for (ArrayList<Integer> x : itemQ) {
                if (item == x.get(0)) {
                    if(remaining < (x.get(1)+remove)){
                        System.out.println("You dont have enough quantity of the entered item");
                    }
                    x.add(1, x.get(1) + remove);
                    return;
                }
            }
        ArrayList<Integer> unitItem = new ArrayList<Integer>();
        unitItem.add(item);
        unitItem.add(remove);
        itemQ.add(unitItem);
    }

    /**
     * Writes all exchanges and changes to database
     */
    @Override
    public int handshake(){
        Session session = sessionF.openSession();
        session.beginTransaction();
        Transactions transaction = new Transactions();
        transaction.setId(1);
        transaction.setType("Purchase");
        session.save(transaction);
        ArrayList<Float> cost = new ArrayList<>();
        for (ArrayList<Integer> x: itemQ){
            RelationTransaction r = new RelationTransaction();
            Table_Initializer currentS = session.get(Table_Initializer.class, x.get(0));
            r.setTransaction(transaction);
            r.setStock(currentS);
            r.setQuantity(x.get(1));
            cost.add((currentS.getSell_price()*x.get(1)));
            currentS.setStock((currentS.getStock() - x.get(1)));
            transaction.getRelation().add(r);
            session.save(r);
            session.update(currentS);
        }
        float sum = 0;
        for (Float i: cost){
            sum += i;
        }
        transaction.setTotalCost(sum);
        float customerPay;
        while (true) {
            System.out.println("Amount required: " + sum);
            System.out.println("Amount paid by customer");
            customerPay = scanner.nextFloat();
            if ((customerPay - sum) >= 0){
                break;
            }else{
                System.out.println("Customer has not paid enough for purchase");
            }
        }
        transaction.setTotalPaid(customerPay);
        System.out.println("The change needed to be returned to customer is: " + (customerPay - sum));
        session.update(transaction);
        session.getTransaction().commit();
        session.close();
        return transaction.getId();
    }
}
