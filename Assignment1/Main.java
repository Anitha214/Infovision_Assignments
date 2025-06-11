package Bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCreating = true;

        System.out.println("Welcome to Bank Account Opening System");

        while (continueCreating) {
            System.out.println("\n1. Savings Account");
            System.out.println("2. Current Account");
            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            AccountInterface account;

            switch (choice) {
                case 1:
                    account = new SavingsAccount();
                    break;
                case 2:
                    account = new CurrentAccount();
                    break;
                default:
                    System.out.println("Invalid choice. Please choose 1 or 2.");
                    continue;
            }

            account.openAccount();
            account.showDetails();

            System.out.print("\nDo you want to create another account? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("yes")) {
                continueCreating = false;
                System.out.println("Thank you for using our account opening service!");
            }
        }

        scanner.close();
    }
}
