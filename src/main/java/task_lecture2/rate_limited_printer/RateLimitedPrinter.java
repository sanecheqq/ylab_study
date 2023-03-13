package task_lecture2.rate_limited_printer;

public class RateLimitedPrinter {
    private final double interval;
    private long prevPrintTime;

    public RateLimitedPrinter(double interval) {
        this.interval = interval;
        prevPrintTime = -1;
    }

    public void print(String message) {
        long currentTime = System.currentTimeMillis();
        long prevPrintTime_copy = getPrevPrintTime();
        if (prevPrintTime_copy == -1 || currentTime - prevPrintTime_copy >= getInterval()) {
            printMessage(message);
            setPrevPrintTime(currentTime);
        }
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    public void setPrevPrintTime(long prevPrintTime) {
        this.prevPrintTime = prevPrintTime;
    }

    public double getInterval() {
        return interval;
    }

    public long getPrevPrintTime() {
        return prevPrintTime;
    }
}
