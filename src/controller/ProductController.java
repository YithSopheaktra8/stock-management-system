package controller;

import modal.Product;
import service.impl.ProductServiceImpl;

import java.util.List;

public class ProductController {
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    public void readDataFromFile(){
        productService.showAllProduct();
    }

    public void writeDataToFile(List<Product> productList){
        productService.writeObject(productList);
    }

    public void editAllProduct(){
        productService.editProduct();
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
    public void clearData(){
        productService.clearDataInFile();
    }

}
