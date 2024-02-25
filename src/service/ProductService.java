package service;

import modal.Product;

import java.util.List;

public interface ProductService {
    void writeObject(List<Product> products);
    void readSingleObject(List<Product> products);
    void showAllProduct(List<Product> products);
    void editProduct(List<Product> products);
    void searchProductByName(List<Product> products);
    void deleteProductByName(List<Product> products);
    void commitToDataSource();
    void backUpFile();
    void restoreFile();
    void addRandomRecord();
    void clearDataInFile();
    List<Product> loadDataFromFile();

}
