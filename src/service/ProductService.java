package service;

import modal.Product;

import java.util.List;

public interface ProductService {

    void writeObjectToFile();
    List<Product> readListObjectFromFile();
}
