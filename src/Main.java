import controller.ProductController;
import modal.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ProductController productController = new ProductController();
        List<Product> products = new ArrayList<>(){{
            add(new Product("1001","COKE",2.5,30, LocalDate.now()));
            add(new Product("1002","FANTA",2.0,20, LocalDate.now()));
        }};

        productController.writeDataToFile(products);
        productController.readDataFromFile();
        productController.editAllProduct();
        productController.readDataFromFile();


    }
}
