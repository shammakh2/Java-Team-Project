package csc1035.project3.stock.table;

import csc1035.project3.transactions.table.Transactions;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stock {

    @Id
    private int id;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private boolean perishable;
    @Column
    private double cost;
    @Column
    private int stock;
    @Column
    private double sell_price;
    @ManyToMany(mappedBy = "stocks")
    private List<Transactions> transactions = new ArrayList<Transactions>();

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }
}
