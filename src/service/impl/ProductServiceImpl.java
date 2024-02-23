package service.impl;

import file.FileHandler;
import modal.Product;
import service.ProductService;
import utils.Helper;
import utils.RenderMenu;
import utils.TableFormatter;
import utils.ValidateInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {

    private static final FileHandler fileHandler = new FileHandler();


    @Override
    public void writeObject(List<Product> productList) {
        fileHandler.writeListToFile(productList);
    }
    @Override
    public List<Product> readObject() {
        return fileHandler.readListFile();
    }

    @Override
    public void showAllProduct() {
        List<Product> products = fileHandler.readListFile();
        TableFormatter.displayTable(products);
    }
    @Override
    public void editProduct() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = fileHandler.readListFile();
        System.out.print("Enter code to update : ");
        String code = scanner.nextLine();
        for (Product product : products){
            if (product.getCode().equals(code)){
                TableFormatter.showOneProduct(product);
                RenderMenu.updateMenu();
                String userInput = ValidateInput.validateInputString("> Option [1-5] : ","! Please Input from 1-5","[1-5]+",scanner);
                switch (userInput) {
                    case "1" -> {
                        System.out.print("Enter NAME : ");
                        String name = scanner.nextLine();
                        System.out.print("Enter PRICE : ");
                        double price = Double.parseDouble(scanner.nextLine());
                        Integer quantity = ValidateInput.validateInputNumber("Enter product Quantity : ","! Product quantity cannot be decimal number",scanner);
                        Product product1 = new Product(product.getCode(),name,price,quantity,product.getImported());
                        System.out.println("#".repeat(40));
                        System.out.println(STR."# New Product detail of \{product.getCode()}");
                        TableFormatter.showOneProduct(product1);
                        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                        if (isSure.equalsIgnoreCase("y")){
                            product.setCode(product1.getCode());
                            product.setName(product1.getName());
                            product.setPrice(product1.getPrice());
                            product.setQuantity(product1.getQuantity());
                            product.setImported(product1.getImported());
                        }
                    }
                    case "2" -> {
                        String newName = ValidateInput.validateInputString("> Please Enter new Product name : ", "! Please Input alphabet only", "[a-zA-Z\\s]+", scanner);
                        Product product1 = new Product(product.getCode(),newName, product.getPrice(), product.getQuantity(), product.getImported());
                        System.out.println("#".repeat(40));
                        System.out.println(STR."# New Product detail of \{product.getCode()}");
                        TableFormatter.showOneProduct(product1);
                        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                        if (isSure.equalsIgnoreCase("y"))
                            product.setName(product1.getName());
                    }
                    case "3" -> {
                        double newPrice = Double.parseDouble(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
                        Product product1 = new Product(product.getCode(),product.getName(), newPrice, product.getQuantity(), product.getImported());
                        System.out.println("#".repeat(40));
                        System.out.println(STR."# New Product detail of \{product.getCode()}");
                        TableFormatter.showOneProduct(product1);
                        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                        if (isSure.equalsIgnoreCase("y"))
                            product.setPrice(product1.getPrice());
                    }
                    case "4" -> {
                        int newQty = Integer.parseInt(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
                        Product product1 = new Product(product.getCode(),product.getName(), product.getPrice(), newQty, product.getImported());
                        System.out.println("#".repeat(40));
                        System.out.println(STR."# New Product detail of \{product.getCode()}");
                        TableFormatter.showOneProduct(product1);
                        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                        if (isSure.equalsIgnoreCase("y"))
                            product.setQuantity(product1.getQuantity());
                    }
                }

            }
        }
        fileHandler.writeListToFile(products);
    }

    @Override
    public void searchProduct() {

    }
}
