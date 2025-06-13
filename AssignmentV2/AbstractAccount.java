package Bank;

import java.util.Scanner;

    public abstract class AbstractAccount implements AccountInterface {
    	 private static long nextAccountNumber = 34567890245L;
         private long accountNumber;
        protected String accountHolderName;
        protected String branchName;
        protected String address;
        protected String phoneNumber;
        protected String panCard;
        protected String aadharCard;
        protected double balance;
       

        Scanner scanner = new Scanner(System.in);


        public long getAccountNumber() {
            return accountNumber;
        }

        public AbstractAccount() {
            this.accountNumber = nextAccountNumber++;
        }

        public void collectCommonDocuments() {
            System.out.print("Enter Account Holder Name: ");
            accountHolderName = scanner.nextLine();

            System.out.print("Enter Branch: ");
            branchName = scanner.nextLine();

            while (true) {
                try {
                    System.out.print("Enter Phone Number: ");
                    phoneNumber = scanner.nextLine();
                    if (!phoneNumber.matches("^[6-9]\\d{9}$")) {
                        throw new InvalidPhoneException("Invalid phone number. Must be a 10-digit number starting with 6-9.");
                    }
                    break;
                } catch (InvalidPhoneException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter PAN Card Number: ");
                    panCard = scanner.nextLine();
                    if (!panCard.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
                        throw new InvalidPanException("Invalid PAN format. (e.g., ABCDE1234F)");
                    }
                    break;
                } catch (InvalidPanException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.print("Enter Aadhar Card Number: ");
                    aadharCard = scanner.nextLine().trim();
                    if (!aadharCard.matches("\\d{12}")) {
                        throw new InvalidAadharException("Invalid Aadhar number. It must be exactly 12 digits.");
                    }
                    break;
                } catch (InvalidAadharException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            System.out.print("Enter Address: ");
            address = scanner.nextLine();
        }


        public void showCommonDetails() {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Name: " + accountHolderName);
            System.out.println("Branch: " + branchName);
            System.out.println("Phone: " + phoneNumber);
            System.out.println("PAN: " + panCard);
            System.out.println("Aadhar: " + aadharCard);
            System.out.println("Balance: ₹" + balance);
            System.out.println("Address: " + address);
        }

        public double getBalance() {
            return balance;
        }

        protected void deposit(double amount) throws InvalidAmountException {
            if (amount <= 0) {
                throw new InvalidAmountException("Deposit amount must be greater than 0.");
            }
            balance += amount;
        }
          
            
    }
	    



