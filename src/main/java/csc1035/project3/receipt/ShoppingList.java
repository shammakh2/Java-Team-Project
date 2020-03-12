package csc1035.project3.receipt;

public class ShoppingList {
    private int productID;
    private String productName;
    private int quantity;
    private float price;
    private float totalPrice;

    public ShoppingList(int productID, String productName, int quantity, float price){
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price * quantity;
    }

    public int getProductID(){
        return productID;
    }
    public String getProductName(){
        return productName;
    }
    public int getQuantity(){
        return quantity;
    }
    public float getPrice(){
        return price;
    }
    public float getTotalPrice(){
        return totalPrice;
    }
}
