package controller;

import modal.Product;
import service.impl.ProductServiceImpl;

public class ProductController {
    private static final ProductServiceImpl productService = new ProductServiceImpl();

    public static void savedToFile(Product product){
        productService.writeObjectToFile(product);
    }

    public static void readFromFile(){
        productService.readObjectFromFile();
    }

}
