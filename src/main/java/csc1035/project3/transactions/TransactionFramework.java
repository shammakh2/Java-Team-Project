package csc1035.project3.transactions;

import org.hibernate.SessionFactory;

/**
 * This represents a template for all classes dealing with EPOS customer transactions.
 * @author Shammakh
 */
public interface TransactionFramework {
    /**
     * Method that loads up or queues all tasks that need to be performed and items that need to be dealt with
     * during 'handshake' phase
     *
     * @param item Id of stock
     * @param count Amount to add or remove from Stocks table
     * @see #handshake()
     */
    void queue(int item, int count);

    /**
     * Performs all the actions in the queue and writes them to the database.
     */
    void handshake();
}
