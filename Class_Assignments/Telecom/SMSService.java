package day3task;

public class SMSService extends BaseService {
 
	public SMSService(double rate) {
	    super("SMS Service", rate);
	}

	public SMSService(SMSService other) {
	    super(other);
	}

	public SMSService(SMSService other, boolean deepCopy) {
	    super(other, deepCopy);
	}


    @Override
    protected void processPayment(int units, double amount) {
        System.out.printf("Billing SMS: %d msgs @ ₹%.2f = ₹%.2f%n", units, ratePerUnit, amount);
    }
}

