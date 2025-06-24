package TeleSystem;

import java.util.*;

public class Customer {
    private String customerId;
    private String name;

    private List<Call> callHistory;
    private Set<Service> subscribedServices;
    private List<Complaint> complaints;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.callHistory = new ArrayList<>();
        this.subscribedServices = new HashSet<>();
        this.complaints = new ArrayList<>();
    }

    public void recordCall(String number, int duration) {
        callHistory.add(new Call(number, duration));
    }

    public void subscribeService(Service service) {
        if (subscribedServices.add(service)) {
            System.out.println(name + " subscribed to: " + service.getName());
        } else {
            System.out.println(name + " already has the service: " + service.getName());
        }
    }

    public void unsubscribeService(Service service) {
        if (subscribedServices.remove(service)) {
            System.out.println(name + " unsubscribed from: " + service.getName());
        } else {
            System.out.println(service.getName() + " is not currently subscribed by " + name);
        }
    }

    public void fileComplaint(String complaintText) {
        complaints.add(new Complaint(complaintText));
    }

    public void viewComplaints() {
        if (complaints.isEmpty()) {
            System.out.println("No complaints found.");
        } else {
            for (Complaint c : complaints) {
                System.out.println(c);
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<Call> getCallHistory() {
        return callHistory;
    }

    public void displaySummary() {
        System.out.println("\nCustomer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Subscribed Services: " + subscribedServices);
        System.out.println("Call History:");
        if (callHistory.isEmpty()) {
            System.out.println("  No calls made.");
        } else {
            for (Call c : callHistory) {
                System.out.println("  " + c);
            }
        }
        System.out.println("Complaints:");
        if (complaints.isEmpty()) {
            System.out.println("  No complaints filed.");
        } else {
            for (Complaint c : complaints) {
                System.out.println("  " + c);
            }
        }
    }


    public String getCustomerId() {
        return customerId;
    }
}
