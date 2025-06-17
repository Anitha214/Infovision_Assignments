package day7task;

public class OrderThread extends Thread {

    private Inventry inventry;
    private Payment payment;
    private String user;
    private int qty;
    private int pricePerItem = 100; 

    public OrderThread(Inventry inventry, Payment payment, String user, int qty) {
        this.inventry = inventry;
        this.payment = payment;
        this.user = user;
        this.qty = qty;
    }

    public void run() {
        boolean purchased = inventry.purchase(user, qty);
        if (purchased) {
            int amount = qty * pricePerItem;
            payment.processPayment(user, amount);
        } else {
            System.out.println(user + ": Purchase failed. Payment not processed.");
        }
    }

    public static void main(String[] args) {
        Inventry inv = new Inventry();
        Payment payment = new Payment();

        OrderThread th1 = new OrderThread(inv, payment, "Anitha", 10);
        OrderThread th2 = new OrderThread(inv, payment, "Abitha", 80);
        OrderThread th3 = new OrderThread(inv, payment, "Sophitha", 15);

        th1.start();
        th2.start();
        th3.start();
    }
}




