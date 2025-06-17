package day7task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventry {

    private int stock = 100;
    private Lock lock = new ReentrantLock();

    public boolean purchase(String user, int qty) {
        lock.lock();
        try {
            if (stock >= qty) {
                System.out.println(user + " purchased " + qty + " item(s).");
                stock -= qty;
                System.out.println("Remaining stock: " + stock);
                return true;  
            } else {
                System.out.println(user + ": Not enough stock to purchase " + qty + " item(s).");
                return false; 
            }
        } finally {
            lock.unlock();
        }
    }

    public int getStock() {
        return stock;
    }
}
