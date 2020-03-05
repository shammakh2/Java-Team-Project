package csc1035.project3.transactions.table;

import csc1035.project3.table.Relation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transactions {
    @Id
    private int id;
    @Column
    private String customerName;
    @Column
    private int totalCost;
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

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public List<Relation> getRelation() {
        return relation;
    }

    public void setRelation(List<Relation> stocks) {
        this.relation = stocks;
    }
}
