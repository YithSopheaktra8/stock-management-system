package utils;

import modal.Product;

import java.util.Random;
import java.util.Scanner;

public class Helper {
    public static String textGreen = "\u001B[32m";
    public static String textOrange = "\u001B[38;2;255;165;0m";
    public static String textAccentBlue = "\u001B[94m";
    public static String resetColor = "\u001B[0m";
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
                System.out.print(STR."\{Helper.textOrange}\rLoading product from DATA_SOURCE  \{animation.charAt(i++ % animation.length())}\{Helper.resetColor}");
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

    public static void editAllProductInfo(Product product, Scanner scanner) {
        String name = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Please Enter new Product name : \{Helper.resetColor}", STR."\{Helper.textOrange}! Please Input Alphabet and Number only\{Helper.resetColor}", "[0-9a-zA-Z\\s]+", scanner);
        double price = Double.parseDouble(ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Please Enter new Product price : \{Helper.resetColor}", STR."\{Helper.textOrange}! Please Input Decimal only\{Helper.resetColor}", "[0-9]+", scanner));
        Integer quantity = ValidateInput.validateInputNumber(STR."\{Helper.textAccentBlue}> Please Enter product Quantity : \{Helper.resetColor}",STR."\{Helper.textOrange}Product quantity cannot be decimal number\{Helper.resetColor}", scanner);
        Product product1 = new Product(product.getCode(),name,price,quantity, product.getImported());
        System.out.println("#".repeat(40));
        System.out.println(STR."\{Helper.textGreen}# New Product detail of \{product.getCode()}\{Helper.resetColor}");
        TableFormatter.showOneProduct(product1);
        String isSure = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Are you sure to update? [Y/n] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please input y or n (y = yes),(n = no)\{Helper.resetColor}","^[yYnN]+$", scanner);
        if (isSure.equalsIgnoreCase("y")){
            product.setCode(product1.getCode());
            product.setName(product1.getName());
            product.setPrice(product1.getPrice());
            product.setQuantity(product1.getQuantity());
            product.setImported(product1.getImported());
            System.out.println(STR."\{Helper.textGreen}# Product updated successfully\{Helper.resetColor}");
        }
    }
    public static void editProductName(Product product, Scanner scanner) {
        String newName = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Please Enter new Product name : \{Helper.resetColor}", STR."\{Helper.textOrange}! Please Input Alphabet and Number only\{Helper.resetColor}", "[0-9a-zA-Z\\s]+", scanner);
        Product product1 = new Product(product.getCode(),newName, product.getPrice(), product.getQuantity(), product.getImported());
        System.out.println("#".repeat(40));
        System.out.println(STR."\{Helper.textGreen}# New Product detail of \{product.getCode()}\{Helper.resetColor}");
        TableFormatter.showOneProduct(product1);
        String isSure = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Are you sure to update? [Y/n] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please input y or n (y = yes),(n = no)\{Helper.resetColor}","^[yYnN]+$", scanner);
        if (isSure.equalsIgnoreCase("y")){
            product.setName(product1.getName());
            System.out.println(STR."\{Helper.textGreen}# Product updated successfully\{Helper.resetColor}");
        }
    }
    public static void editProductPrice(Product product, Scanner scanner) {
        double newPrice = Double.parseDouble(ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Please Enter new Product price : \{Helper.resetColor}", STR."\{Helper.textOrange}! Please Input Decimal only\{Helper.resetColor}", "[0-9]+", scanner));
        Product product1 = new Product(product.getCode(), product.getName(), newPrice, product.getQuantity(), product.getImported());
        System.out.println("#".repeat(40));
        System.out.println(STR."\{Helper.textGreen}# New Product detail of \{product.getCode()}\{Helper.resetColor}");
        TableFormatter.showOneProduct(product1);
        String isSure = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Are you sure to update? [Y/n] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please input y or n (y = yes),(n = no)\{Helper.resetColor}","^[yYnN]+$", scanner);
        if (isSure.equalsIgnoreCase("y")){
            product.setPrice(product1.getPrice());
            System.out.println(STR."\{Helper.textGreen}# Product updated successfully\{Helper.resetColor}");
        }
    }

    public static void editQuantity(Product product, Scanner scanner) {
        int newQty = Integer.parseInt(ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Please Enter new Product price : \{Helper.resetColor}", STR."\{Helper.textOrange}! Please Input Decimal only\{Helper.resetColor}", "[0-9]+", scanner));
        Product product1 = new Product(product.getCode(), product.getName(), product.getPrice(), newQty, product.getImported());
        System.out.println("#".repeat(40));
        System.out.println(STR."\{Helper.textGreen}# New Product detail of \{product.getCode()}\{Helper.resetColor}");
        TableFormatter.showOneProduct(product1);
        String isSure = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Are you sure to update? [Y/n] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please input y or n (y = yes),(n = no)\{Helper.resetColor}","^[yYnN]+$", scanner);
        if (isSure.equalsIgnoreCase("y")){
            product.setQuantity(product1.getQuantity());
            System.out.println(STR."\{Helper.textGreen}# Product updated successfully\{Helper.resetColor}");
        }
    }
}
