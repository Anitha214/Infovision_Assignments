package day3task;

public class TelecomApp {
    public static void main(String[] args) throws InterruptedException {
        SMSService sms = new SMSService(1.5);
        VoiceCallService voice = new VoiceCallService(2.0);

        // Create billing tasks
        Thread smsThread1 = new Thread(new BillingTask(sms, 10));
        Thread smsThread2 = new Thread(new BillingTask(sms, 10, 10.0));
        Thread smsThread3 = new Thread(new BillingTask(sms, "SMS123", 5));

        Thread voiceThread1 = new Thread(new BillingTask(voice, 5));
        Thread voiceThread2 = new Thread(new BillingTask(voice, 5, 20.0));
        Thread voiceThread3 = new Thread(new BillingTask(voice, "VOICE456", 3));

        // Start threads
        smsThread1.start();
        smsThread2.start();
        smsThread3.start();

        voiceThread1.start();
        voiceThread2.start();
        voiceThread3.start();

        // Wait for all threads to finish
        smsThread1.join();
        smsThread2.join();
        smsThread3.join();

        voiceThread1.join();
        voiceThread2.join();
        voiceThread3.join();

        System.out.println("\nAll billing tasks completed.");
    }
}
