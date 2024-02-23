import controller.ProductController;
import modal.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductController productController = new ProductController();
        List<Product> products = new ArrayList<>(){{
            add(new Product("COKE",2.5,30));
            add(new Product("FANTA",2.0,20));
        }};

//        productController.writeDataToFile(products);
        productController.readDataFromFile();
        productController.editAllProduct();
        productController.readDataFromFile();


    }
}
