package com.UsageService.util;

import java.util.Random;

public class RandomUsageGenerator {

    private static final Random random = new Random();
    public static double getRandomDataUsage() {
        return 100 + (5000 - 100) * random.nextDouble(); // 100MB to 5000MB
    }

    public static int getRandomCallMinutes() {
        return 1 + random.nextInt(120); // 1 to 120 minutes
    }

    public static Long getRandomUserId() {
        return 1L + random.nextLong(300);
    }

    public static Long getRandomPlanId() {
        return 1L + random.nextLong(10);
    }

}
