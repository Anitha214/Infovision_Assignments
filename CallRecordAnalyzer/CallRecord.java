package CallRecordAnalyzer;

import java.time.LocalDateTime;

public class CallRecord {
    private String callerId;
    private String receiverId;
    private LocalDateTime callTime;
    private int durationInSeconds;
    private String operator;
    private String location;

    public CallRecord(String callerId, String receiverId, LocalDateTime callTime,
                      int durationInSeconds, String operator, String location) {
        this.callerId = callerId;
        this.receiverId = receiverId;
        this.callTime = callTime;
        this.durationInSeconds = durationInSeconds;
        this.operator = operator;
        this.location = location;
    }

    // Getters
    public String getCallerId() { return callerId; }
    public String getReceiverId() { return receiverId; }
    public LocalDateTime getCallTime() { return callTime; }
    public int getDurationInSeconds() { return durationInSeconds; }
    public String getOperator() { return operator; }
    public String getLocation() { return location; }

    @Override
    public String toString() {
        return String.format("Caller: %s, Receiver: %s, Time: %s, Duration: %ds, Operator: %s, Location: %s",
                callerId, receiverId, callTime, durationInSeconds, operator, location);
    }
}
