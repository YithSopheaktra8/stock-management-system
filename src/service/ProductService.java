package service;

import modal.Product;

import java.util.List;

public interface ProductService {
    void writeObjectToFile(Product product);
    void readObjectFromFile();

    void writeObjectToFile(List<Product> products);
}
