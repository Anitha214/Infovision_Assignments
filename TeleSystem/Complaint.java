package TeleSystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Complaint {
    private String complaintText;
    private Date timestamp;

    public Complaint(String complaintText) {
        this.complaintText = complaintText;
        this.timestamp = new Date();
    }

    public String getComplaintText() {
        return complaintText;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return "Complaint: " + complaintText + " | Filed on: " + formatter.format(timestamp);
    }
}
