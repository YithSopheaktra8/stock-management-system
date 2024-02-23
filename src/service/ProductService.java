package service;

import modal.Product;

import java.util.List;

public interface ProductService {

    void writeObjectToFile();
    List<Product> readListObjectFromFile();

    void showAllProduct();

    Product updateAll(Product product);

    void editProduct();
}
