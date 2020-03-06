package csc1035.project3.transactions;

import csc1035.project3.HibernateUtil;
import csc1035.project3.stock.table.Stocks;
import org.hibernate.Session;
import csc1035.project3.transactions.table.Transactions;
import csc1035.project3.table.Relation;

import java.io.*;

public class Transaction {
    static Session session = HibernateUtil.getSessionFactory().openSession();
    public static void loadStocks(){
        try {
            FileReader stockFile = new FileReader("src/main/resources/stock.sample.csv");
            BufferedReader stockLine = new BufferedReader(stockFile);
            String l;
            session.beginTransaction();
            while ((l = stockLine.readLine()) != null){
                try {

                    String[] split = l.split(",");
                    Stocks stock = new Stocks();
                    System.out.println(Integer.parseInt(split[0]));
                    stock.setId(Integer.parseInt(split[0]));
                    stock.setName(split[1]);
                    stock.setCategory(split[2]);
                    stock.setPerishable(Boolean.parseBoolean(split[3]));
                    stock.setCost(Double.parseDouble(split[4]));
                    stock.setStock(Integer.parseInt(split[5]));
                    stock.setSell_price(Double.parseDouble(split[6]));
                    session.save(stock);
                }catch (NumberFormatException n){

                }
            }
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        loadStocks();
    }
}
