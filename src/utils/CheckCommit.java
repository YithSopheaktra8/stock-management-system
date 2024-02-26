package utils;

import file.FileHandler;

import java.io.BufferedWriter;
import java.io.*;
import java.io.IOException;

public class CheckCommit {
    private static final String COMMIT_STATUS_FILE = "commit_status.txt";
    public static boolean dataCommitted = false;

    public static void saveCommitStatus() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMMIT_STATUS_FILE))) {
            writer.write(String.valueOf(dataCommitted));
        } catch (IOException e) {
            // Handle the exception (e.g., write error)
            e.printStackTrace();
        }
    }
    public static void loadCommitStatus() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COMMIT_STATUS_FILE))) {
            String line = reader.readLine();
            if (line != null && line.trim().equalsIgnoreCase("true")) {
                dataCommitted = true;
                FileHandler.isCommitted = true;
            }
        } catch (IOException e) {
            // Handle the exception (e.g., file not found, read error)
            e.printStackTrace();
        }
    }
}
