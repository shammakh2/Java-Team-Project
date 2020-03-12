package csc1035.project3.tables;

import csc1035.project3.tables.transrelational.RelationTransaction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private double totalCost;
    @OneToMany(mappedBy = "transaction")
    private List<RelationTransaction> relation = new ArrayList<RelationTransaction>();
    @Column
    private String type;
    @Column
    private float totalPaid;
    @OneToOne
    private Exchanges exchange;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(float totalPaid) {
        this.totalPaid = totalPaid;
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
