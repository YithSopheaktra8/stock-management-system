package service.impl;

import modal.Product;
import service.ProductService;
import utils.Helper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {

    private static final Helper helper = new Helper();

    @Override
    public void writeObjectToFile() {
        Scanner scanner = new Scanner(System.in);
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("data.txt"))){
            Product product = helper.inputProduct(scanner);
            List<Product> products = new ArrayList<>();
            products.add(product);
            List<List<Product>> allProduct = new ArrayList<>();
            allProduct.add(products);
            objectOutputStream.writeObject(allProduct);
            System.out.println("Write success");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<Product> readListObjectFromFile() {
        List<Product> productLists = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data.txt"))) {
            List<List<Product>> products = (List<List<Product>>) objectInputStream.readObject();
            for (List<Product> productList : products){
                productLists.addAll(productList);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return productLists;
    }
    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.writeObjectToFile();
        List<Product> productList = productService.readListObjectFromFile();
        System.out.println(productList);
    }
}
