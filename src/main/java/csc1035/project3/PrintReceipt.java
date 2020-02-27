package csc1035.project3;


public class PrintReceipt {
    public static void main(String[] args) {
        AsciiTable();
    }
    public static void AsciiTable() {
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
        System.out.format(leftAlignFormat, test1, test2, test3, test4);
        // for every item in array/collection produce a quantity * price and add to total.
        System.out.format("+-----------------+---------+----------+----------+%n");
        System.out.format("The final total paid: £%.2f%n", finalTotal);


    }
}
