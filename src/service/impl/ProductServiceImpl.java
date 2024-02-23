package service.impl;

import modal.Product;
import service.ProductService;
import utils.Helper;
import utils.TableFormatter;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductServiceImpl implements ProductService {

    private static final Helper helper = new Helper();

    @Override
    public void writeObjectToFile() {
        Scanner scanner = new Scanner(System.in);
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("data.txt"))){
            Product product3 = helper.inputProduct(scanner);
            Product product = Product.builder()
                    .code("001")
                    .name("Coke")
                    .price(2.5)
                    .quantity(50)
                    .imported(LocalDate.now())
                    .build();
            Product product1 = Product.builder()
                    .code("001")
                    .name("Coke")
                    .price(2.5)
                    .quantity(50)
                    .imported(LocalDate.now())
                    .build();
            List<Product> products = new ArrayList<>();
            products.add(product);
            products.add(product1);
            products.add(product3);
            List<List<Product>> allProduct = new ArrayList<>();
            allProduct.add(products);
            objectOutputStream.writeObject(allProduct);
            System.out.println("Write success");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void readListObjectFromFile() {
        List<Product> productLists = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data.txt"))) {
            List<List<Product>> products = (List<List<Product>>) objectInputStream.readObject();
            for (List<Product> productList : products){
                TableFormatter.displayTable(productList);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
