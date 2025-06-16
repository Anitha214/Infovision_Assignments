package day3task;

public abstract class BaseService implements TelecomService {
    protected String serviceName;
    protected double ratePerUnit;
   

    public BaseService(String serviceName, double ratePerUnit) {
        this.serviceName = serviceName;
        this.ratePerUnit = ratePerUnit;
    }

    public BaseService(BaseService other) {
        this.serviceName = other.serviceName;
        this.ratePerUnit = other.ratePerUnit;
    }

    public BaseService(BaseService other, boolean deepCopy) {
        this.serviceName = deepCopy ? new String(other.serviceName) : other.serviceName;
        this.ratePerUnit = other.ratePerUnit;
    }

    @Override
    public void start() {
        System.out.println(serviceName + " started.");
    }

    @Override
    public void stop() {
        System.out.println(serviceName + " stopped.");
    }

    @Override
    public void billCustomer(int units) {
        double amount = units * ratePerUnit;
        processPayment(units, amount);
    }

    public void billCustomer(int units, double discountPercent) {
        double amount = units * ratePerUnit;
        double discount = amount * (discountPercent / 100);
        double finalAmount = amount - discount;
        processPaymentWithDiscount(units, amount, discount, finalAmount);
    }

    public void billCustomer(String customerId, int units) {
        System.out.println("Billing customer ID: " + customerId);
        billCustomer(units);
    }

    protected void processPaymentWithDiscount(int units, double originalAmount, double discount, double finalAmount) {
        System.out.printf("Billing %d units: ₹%.2f - ₹%.2f (discount) = ₹%.2f%n",
                units, originalAmount, discount, finalAmount);
    }

    protected abstract void processPayment(int units, double amount);
}
