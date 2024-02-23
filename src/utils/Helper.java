package utils;

import modal.Product;

import java.time.LocalDate;
import java.util.Scanner;

public class Helper {
    public Product inputProduct(Scanner scanner){
        String code = ValidateInput.validateInputString("Enter product Code : ","Product Code cannot be spiral symbol","[0-9a-zA-Z\\s]+",scanner);
        String name = ValidateInput.validateInputString("Enter product Name : ","Product Code cannot be spiral symbol","[a-zA-Z\\s]+",scanner);
        double price = scanner.nextDouble();
        Integer quantity = ValidateInput.validateInputNumber("Enter product Quantity : ","Product quantity cannot be decimal number",scanner);
        return new Product(code,name,price,quantity, LocalDate.now());
    }
}
