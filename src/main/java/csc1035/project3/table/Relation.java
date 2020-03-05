package csc1035.project3.table;

import csc1035.project3.stock.table.Stocks;
import csc1035.project3.transactions.table.Transactions;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "transaction_stock")
public class Relation {

    @EmbeddedId
    private RelationId id = new RelationId();

    @ManyToOne
    @MapsId("stockId")
    private Stocks stock;
    @ManyToOne
    @MapsId("transactionId")
    private Transactions transaction;

    public RelationId getId() {
        return id;
    }

    public void setId(RelationId id) {
        this.id = id;
    }

    public Stocks getStock() {
        return stock;
    }

    public void setStock(Stocks stock) {
        this.stock = stock;
    }

    public Transactions getTransaction() {
        return transaction;
    }

    public void setTransaction(Transactions transaction) {
        this.transaction = transaction;
    }
}
