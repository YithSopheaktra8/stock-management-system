package service.impl;

import file.FileHandler;
import modal.Product;
import service.ProductService;
import utils.Commit;
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
        fileHandler.writeListToFile(productList,FileHandler.TRANSACTION_SOURCE);
    }

    @Override
    public void showAllProduct() {
        List<Product> products = fileHandler.readListFile(FileHandler.TRANSACTION_SOURCE);
        TableFormatter.displayTable(products);
    }
    @Override
    public void editProduct() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = fileHandler.readListFile(FileHandler.TRANSACTION_SOURCE);
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
                        String newName = ValidateInput.validateInputString("> Please Enter new Product name : ", "! Please Input Alphabet and Number only", "[0-9a-zA-Z\\s]+", scanner);
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
        fileHandler.writeListToFile(products,FileHandler.TRANSACTION_SOURCE);
        Commit.isTransactionUpdated = true;
    }

    @Override
    public void searchProductByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#".repeat(40));
        System.out.println("# Search product by name");
        String productName = ValidateInput.validateInputString("Enter product name : ","! Must be Alphabet and Number only!","[0-9a-zA-Z\\s]+",scanner);
        List<Product> productList = fileHandler.readListFile(FileHandler.TRANSACTION_SOURCE);
        List<Product> searchList = new ArrayList<>();
        boolean isFound = false;
        for (Product product : productList){
            if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                searchList.add(product);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("! Product Not Found !");
        }else {
            TableFormatter.displayTable(searchList);
        }

    }

    @Override
    public void deleteProductByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#".repeat(40));
        System.out.println("# Delete a product by name");
        String productName = ValidateInput.validateInputString("Enter product name : ","! Must be Alphabet and Number only!","[0-9a-zA-Z\\s]+",scanner);
        List<Product> productList = fileHandler.readListFile(FileHandler.TRANSACTION_SOURCE);
        List<Product> listNotFound = new ArrayList<>();
        String isSure = "";
        boolean isFound = false;
        for (Product product : productList){
            if(!product.getName().equalsIgnoreCase(productName)){
                listNotFound.add(product);
            }else {
                System.out.println("#".repeat(40));
                System.out.println(STR."# Product detail of \{product.getCode()}");
                TableFormatter.showOneProduct(product);
                isSure = ValidateInput.validateInputString("Are you sure to delete? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                isFound = true;
            }
        }
        if(isFound && isSure.equalsIgnoreCase("y")){
            System.out.println("! Product has been deleted successfully !");
            fileHandler.writeListToFile(listNotFound,FileHandler.TRANSACTION_SOURCE);
            Commit.isTransactionUpdated = true;
        }
    }

    @Override
    public void commitToDataSource() {
        Scanner scanner = new Scanner(System.in);
        if(FileHandler.isCommitted){
            System.out.println("#".repeat(40));
            System.out.println("You have uncommitted transaction.");
            String isSure = ValidateInput.validateInputString("> Do you want to save or lose data? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
            if(isSure.equalsIgnoreCase("y")){
                List<Product> productList = fileHandler.readListFile(FileHandler.TRANSACTION_SOURCE);
                fileHandler.writeListToFile(productList,FileHandler.DATA_SOURCE);
                FileHandler.isCommitted = false;
                Commit.isTransactionUpdated = false;
            }
            System.out.println("No commit change");
        }else {
            System.out.println("> No commit change");
        }
    }
}
