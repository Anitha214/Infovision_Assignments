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
            System.out.print("Choose an option: ");
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
                    System.out.println("Invalid choice.");
                    continue;
            }
          
           
            boolean success = account.openAccount();
            if (!success) {
                continue;
            }
            account.displayAccountDetails();
            
            System.out.println("\nCalculating Interest:");
            double interest = account.calculateInterest();

            
            boolean continueWithdrawal = true;
            while (continueWithdrawal) {
                double amount = 0;
                while (true) {
                    try {
                        System.out.print("Enter amount to withdraw: ");
                        amount = Double.parseDouble(scanner.nextLine().trim());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a numeric amount.");
                    }
                }

                if (account instanceof SavingsAccount) {
                    ((SavingsAccount) account).withdraw(amount);
                } else if (account instanceof CurrentAccount) {
                    ((CurrentAccount) account).withdraw(amount);
                }

                System.out.print("Do you want to withdraw again? (yes/no): ");
                String withdrawAgain = scanner.nextLine().trim().toLowerCase();
                if (!withdrawAgain.equals("yes")) {
                    continueWithdrawal = false;
                }
            }

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
