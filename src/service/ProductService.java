package service;

import modal.Product;

import java.util.List;

public interface ProductService {
    void writeObject(List<Product> productList);
    void showAllProduct();
    void editProduct();
    void searchProductByName();
    void deleteProductByName();
    void commitToDataSource();
    void backUpFile();
    void restoreFile();
    void addRandomRecord();

    void clearDataInFile();

}
