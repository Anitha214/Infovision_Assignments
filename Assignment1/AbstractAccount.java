package Bank;

import java.util.Scanner;

public abstract class AbstractAccount implements AccountInterface {
    protected String accountHolderName;
    protected String branchName;
    protected String phoneNumber;
    protected String panCard;
    protected String aadharCard;
   protected String address;
    

    Scanner scanner = new Scanner(System.in);

 
        public void collectCommonDocuments() {
            System.out.print("Enter Account Holder Name: ");
            accountHolderName = scanner.nextLine();
            
            System.out.print("Enter Branch: ");
            branchName = scanner.nextLine();
            

            while (true) {
                System.out.print("Enter Phone Number: ");
                phoneNumber = scanner.nextLine();
                if (phoneNumber.matches("^[6-9]\\d{9}$")) {
                    break;
                } else {
                    System.out.println("Invalid phone number. Please enter a 10-digit number ");
                }
            }

           
            while (true) {
                System.out.print("Enter PAN Card Number: ");
                panCard = scanner.nextLine();
                if (panCard.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
                    break;
                } else {
                    System.out.println("Invalid PAN format. (e.g., ABCDE1234F).");
                }
            }

            while (true) {
                System.out.print("Enter Aadhar Card Number: ");
                aadharCard = scanner.nextLine().trim();
                if (aadharCard.matches("\\d{12}")) {
                    break;
                } else {
                    System.out.println("Invalid Aadhar number. It must be exactly 12 digits.");
                }
            }
    
        
        System.out.print("Enter Address: ");
        address = scanner.nextLine();
        
    }
	    
}


