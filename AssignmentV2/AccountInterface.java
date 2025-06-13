package Bank;


public interface AccountInterface {
	boolean openAccount(); 
	void displayAccountDetails();
    void withdraw(double amount);
    double calculateInterest(); 
}
