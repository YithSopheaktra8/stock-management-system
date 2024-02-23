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
            add(new Product("ANCHOR",2.0,20));
            add(new Product("HANUMAN",2.0,20));
        }};


        productController.clearData();
//        productController.readDataFromFile();
        productController.addRandom();
//        productController.restoreFile();
//        productController.readDataFromFile();
        productController.clearData();
        Commit.closeProgram();
    }
}
