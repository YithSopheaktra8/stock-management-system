package utils;

import controller.ProductController;
import file.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Commit {
    public static final String STATUS_FILE_PATH = "commit_status.txt";
    public static boolean isTransactionUpdated = false;

    public static void closeProgram() {
        // Save the status of committed or uncommitted changes to a file
        saveCommitStatus();

        // Simulating closing the program...
        System.out.println("Closing the program...");
        // ... (your logic for closing the program)
    }
    public static void saveCommitStatus() {
        try {
            Files.writeString(Path.of(STATUS_FILE_PATH), String.valueOf(isTransactionUpdated));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkUncommittedChanges() {
        ProductController productController = new ProductController();
        File statusFile = new File(STATUS_FILE_PATH);
        if (statusFile.exists()) {
            try {
                String content = Files.readString(Path.of(STATUS_FILE_PATH));
                boolean uncommittedChanges = Boolean.parseBoolean(content);
                if (uncommittedChanges) {
                    FileHandler.isCommitted = true;
                    System.out.println("Alert: You have uncommitted changes from the previous session.");
                    productController.commitToDataSource();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
