class CallSimulator implements Runnable {
    private Call call;

    public CallSimulator(Call call) {
        this.call = call;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            call.endCall();
            System.out.println("Call from " + call.getCaller().getName() + " to " + call.getReceiver().getName() +
                    " ended Duration: " + call.getDuration() + " seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
