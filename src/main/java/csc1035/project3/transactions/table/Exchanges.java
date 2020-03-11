package csc1035.project3.transactions.table;

import csc1035.project3.table.RelationExchange;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exchanges {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @OneToOne(mappedBy = "exchange")
    private Transactions transaction;

    @OneToMany(mappedBy = "exchanges")
    private List<RelationExchange> relationE = new ArrayList<RelationExchange>();

    @Column
    private double priceToBePaid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public List<RelationExchange> getRelationE() {
        return relationE;
    }

    public void setRelationE(List<RelationExchange> relationE) {
        this.relationE = relationE;
    }

    public double getPriceToBePaid() {
        return priceToBePaid;
    }

    public void setPriceToBePaid(double priceToBePaid) {
        this.priceToBePaid = priceToBePaid;
    }
}
