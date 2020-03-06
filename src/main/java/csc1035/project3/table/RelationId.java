package csc1035.project3.table;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RelationId implements Serializable {

    private int stockId;

    private int transactionId;


    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
