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
}
