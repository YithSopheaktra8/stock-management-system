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

    public void searchProduct(){
        productService.searchProductByName();
    }

    public void deleteProduct(){
        productService.deleteProductByName();
    }
    public void commitToDataSource(){
        productService.commitToDataSource();
    }
    public void backUpFile(){
        productService.backUpFile();
    }

    public void restoreFile(){
        productService.restoreFile();
    }

    public void addRandom(){
        productService.addRandomRecord();
    }
    public List<Product> loadFromFile(){
        return  productService.loadDataFromFile();
    }
    public void clearData(){
        productService.clearDataInFile();
    }

}
