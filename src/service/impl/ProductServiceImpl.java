package service.impl;

import file.FileHandler;
import modal.Product;
import service.ProductService;
import utils.Helper;
import utils.TableFormatter;

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
                Helper.updateAll(product);
            }
        }
        fileHandler.writeListToFile(products);
    }

}
