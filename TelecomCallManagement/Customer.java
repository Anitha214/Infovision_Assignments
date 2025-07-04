

class Customer {
    private String customerId;
    private String name;
    private Plan plan;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Plan getPlan() {
        return plan;
    }
}