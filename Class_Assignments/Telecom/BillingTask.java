
package day3task;

public class BillingTask implements Runnable {
    private BaseService service;
    private int units;
    private double discount;
    private String customerId;
    private boolean useDiscount;
    private boolean useCustomerId;

    // Constructor for billing without discount or customerId
    public BillingTask(BaseService service, int units) {
        this.service = service;
        this.units = units;
        this.useDiscount = false;
        this.useCustomerId = false;
    }

    // Constructor for billing with discount
    public BillingTask(BaseService service, int units, double discount) {
        this.service = service;
        this.units = units;
        this.discount = discount;
        this.useDiscount = true;
        this.useCustomerId = false;
    }

    // Constructor for billing with customerId
    public BillingTask(BaseService service, String customerId, int units) {
        this.service = service;
        this.customerId = customerId;
        this.units = units;
        this.useCustomerId = true;
        this.useDiscount = false;
    }

    @Override
    public void run() {
        service.start();
        if (useCustomerId) {
            service.billCustomer(customerId, units);
        } else if (useDiscount) {
            service.billCustomer(units, discount);
        } else {
            service.billCustomer(units);
        }
        service.stop();
    }
}

