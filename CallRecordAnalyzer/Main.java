package CallRecordAnalyzer;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	List<CallRecord> records = CallDataSeeder.generateSampleRecords();
        CallRecordManager analyzer = new CallRecordManager(records);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Telecom Call Records Analyzer =====");
            System.out.println("1. Show total & average call duration");
            System.out.println("2. Show top 3 longest calls");
            System.out.println("3. Show most frequent callers");
            System.out.println("4. Group calls by operator");
            System.out.println("5. Group calls by location");
            System.out.println("6. Filter calls longer than X seconds");
            System.out.println("7. Filter calls on specific date (YYYY-MM-DD)");
            System.out.println("8. Search by Caller ID");
            System.out.println("9. Exit");
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    analyzer.totalAndAverageCallDuration();
                    break;
                case 2:
                    analyzer.longestCalls(3);
                    break;
                case 3:
                    analyzer.frequentCallers();
                    break;
                case 4:
                    analyzer.groupByOperator();
                    break;
                case 5:
                    analyzer.groupByLocation();
                    break;
                case 6:
                    System.out.print("Enter minimum duration in seconds: ");
                    int min = scanner.nextInt();
                    analyzer.filterByDuration(min);
                    break;
                case 7:
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateStr = scanner.nextLine();
                    analyzer.filterByDate(LocalDate.parse(dateStr));
                    break;
                case 8:
                    System.out.print("Enter Caller ID: ");
                    String callerId = scanner.nextLine();
                    analyzer.searchByCallerId(callerId);
                    break;
                case 9:
                    System.out.println("Good Byee..");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
