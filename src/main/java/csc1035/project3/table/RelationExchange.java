package csc1035.project3.table;

import csc1035.project3.stock.table.Stocks;
import csc1035.project3.transactions.table.Exchanges;

import javax.persistence.*;

@Entity(name = "transaction_exchange")
public class RelationExchange {

    @EmbeddedId
    private RelationId id = new RelationId();

    @ManyToOne
    @MapsId("stockId")
    private Stocks stock;
    @ManyToOne
    @MapsId("transactionId")
    private Exchanges exchanges;
    @Column
    private int quantity;

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

    public Exchanges getExchanges() {
        return exchanges;
    }

    public void setExchanges(Exchanges exchange) {
        this.exchanges = exchange;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
