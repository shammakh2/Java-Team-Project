package csc1035.project3.receipt;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PrintReceipt {
    public static void main(String[] args) throws IOException {
        // Test Running

        //NewAsciiTableReceipt();
        //ReceiptToFile();
        tempShoppingList();
    }

    public static void tempShoppingList(){
        // This simulates the data I obtain from the shopping basket/ transaction.
        int fakeID = 244351324;
        int fakeQuantity = 7;
        float fakePrice = 3.40f;
        ArrayList<ShoppingList> tempArray = new ArrayList<>();
        ShoppingList tempList = new ShoppingList(fakeID, fakeQuantity, fakePrice);
        tempArray.add(tempList);
        NewAsciiTableReceipt(tempArray);
    }

    public static void NewAsciiTableReceipt(ArrayList<ShoppingList> asciiForReceipt) {
        // Possibly take a collection which will contain the Item ID, price, quantity.

        String leftAlignFormat = "| %-15s | £%6.2f | %-8d | £%7.2f |%n";

        // Testing variables
        String test1 = "44353";
        float test2 = 32.30f;
        int test3 = 30;
        float test4 = test2 * test3;
        float finalTotal = 23.00f;

        System.out.format("+-----------------+---------+----------+----------+%n");
        System.out.format("|     Product     |  Price  | Quantity |   Total  |%n");
        System.out.format("+-----------------+---------+----------+----------+%n");

        // Perform as loop for each product.
        for (ShoppingList data : asciiForReceipt){
            System.out.format(leftAlignFormat, data.getProductID(), data.getPrice(), data.getQuantity(), (data.getPrice() * data.getQuantity()));
        }

        // for every item in array/collection produce a quantity * price and add to total.
        System.out.format("+-----------------+---------+----------+----------+%n");
        System.out.format("The final total paid: £%.2f%n", finalTotal);


    }
    public static void ReceiptToFile() throws IOException {
        // Write the purchase receipt to a file rather than to console

        FileWriter receiptPrint = new FileWriter("Receipts.txt", true);
        receiptPrint.append(String.format("%n"));
        receiptPrint.append("New Receipt:");
        receiptPrint.append(String.format("%n"));
        receiptPrint.append(String.format("+-----------------+---------+----------+----------+%n"));
        receiptPrint.append(String.format("|     Product     |  Price  | Quantity |   Total  |%n"));
        receiptPrint.append(String.format("+-----------------+---------+----------+----------+%n"));
        //Append each product
        receiptPrint.flush();
        receiptPrint.close();
    }
}
