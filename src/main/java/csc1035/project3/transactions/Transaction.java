package csc1035.project3.transactions;
import csc1035.project3.HibernateUtil;
import csc1035.project3.stock.table.Stock;
import org.hibernate.Session;
import csc1035.project3.transactions.table.Transactions;

public class Transaction {
    public static void main(String[] args){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Stock stock = new Stock();
        stock.setId(1);
        stock.setName("Card");
        stock.setCategory("Game");
        stock.setCost(5.00);
        stock.setPerishable(false);
        stock.setSell_price(10.00);
        Transactions first = new Transactions();
        first.setId(101);
        first.setCustomerName("Shamm");
        first.setTotalCost(100_999);

        stock.getTransactions().add(first);
        first.getStocks().add(stock);

        session.save(first);
        session.save(stock);
        session.getTransaction().commit();
    }
}
