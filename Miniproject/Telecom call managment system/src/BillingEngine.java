import java.util.List;
import java.util.Map;

class BillingEngine {
    private static BillingEngine instance;

    BillingEngine() {}

    public static BillingEngine getInstance() {
        if (instance == null) {
            instance = new BillingEngine();
        }
        return instance;
    }

    public double calculateCallCost(Call call) {
        double rate = call.getCaller().getPlan().getPerMinuteRate();
        double durationInMinutes = call.getDuration() / 60.0;
        return rate * durationInMinutes;
    }

    public void generateBill(Customer customer, Map<Customer, List<CallLog>> callLogs) {
        List<CallLog> logs = callLogs.get(customer);
        double totalCost = 0;
        for (CallLog log : logs) {
            totalCost += log.getCost();
        }
        System.out.println("Bill for " + customer.getName() + ": " + totalCost);
    }
}