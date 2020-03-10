package csc1035.project3.stock.table;

import csc1035.project3.table.RelationExchange;
import csc1035.project3.table.RelationTransaction;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Stocks {

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
//    @OneToMany(mappedBy = "stock")
//    private List<RelationTransaction> relation = new ArrayList<RelationTransaction>();
//    @OneToMany(mappedBy = "stock")
//    private List<RelationExchange> relationE = new ArrayList<RelationExchange>();

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

//    public List<RelationTransaction> getRelation() {
//        return relation;
//    }
//
//    public void setRelation(List<RelationTransaction> relation) {
//        this.relation = relation;
//    }

//    public List<RelationExchange> getRelationE() {
//        return relationE;
//    }
//
//    public void setRelationE(List<RelationExchange> relationE) {
//        this.relationE = relationE;
//    }
}
