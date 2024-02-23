package utils;

import modal.Product;

import java.time.LocalDate;
import java.util.Scanner;

public class Helper {
    public Product inputProduct(Scanner scanner){
        System.out.print("CODE : ");
        String code = scanner.nextLine();
        System.out.print("NAME : ");
        String name = scanner.nextLine();
        System.out.print("PRICE : ");
        Double price = scanner.nextDouble();
        System.out.print("QUANTITY : ");
        Integer quantity = scanner.nextInt();
        return new Product(code,name,price,quantity, LocalDate.now());
    }
}
