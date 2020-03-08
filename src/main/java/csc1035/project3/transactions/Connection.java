package csc1035.project3.transactions;

public interface Connection {
    
    public void queue(int item, int remove);

    public void handshake();
}
