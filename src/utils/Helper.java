package utils;

import java.util.Random;

public class Helper {
    public static String generateProductCode(){
        Random random = new Random();
        int sixDigit = 100000 + random.nextInt(900000);
        return STR."CSTAD-\{sixDigit}";
    }
    public static int goToSpecificPage(int desiredPage, int totalPages) {
        return Math.max(1, Math.min(desiredPage, totalPages));
    }

    // Create a new thread to display the loading animation
    public static Thread getThread() {
        Thread loadingThread = new Thread(() -> {
            String animation = "|/-\\";
            int i = 0;
            while (true) {
                System.out.print(STR."\rLoading \{animation.charAt(i++ % animation.length())}");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        loadingThread.start(); // Start the loading animation
        return loadingThread;
    }
}
