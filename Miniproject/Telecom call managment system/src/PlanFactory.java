

class PlanFactory {
    public static Plan getPlan(String planType) {
        if ("prepaid".equalsIgnoreCase(planType)) {
            return new PrepaidPlan();
        } else if ("postpaid".equalsIgnoreCase(planType)) {
            return new PostpaidPlan();
        }
        return null;
    }
}
