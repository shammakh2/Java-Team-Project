package csc1035.project3.receipt;


import csc1035.project3.HibernateUtil;
import csc1035.project3.tables.transrelational.RelationTransaction;
import org.hibernate.Session;

import org.hibernate.query.Query;
import org.hibernate.type.IntegerType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hibernate.type.StandardBasicTypes.INTEGER;

public class PrintReceipt {
    private static int size = 13;
    private static StringBuilder border = new StringBuilder();
    private static ArrayList<ShoppingList> asciiForReceipt = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Test Running
        //ReceiptToFile();
        //tempShoppingList();
        pullUpItems( "purchase", 5, 6);

    }

    public static void pullUpItems(String type, int...id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM transaction_stock AS t WHERE t.transaction = :id");
        List<RelationTransaction> rt = (List<RelationTransaction>) query.setParameter("id", id[0], INTEGER).list();
        for (RelationTransaction x: rt){
            insertItems(x.getStock().getId(), x.getStock().getName(), x.getQuantity(), x.getStock().getSell_price());
        }
        NewAsciiTableReceipt(rt.get(0).getTransaction().getTotalPaid(), type, id);
    }

    public static void insertItems(int id, String name, int quantity, float price) {
        ShoppingList tempList = new ShoppingList(id, name, quantity, price);
        asciiForReceipt.add(tempList);
    }

    public static void tempShoppingList() {
        // This simulates the data I obtain from the shopping basket/ transaction.
        int fakeID = 244351324;
        int fakeQuantity = 7;
        String fakeName = "Shammmmmmmmmmmmmmmmakhhhhhhhhhh";
        float fakePrice = 5.40f;
        ShoppingList tempList = new ShoppingList(fakeID, fakeName, fakeQuantity, fakePrice);
        asciiForReceipt.add(tempList);
    }

    public static void NewAsciiTableReceipt(float paid, String type, int...id) {
        // Possibly take a collection which will contain the Item ID, price, quantity.
        border = new StringBuilder();
        resizer(asciiForReceipt);
        String leftAlignFormat = "| %-15s | %-" + size + "s | £%6.2f | %-8d | £%7.2f |%n";
        for (int i = 0; i < size; i++) {
            border.append("-");
        }
        // Testing variables
        float finalTotal = 0.00f;

        switch (type){
            case "purchase":
                System.out.format("Purchase transaction receipt\n");
                System.out.format("Transaction ID: %d\n", id[0]);
                break;
            case "exchange - return":
                System.out.format("Exchange transaction receipt\n");
                System.out.format("---------EXCHANGE PENDING---------\n");
                System.out.format("Transaction ID: %d\n", id[0]);
                break;
            case "exchange - repurchase":
                System.out.format("Exchange transaction receipt\n");
                System.out.format("---------EXCHANGE COMPLETE---------\n");
                System.out.format("Transaction ID: %d-%d\n", id[1],id[0]);
                break;
            case "refund":
                System.out.format("Refund transaction receipt\n");
                System.out.format("Transaction ID: %d\n", id[0]);
                break;
        }

        System.out.format("+-----------------+-%11s-+---------+----------+----------+%n", border.toString());
        System.out.format("|     Product     | %-" + size + "s |  Price  | Quantity |   Total  |%n", "Product Name");
        System.out.format("+-----------------+-%11s-+---------+----------+----------+%n", border.toString());

        // Perform as loop for each product.
        for (ShoppingList data : asciiForReceipt) {
            System.out.format(leftAlignFormat, data.getProductID(), data.getProductName(), data.getPrice(), data.getQuantity(), data.getTotalPrice());
            finalTotal += data.getTotalPrice();
        }
        System.out.format("+-----------------+-%11s-+---------+----------+----------+%n", border.toString());
        switch (type){
            case "purchase":
                // for every item in array/collection produce a quantity * price and add to total.
                System.out.format("The final total to be paid: £%.2f%n", finalTotal);
                System.out.format("The final total that was paid: £%.2f%n", paid);
                System.out.format("Change returned to customer: £%.2f%n", paid - finalTotal);
                break;
            case "exchange - return":
                System.out.format("The amount you have remaining for exchange: £%.2f%n", finalTotal);
                System.out.format("---------EXCHANGE PENDING---------\n");
                break;
            case "exchange - repurchase":
                System.out.format("---------EXCHANGE COMPLETE---------\n");
                break;
            case "refund":
                System.out.format("The final total returned: £%.2f%n", finalTotal);
                break;
        }

        asciiForReceipt.clear();


    }

    public static void ReceiptToFile() throws IOException {
        // Write the purchase receipt to a file rather than to console

        FileWriter receiptPrint = new FileWriter("Receipts.txt", true);
        receiptPrint.append(String.format("%n"));
        receiptPrint.append("New Receipt:");
        receiptPrint.append(String.format("%n"));
        receiptPrint.append(String.format("+-----------------+-%11s-+---------+----------+----------+%n", border.toString()));
        receiptPrint.append(String.format("|     Product     | %-" + size + "s |  Price  | Quantity |   Total  |%n", "Product Name"));
        receiptPrint.append(String.format("+-----------------+-%11s-+---------+----------+----------+%n", border.toString()));
        //Append each product
        receiptPrint.flush();
        receiptPrint.close();
    }

    public static void resizer(ArrayList<ShoppingList> asciiForReceipt) {
        for (ShoppingList x : asciiForReceipt) {
            if (x.getProductName().length() > size) {
                size = x.getProductName().length();
            }
        }
    }
}
