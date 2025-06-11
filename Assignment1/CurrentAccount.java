package Bank;

public class CurrentAccount extends AbstractAccount {

    private String companyName;
    private String gstNumber;

    @Override
    public void openAccount() {
        System.out.println("\nOpening Current Account...");
        collectCommonDocuments();

        System.out.print("Enter Company Name: ");
        companyName = scanner.nextLine();

        while (true) {
            System.out.print("Enter GST Number: ");
            gstNumber = scanner.nextLine().trim().toUpperCase();
            if (gstNumber.matches("\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}")) {
                break;
            } else {
                System.out.println("Invalid GST number. Must follow the 15-character format.");
            }
        }


        System.out.println(accountHolderName + " Your Current Account Opened Successfully!\n");
    }

    @Override
    public void showDetails() {
        System.out.println("----- Current Account Details -----");
        System.out.println("Name: " + accountHolderName);
        System.out.println("Branch: " + branchName);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("PAN: " + panCard);
        System.out.println("Aadhar: " + aadharCard);
        System.out.println("Address: " + address);
        System.out.println("Company Name: " + companyName);
        System.out.println("GST Number: " + gstNumber);
    }
}
