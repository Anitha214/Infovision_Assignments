package CallRecordAnalyzer;

import java.time.LocalDateTime;
import java.util.*;

public class CallDataSeeder {
    public static List<CallRecord> generateSampleRecords() {
        return Arrays.asList(
            new CallRecord("Anitha", "Krishnan", LocalDateTime.now().minusDays(1), 180, "Verizon", "New York"),
            new CallRecord("Abitha", "Kaviyarasu", LocalDateTime.now().minusDays(2), 60, "Verizon", "New York"),
            new CallRecord("Anitha", "Surya", LocalDateTime.now().minusHours(3), 300, "AT&T", "Chicago"),
            new CallRecord("Sophitha", "Raja", LocalDateTime.now().minusDays(3), 90, "T-Mobile", "Los Angeles"),
            new CallRecord("Kavitha", "Pranav", LocalDateTime.now().minusDays(5), 120, "Verizon", "New York"),
            new CallRecord("Saswathi", "Diyashana",LocalDateTime.now().minusDays(10), 240, "Vodafone", "Houston")

        );
    }
}
