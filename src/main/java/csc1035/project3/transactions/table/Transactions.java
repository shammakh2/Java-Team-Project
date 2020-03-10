package csc1035.project3.transactions.table;

import csc1035.project3.table.RelationTransaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String customerName;
    @Column
    private double totalCost;
    @OneToMany(mappedBy = "transaction")
    private List<RelationTransaction> relation = new ArrayList<RelationTransaction>();
    @Column
    private String type;
    @OneToOne
    private Exchanges exchange;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<RelationTransaction> getRelation() {
        return relation;
    }

    public void setRelation(List<RelationTransaction> relation) {
        this.relation = relation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Exchanges getExchange() {
        return exchange;
    }

    public void setExchange(Exchanges exchanges) {
        this.exchange = exchanges;
    }
}
