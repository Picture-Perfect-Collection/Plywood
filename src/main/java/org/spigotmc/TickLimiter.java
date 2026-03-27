package org.spigotmc;

public class TickLimiter {

    private final int maxTime;
    private long startTime;

    public TickLimiter(int maxtime) {
        this.maxTime = maxtime;
    }

    public void initTick() {
        startTime = System.nanoTime();
    }

    public boolean shouldContinue() {
        long remaining = System.nanoTime() - startTime;
        return remaining < (maxTime * 1000000L);
    }
}
