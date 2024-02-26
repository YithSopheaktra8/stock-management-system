package utils;

import modal.Product;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Helper {
    public static String generateProductCode(){
        Random random = new Random();
        int sixDigit = 100000 + random.nextInt(900000);
        return STR."CSTAD-\{sixDigit}";
    }
    public static int goToPreviousPage(int currentPage) {
        return Math.max(1, currentPage - 1);
    }
    public static int goToFirstPage() {
        return 1;
    }
    public static int goToSpecificPage(int desiredPage, int totalPages) {
        return Math.max(1, Math.min(desiredPage, totalPages));
    }

    public static int goToNextPage(int currentPage, int totalPages) {
        return Math.min(totalPages, currentPage + 1);
    }

    public static int goToLastPage(int totalPages) {
        return totalPages;
    }
}
