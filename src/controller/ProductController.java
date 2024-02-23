package controller;

import modal.Product;
import service.impl.ProductServiceImpl;

public class ProductController {
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    public void readDataFromFile(){
        productService.readListObjectFromFile();
    }

    public void writeDataToFile(){
        productService.writeObjectToFile();
    }

}
