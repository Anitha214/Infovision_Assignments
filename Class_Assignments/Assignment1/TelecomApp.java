package day3task;

public class TelecomApp {
    public static void main(String[] args) {
        SMSService sms = new SMSService(1.5);
        VoiceCallService voice = new VoiceCallService(2.0);

        runService(sms, 10);
        sms.billCustomer(10, 10.0);  
        sms.billCustomer("SMS123", 5); 

        System.out.println();

        runService(voice, 5);
        voice.billCustomer(5, 20.0);  
        voice.billCustomer("VOICE456", 3);  
    }

    private static void runService(BaseService service, int units) {
        service.start();
        service.billCustomer(units);
        service.stop();
        System.out.println();
    }
}
