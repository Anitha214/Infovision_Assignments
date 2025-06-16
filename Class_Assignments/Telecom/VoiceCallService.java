package day3task;

public class VoiceCallService extends BaseService {
	public VoiceCallService(double rate) {
	    super("Voice Call Service", rate);
	}

	public VoiceCallService(VoiceCallService other) {
	    super(other);
	}

	public VoiceCallService(VoiceCallService other, boolean deepCopy) {
	    super(other, deepCopy);
	}


    @Override
    protected void processPayment(int units, double amount) {
        System.out.printf("Billing Calls: %d mins @ ₹%.2f = ₹%.2f%n", units, ratePerUnit, amount);
    }
  
    
}