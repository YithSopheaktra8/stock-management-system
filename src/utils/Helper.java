package utils;

import modal.Product;

import java.time.LocalDate;
import java.util.Scanner;

public class Helper {
    public static Product inputProduct(Scanner scanner){
        System.out.print("Enter CODE : ");
        String code = scanner.nextLine();
        System.out.print("Enter NAME : ");
        String name = scanner.nextLine();
        System.out.print("Enter PRICE : ");
        double price = Double.parseDouble(scanner.nextLine());
        Integer quantity = ValidateInput.validateInputNumber("Enter product Quantity : ","Product quantity cannot be decimal number",scanner);
        return new Product(code,name,price,quantity, LocalDate.now());
    }

    public static void updateAll(Product product) {
        Scanner scanner = new Scanner(System.in);
        Product product1 = inputProduct(scanner);
        product.setCode(product1.getCode());
        product.setName(product1.getName());
        product.setPrice(product1.getPrice());
        product.setQuantity(product1.getQuantity());
        product.setImported(product1.getImported());
    }
}
