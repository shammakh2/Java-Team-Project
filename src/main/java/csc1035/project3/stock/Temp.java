package csc1035.project3.stock;

import csc1035.project3.stock.table.Stocks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Temp {
    public static void loadStocks(SessionFactory sF){
        try {
            FileReader stockFile = new FileReader("src/main/resources/stock.sample.csv");
            BufferedReader stockLine = new BufferedReader(stockFile);
            String l;
            Session session = sF.openSession();
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
}
