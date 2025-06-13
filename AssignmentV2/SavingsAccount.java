package Bank;

public class SavingsAccount extends AbstractAccount {
    private static final double MIN_DEPOSIT = 1000;
    private static final double INTEREST_RATE = 0.04;
    private static final int MAX_WITHDRAWALS = 3;

    private int withdrawalCount = 0;

    @Override
    public boolean openAccount() {
        System.out.println("\nOpening Savings Account...");
        collectCommonDocuments();

        double deposit = 0;
        while (true) {
            try {
                System.out.print("Enter Initial Deposit (Min ₹1000): ");
                deposit = Double.parseDouble(scanner.nextLine().trim());
                if (deposit < MIN_DEPOSIT) {
                    System.out.println("Minimum deposit must be ₹1000. Account not created.");
                    return false;
                }
                break; 
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

        this.balance = deposit;
        System.out.println("Savings Account Opened Successfully!\n");
        return true;
    }


    @Override
    public void displayAccountDetails() {
        System.out.println("----- Account Details -----");
        showCommonDetails();
    }

    @Override
    public void withdraw(double amount) {
        try {
            if (withdrawalCount >= MAX_WITHDRAWALS) {
                throw new RuntimeException("Withdrawal limit reached.");
            }

            if (amount <= 0) {
                throw new InvalidAmountException("Amount must be greater than 0.");
            }

            if (amount > balance) {
                throw new InsufficientFundsException("Insufficient balance.");
            }

            balance -= amount;
            withdrawalCount++;
            System.out.println("\nWithdrawal of ₹" + amount + " successful.");
            System.out.println("Remaining Balance: ₹" + balance);

        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Runtime Error: " + e.getMessage());
        }
    }


    @Override
    public double calculateInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;  
        System.out.println("Interest Earned: ₹" + interest);
        System.out.println("Updated Balance: ₹" + balance); 
        return interest;
    }

}
