package TeleSystem;

import java.util.*;

public class TelecomSystem {
    private Map<String, Service> availableServices = new HashMap<>();
    private Map<String, Customer> customers;

    public TelecomSystem() {
        customers = new HashMap<>();
        initializeServices();
    }

    private void initializeServices() {
        addService(new Service("Caller Tune", "Customize the ring-back tone"));
        addService(new Service("Missed Call Alert", "Receive alerts for missed calls"));
        addService(new Service("Voicemail", "Let callers leave voice messages"));
        addService(new Service("Data Pack", "Internet data package for browsing"));
    }

    public void addService(Service service) {
        availableServices.put(service.getName().toLowerCase(), service);
    }

    public Service getServiceByName(String name) {
        return availableServices.get(name.toLowerCase());
    }

    public void listAllServices() {
        System.out.println("\nAvailable Value-Added Services:");
        for (Service service : availableServices.values()) {
            System.out.println("  • " + service);
        }
    }

    public void addCustomer(String customerId, String name) {
        if (customers.containsKey(customerId)) {
            System.out.println("Customer already exists with ID: " + customerId);
        } else {
            customers.put(customerId, new Customer(customerId, name));
            System.out.println("Customer added successfully.");
        }
    }

    public Customer getCustomer(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("No customer found with ID: " + customerId);
        }
        return customer;
    }

    public void displayAllCustomerSummaries() {
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }

        for (Customer c : customers.values()) {
            c.displaySummary();
        }
    }


    public void searchCustomerByName(String name) {
        System.out.println("\nSearching for customers with name containing: " + name);
        boolean found = false;
        for (Customer customer : customers.values()) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                customer.displaySummary();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No customers found with the name: " + name);
        }
    }

    public void searchCustomerByPhoneNumber(String phoneNumber) {
        System.out.println("\nSearching for customers who called: " + phoneNumber);
        boolean found = false;
        for (Customer customer : customers.values()) {
            for (Call call : customer.getCallHistory()) {
                if (call.getPhoneNumber().equals(phoneNumber)) {
                    customer.displaySummary();
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No customers found who called the number: " + phoneNumber);
        }
    }
    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

}
