package TeleSystem;

import java.util.Collection;
import java.util.Scanner;

public class TelecomCustomerManagement {

    private static final int CALL_RATE_PER_MIN = 1; 

    public static void main(String[] args) {
        TelecomSystem system = new TelecomSystem();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Telecom Customer Management Menu ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Record Call");
            System.out.println("3. Subscribe to Service");
            System.out.println("4. Unsubscribe from Service");
            System.out.println("5. File Complaint");
            System.out.println("6. View Customer Summary");
            System.out.println("7. Search Customer by Name");
            System.out.println("8. Search Customer by Phone Number");
            System.out.println("9. View Billing Summary");
            System.out.println("10. List All Services");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    system.addCustomer(id, name);
                    break;

                case 2:
                    System.out.print("Enter Customer ID: ");
                    id = scanner.nextLine();
                    Customer cust = system.getCustomer(id);
                    if (cust != null) {
                        System.out.print("Enter phone number called: ");
                        String number = scanner.nextLine();
                        System.out.print("Enter duration in minutes: ");
                        int duration = Integer.parseInt(scanner.nextLine());
                        cust.recordCall(number, duration);
                    }
                    break;

                case 3:
                    System.out.print("Enter Customer ID: ");
                    id = scanner.nextLine();
                    cust = system.getCustomer(id);
                    if (cust != null) {
                        system.listAllServices();
                        System.out.print("Enter service name to subscribe: ");
                        String serviceName = scanner.nextLine();
                        Service service = system.getServiceByName(serviceName);
                        if (service != null) {
                            cust.subscribeService(service);
                        } else {
                            System.out.println("Invalid service name.");
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter Customer ID: ");
                    id = scanner.nextLine();
                    cust = system.getCustomer(id);
                    if (cust != null) {
                        System.out.print("Enter service name to unsubscribe: ");
                        String serviceName = scanner.nextLine();
                        Service service = system.getServiceByName(serviceName);
                        if (service != null) {
                            cust.unsubscribeService(service);
                        } else {
                            System.out.println("Invalid service name.");
                        }
                    }
                    break;

                case 5:
                    System.out.print("Enter Customer ID: ");
                    id = scanner.nextLine();
                    cust = system.getCustomer(id);
                    if (cust != null) {
                        System.out.print("Enter complaint: ");
                        String complaint = scanner.nextLine();
                        cust.fileComplaint(complaint);
                    }
                    break;

                case 6:
                    system.displayAllCustomerSummaries();
                    break;

                case 7:
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    system.searchCustomerByName(name);
                    break;

                case 8:
                    System.out.print("Enter phone number to search: ");
                    String phone = scanner.nextLine();
                    system.searchCustomerByPhoneNumber(phone);
                    break;

                case 9:
                    System.out.println("\n--- Billing Summary (₹1 per minute) ---");
                    Collection<Customer> allCustomers = system.getAllCustomers();
                    if (allCustomers.isEmpty()) {
                        System.out.println("No customers found. Please add customers first.");
                        break;
                    }

                    boolean anyCallMade = false;
                    for (Customer c : allCustomers) {
                        int totalDuration = c.getCallHistory().stream().mapToInt(Call::getDuration).sum();
                        if (totalDuration > 0) {
                            anyCallMade = true;
                            int totalCost = totalDuration * CALL_RATE_PER_MIN;
                            System.out.println("Customer: " + c.getName() +
                                    " | Total Duration: " + totalDuration +
                                    " mins | Bill: ₹" + totalCost);
                        }
                    }

                    if (!anyCallMade) {
                        System.out.println("No call records found for billing.");
                    }
                    break;


                case 10:
                    system.listAllServices();
                    break;

                case 11:
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
