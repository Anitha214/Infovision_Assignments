class PostpaidPlan implements Plan {
    @Override
    public double getPerMinuteRate() {
        return 1.0;
    }
}