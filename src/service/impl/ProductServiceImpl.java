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
                    case "1" -> Helper.updateAll(product);
                    case "2" -> {
                        String newName = ValidateInput.validateInputString("> Please Enter new Product name : ", "! Please Input alphabet only", "[a-zA-Z\\s]+", scanner);
                        product.setName(newName);
                    }
                    case "3" -> {
                        double newPrice = Double.parseDouble(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
                        product.setPrice(newPrice);
                    }
                    case "4" -> {
                        int newQty = Integer.parseInt(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
                        product.setQuantity(newQty);
                    }
                }

            }
        }
        fileHandler.writeListToFile(products);
    }
}
