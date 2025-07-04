import java.util.*;

class CallLog {
    private Call call;
    private double cost;

    public CallLog(Call call, double cost) {
        this.call = call;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public Call getCall() {
        return call;
    }
}