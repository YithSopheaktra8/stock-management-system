import controller.ProductController;
import file.FileHandler;
import modal.Product;
import utils.Commit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Commit.checkUncommittedChanges();

        ProductController productController = new ProductController();
        List<Product> products = new ArrayList<>(){{
            add(new Product("COKE",2.5,30));
            add(new Product("FANTA",2.0,20));
            add(new Product("FANTA12345",2.0,20));
        }};

//        productController.writeDataToFile(products);
        productController.readDataFromFile();
//        productController.commitToDataSource();
////        productController.searchProduct();
//        productController.editAllProduct();
//        productController.readDataFromFile();
        Commit.closeProgram();
    }
}
