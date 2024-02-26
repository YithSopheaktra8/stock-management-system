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
}
