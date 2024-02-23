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
//            Product product = Product.builder()
//                    .code("001")
//                    .name("Coke")
//                    .price(2.5)
//                    .quantity(50)
//                    .imported(LocalDate.now())
//                    .build();
//            Product product1 = Product.builder()
//                    .code("002")
//                    .name("Coke")
//                    .price(2.5)
//                    .quantity(50)
//                    .imported(LocalDate.now())
//                    .build();
            List<Product> products = new ArrayList<>();
//            products.add(product);
//            products.add(product1);
            products.add(product3);
            List<List<Product>> allProduct = new ArrayList<>();
            allProduct.add(products);
            objectOutputStream.writeObject(allProduct);
            objectOutputStream.flush();
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

    @Override
    public void showAllProduct() {
        ProductServiceImpl productService = new ProductServiceImpl();
        List<Product> products = productService.readListObjectFromFile();
        TableFormatter.displayTable(products);
    }

    @Override
    public Product updateAll(Product product) {
        Scanner scanner = new Scanner(System.in);
        Product product1 = helper.inputProduct(scanner);
        product.setCode(product1.getCode());
        product.setName(product1.getName());
        product.setPrice(product1.getPrice());
        product.setQuantity(product1.getQuantity());
        product.setImported(product1.getImported());
        return product;
    }

    @Override
    public void editProduct() {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = readListObjectFromFile();
        List<Product> productList = new ArrayList<>();
        System.out.println(products);
        System.out.print("Enter code to update : ");
        String code = scanner.nextLine();
        for (Product product : products){
            if (product.getCode().equals(code)){
                TableFormatter.showOneProduct(product);
                Product product1 = updateAll(product);
                System.out.println(product1);
            }
        }
    }

    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.writeObjectToFile();
        productService.editProduct();
    }
}
