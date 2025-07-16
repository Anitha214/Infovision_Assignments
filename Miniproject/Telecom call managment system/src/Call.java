import java.util.concurrent.atomic.AtomicInteger;

class Call {
    private Customer caller;
    private Customer receiver;
    private long startTime;
    private long endTime;

    public Call(Customer caller, Customer receiver) {
        this.caller = caller;
        this.receiver = receiver;
        this.startTime = System.currentTimeMillis();
    }

    public void endCall() {
        this.endTime = System.currentTimeMillis();
    }

    public long getDuration() {
        return (endTime - startTime) / 1000;
    }

    public Customer getCaller() {
        return caller;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}