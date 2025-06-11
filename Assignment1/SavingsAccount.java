package Bank;


public class SavingsAccount extends AbstractAccount {


    @Override
    public void openAccount() {
        System.out.println("\nOpening Savings Account...");
        collectCommonDocuments();
        System.out.println(accountHolderName + " Your Savings Account Opened Successfully!\n");
    }

    @Override
    public void showDetails() {
        System.out.println("----- Savings Account Details -----");
        System.out.println("Name: " + accountHolderName);
        System.out.println("Branch: " + branchName);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("PAN: " + panCard);
        System.out.println("Aadhar: " + aadharCard);
        System.out.println("Address: " + address);
        
    }
}
