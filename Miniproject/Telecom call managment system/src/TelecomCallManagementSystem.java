import java.util.*;

public class TelecomCallManagementSystem {
    public static void main(String[] args) throws InterruptedException {

        Customer customer1 = new Customer("C001", "Ani");
        customer1.setPlan(PlanFactory.getPlan("prepaid"));

        Customer customer2 = new Customer("C002", "sophi");
        customer2.setPlan(PlanFactory.getPlan("postpaid"));

        Call call1 = new Call(customer1, customer2);
        Call call2 = new Call(customer2, customer1);

        Thread callThread1 = new Thread(new CallSimulator(call1));
        Thread callThread2 = new Thread(new CallSimulator(call2));

        callThread1.start();
        callThread2.start();

        callThread1.join();
        callThread2.join();

        Map<Customer, List<CallLog>> callLogs = new HashMap<>();
        callLogs.put(customer1, new ArrayList<>());
        callLogs.put(customer2, new ArrayList<>());

        callLogs.get(customer1).add(new CallLog(call1, new BillingEngine().calculateCallCost(call1)));
        callLogs.get(customer2).add(new CallLog(call2, new BillingEngine().calculateCallCost(call2)));

        BillingEngine billingEngine = BillingEngine.getInstance();
        billingEngine.generateBill(customer1, callLogs);
        billingEngine.generateBill(customer2, callLogs);
    }
}
