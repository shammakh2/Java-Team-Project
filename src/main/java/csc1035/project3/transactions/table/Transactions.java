package csc1035.project3.transactions.table;

import csc1035.project3.table.Relation;

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
    private List<Relation> relation = new ArrayList<Relation>();

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

    public List<Relation> getRelation() {
        return relation;
    }

    public void setRelation(List<Relation> stocks) {
        this.relation = stocks;
    }
}
