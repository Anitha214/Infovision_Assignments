package CallRecordAnalyzer;

import java.util.*;
import java.util.stream.*;
import java.time.*;

public class CallRecordManager {
    private List<CallRecord> records;

    public CallRecordManager(List<CallRecord> records) {
        this.records = records;
    }

    public void totalAndAverageCallDuration() {
        int totalDuration = records.stream()
                .mapToInt(CallRecord::getDurationInSeconds)
                .sum();

        double avgDuration = records.stream()
                .mapToInt(CallRecord::getDurationInSeconds)
                .average()
                .orElse(0);

        System.out.println("Total Duration: " + totalDuration + " seconds");
        System.out.println("Average Duration: " + avgDuration + " seconds");
    }

    public void longestCalls(int topN) {
        records.stream()
                .sorted(Comparator.comparingInt(CallRecord::getDurationInSeconds).reversed())
                .limit(topN)
                .forEach(System.out::println);
    }

    public void frequentCallers() {
        Map<String, Long> callerFrequency = records.stream()
                .collect(Collectors.groupingBy(CallRecord::getCallerId, Collectors.counting()));

        callerFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> System.out.println("Caller: " + e.getKey() + ", Calls: " + e.getValue()));
    }

    public void groupByOperator() {
        Map<String, List<CallRecord>> grouped = records.stream()
                .collect(Collectors.groupingBy(CallRecord::getOperator));

        grouped.forEach((operator, recs) -> {
            System.out.println("Operator: " + operator);
            recs.forEach(System.out::println);
        });
    }

    public void groupByLocation() {
        Map<String, List<CallRecord>> grouped = records.stream()
                .collect(Collectors.groupingBy(CallRecord::getLocation));

        grouped.forEach((location, recs) -> {
            System.out.println("Location: " + location);
            recs.forEach(System.out::println);
        });
    }

    public void filterByDuration(int minDuration) {
        List<CallRecord> filtered = records.stream()
                .filter(r -> r.getDurationInSeconds() > minDuration)
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No call records found with duration greater than " + minDuration + " seconds.");
        } else {
            filtered.forEach(System.out::println);
        }
    }


    public void filterByDate(LocalDate date) {
        List<CallRecord> filtered = records.stream()
                .filter(r -> r.getCallTime().toLocalDate().equals(date))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No call records found for date: " + date);
        } else {
            filtered.forEach(System.out::println);
        }
    }
    
    public void searchByCallerId(String callerId) {
        List<CallRecord> callerRecords = records.stream()
                .filter(r -> r.getCallerId().equalsIgnoreCase(callerId))
                .collect(Collectors.toList());

        if (callerRecords.isEmpty()) {
            System.out.println("No calls found for caller: " + callerId);
        } else {
            System.out.println("Calls made by: " + callerId);
            callerRecords.forEach(System.out::println);
        }
    }


}
