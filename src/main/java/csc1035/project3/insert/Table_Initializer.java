package csc1035.project3.insert;

import javax.persistence.*;

@Entity(name = "Stock") //Table name
public class Table_Initializer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_ID", updatable = false, nullable = false)
    private int id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_category")
    private String category;

    public Table_Initializer() {
    }

    @Column(name = "product_perishable")
    private Boolean perishable;

    @Column(name = "product_cost")
    private int cost;

    @Column(name = "product_stock")
    private int stock;

    @Column(name = "product_sell_price")
    private int sell_price;


    public Table_Initializer(int id, String name, String category, Boolean perishable, int cost, int stock, int sell_price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.perishable = perishable;
        this.cost = cost;
        this.stock = stock;
        this.sell_price = sell_price;
    }

    public Table_Initializer(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
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

    public Boolean getPerishable() {
        return perishable;
    }

    public void setPerishable(Boolean perishable) {
        this.perishable = perishable;
    }

    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSell_price() {
        return sell_price;
    }
    public void setSell_price(int sell_price) {
        this.sell_price = sell_price;
    }
}

