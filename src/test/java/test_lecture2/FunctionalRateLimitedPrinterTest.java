package test_lecture2;

import task_lecture2.rate_limited_printer.RateLimitedPrinter;

public class FunctionalRateLimitedPrinterTest {
    public static void main(String[] args) {
        RateLimitedPrinter printer = new RateLimitedPrinter(500);
        long prevPrintTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000_000; i++) {
            Long curPrintTime = System.currentTimeMillis();
            String message = Long.toString(curPrintTime - prevPrintTime);
            printer.print(message);
            prevPrintTime = printer.getPrevPrintTime();
        }
    }
}
