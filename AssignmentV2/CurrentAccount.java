package Bank;

public class CurrentAccount extends AbstractAccount {
    private static final double MIN_DEPOSIT = 5000;
    private static final double MIN_BALANCE = 2000;
    private static final double PENALTY = 500;

    private String companyName;
    private String gstNumber;

   
    	@Override
    	public boolean openAccount() {

        System.out.println("\nOpening Current Account...");
        collectCommonDocuments();

        System.out.print("Enter Company Name: ");
        companyName = scanner.nextLine();

        System.out.print("Enter GST Number: ");
        gstNumber = scanner.nextLine();
        
        double deposit = 0;
        while (true) {
            try {
                System.out.print("Enter Initial Deposit (Min ₹5000): ");
                deposit = Double.parseDouble(scanner.nextLine().trim());
                if (deposit < MIN_DEPOSIT) {
                    System.out.println("Minimum deposit must be ₹5000. Account not created.");
                    return false;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        
        this.balance = deposit;
	    System.out.println("Current Account Opened Successfully!\n");
	    return true;
        
    }

    	@Override
    	public void displayAccountDetails() {
    	    System.out.println("----- Account Details -----");
    	    showCommonDetails();
    
        System.out.println("Company Name: " + companyName);
        System.out.println("GST Number: " + gstNumber);
    }

    	@Override
    	public void withdraw(double amount) {
    	    try {
    	        if (amount <= 0) {
    	            throw new InvalidAmountException("Amount must be greater than 0.");
    	        }

    	        if (amount > balance) {
    	            throw new InsufficientFundsException("Insufficient balance.");
    	        }

    	        balance -= amount;

    	        if (balance < MIN_BALANCE) {
    	            throw new MinBalanceException("Balance below minimum. Penalty of ₹" + PENALTY + " applied.");
    	        }

    	        System.out.println("\nWithdrawal of ₹" + amount + " successful.");
    	        System.out.println("Remaining Balance: ₹" + balance);

    	    } catch (InvalidAmountException | InsufficientFundsException | MinBalanceException e) {
    	        System.out.println("Error: " + e.getMessage());

    	        if (e instanceof MinBalanceException) {
    	            balance -= PENALTY;
    	            System.out.println("Penalty applied. New balance: ₹" + balance);
    	        }

    	    } catch (Exception e) {
    	        System.out.println("Unexpected error: " + e.getMessage());
    	    }
    	}


    @Override
    public double calculateInterest() {
        return 0; 
    }
}
