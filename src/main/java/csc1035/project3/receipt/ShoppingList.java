package csc1035.project3.receipt;

public class ShoppingList {
    int productID;
    int quantity;
    float price;

    public ShoppingList(int productID, int quantity, float price){
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductID(){
        return productID;
    }
    public int getQuantity(){
        return quantity;
    }
    public float getPrice(){
        return price;
    }
}
