package csc1035.project3.tables.transrelational;

import csc1035.project3.tables.Table_Initializer;
import csc1035.project3.tables.Transactions;

import javax.persistence.*;

@Entity(name = "transaction_stock")
public class RelationTransaction {


    @EmbeddedId
    private RelationId id = new RelationId();

    @ManyToOne
    @MapsId("stockId")
    private Table_Initializer stock;
    @ManyToOne
    @MapsId("transactionId")
    private Transactions transaction;

    @Column
    private int quantity;

    public RelationId getId() {
        return id;
    }

    public void setId(RelationId id) {
        this.id = id;
    }

    public Table_Initializer getStock() {
        return stock;
    }

    public void setStock(Table_Initializer stock) {
        this.stock = stock;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
