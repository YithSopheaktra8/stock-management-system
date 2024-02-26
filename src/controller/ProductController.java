package controller;

import modal.Product;
import service.impl.ProductServiceImpl;

import java.util.List;

public class ProductController {
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    public void readDataFromFile(List<Product> products){
        productService.showAllProduct(products);
    }

    public void writeDataToFile(List<Product> products){
        productService.writeObject(products);
    }
    public void readSingleProduct(List<Product> products){
        productService.readSingleObject(products);
    }

    public void editAllProduct(List<Product> products){
        productService.editProduct(products);
    }

    public void searchProduct(List<Product> products){
        productService.searchProductByName(products);
    }

    public void deleteProduct(List<Product> products){
        productService.deleteProductByName(products);
    }
    public void commitToDataSource(){
        productService.commitToDataSource();
    }
    public void backUpFile(){
        productService.backUpFile();
    }

    public void restoreFile(List<Product> products){
        productService.restoreFile(products);
    }

    public void addRandom(List<Product> products){
        productService.addRandomRecord(products);
    }
    public List<Product> loadFromFile(){
        return  productService.loadDataFromFile();
    }
    public void clearData(String filePath){
        productService.clearDataInFile(filePath);
    }

}
