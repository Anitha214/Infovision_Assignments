package TeleSystem;

public class Call {
    private String phoneNumber;
    private int duration;

    public Call(String phoneNumber, int duration) {
        this.phoneNumber = phoneNumber;
        this.duration = duration;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Called: " + phoneNumber + " | Duration: " + duration + " mins";
    }
}
