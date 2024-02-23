package service;

import modal.Product;

import java.util.List;

public interface ProductService {

    void writeObject(List<Product> productList);
    List<Product> readObject();

    void showAllProduct();


    void editProduct();
}
