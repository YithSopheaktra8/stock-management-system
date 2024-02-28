package utils;

import modal.Product;

import java.util.Random;
import java.util.Scanner;

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

    public static void editAllProductInfo(Product product, Scanner scanner) {
        String name = ValidateInput.validateInputString("> Please Enter new Product name : ", "! Please Input Alphabet and Number only", "[0-9a-zA-Z\\s]+", scanner);
        double price = Double.parseDouble(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
        Integer quantity = ValidateInput.validateInputNumber("Enter product Quantity : ","! Product quantity cannot be decimal number", scanner);
        Product product1 = new Product(product.getCode(),name,price,quantity, product.getImported());
        System.out.println("#".repeat(40));
        System.out.println(STR."# New Product detail of \{product.getCode()}");
        TableFormatter.showOneProduct(product1);
        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$", scanner);
        if (isSure.equalsIgnoreCase("y")){
            product.setCode(product1.getCode());
            product.setName(product1.getName());
            product.setPrice(product1.getPrice());
            product.setQuantity(product1.getQuantity());
            product.setImported(product1.getImported());
        }
    }
    public static void editProductName(Product product, Scanner scanner) {
        String newName = ValidateInput.validateInputString("> Please Enter new Product name : ", "! Please Input Alphabet and Number only", "[0-9a-zA-Z\\s]+", scanner);
        Product product1 = new Product(product.getCode(),newName, product.getPrice(), product.getQuantity(), product.getImported());
        System.out.println("#".repeat(40));
        System.out.println(STR."# New Product detail of \{product.getCode()}");
        TableFormatter.showOneProduct(product1);
        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$", scanner);
        if (isSure.equalsIgnoreCase("y"))
            product.setName(product1.getName());
    }
    public static void editProductPrice(Product product, Scanner scanner) {
        double newPrice = Double.parseDouble(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
        Product product1 = new Product(product.getCode(), product.getName(), newPrice, product.getQuantity(), product.getImported());
        System.out.println("#".repeat(40));
        System.out.println(STR."# New Product detail of \{product.getCode()}");
        TableFormatter.showOneProduct(product1);
        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$", scanner);
        if (isSure.equalsIgnoreCase("y"))
            product.setPrice(product1.getPrice());
    }

    public static void editQuantity(Product product, Scanner scanner) {
        int newQty = Integer.parseInt(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
        Product product1 = new Product(product.getCode(), product.getName(), product.getPrice(), newQty, product.getImported());
        System.out.println("#".repeat(40));
        System.out.println(STR."# New Product detail of \{product.getCode()}");
        TableFormatter.showOneProduct(product1);
        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$", scanner);
        if (isSure.equalsIgnoreCase("y"))
            product.setQuantity(product1.getQuantity());
    }
}
