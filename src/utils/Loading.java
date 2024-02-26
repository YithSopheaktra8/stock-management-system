package utils;

public class Loading {
    private static volatile boolean taskCompleted = false;

    public static void display() {
        Thread loadingThread = new Thread(() -> {
            performTask();
            taskCompleted = true;
        });

        loadingThread.start();

        int dotCount = 0;
        try {
            while (true) {
                // Print loading animation while the task is running
                System.out.print("Loading");
                for (int i = 0; i < 5; i++) {
                    System.out.print(".");
                    dotCount++;
                    Thread.sleep(300); // Adjust the delay to control the animation speed
                }

                // Check if the task is completed
                if (taskCompleted) {
                    System.out.println("\nTask completed!");
                    break;
                } else {
                    // Reset the loading animation after printing 5 dots
                    System.out.print("\r"); // Move the cursor back to the beginning of the line
                    for (int i = 0; i < dotCount; i++) {
                        System.out.print(" "); // Clear previously printed dots
                    }
                    System.out.print("\r"); // Move the cursor back to the beginning of the line
                    dotCount = 0; // Reset the dot count
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void performTask() {
        // Simulate a time-consuming task
        try {
            Thread.sleep(5000); // Simulate a task that takes 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
