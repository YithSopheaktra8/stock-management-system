package view;

import controller.ProductController;
import modal.Product;
import utils.Commit;
import utils.RenderMenu;
import utils.ValidateInput;

import java.util.List;
import java.util.Scanner;

public class View {
    public static final ProductController productController = new ProductController();

    public static void displayToConsole(){

        Commit.checkUncommittedChanges();
        List<Product> products = productController.loadFromFile();
        Scanner scanner = new Scanner(System.in);
        ProductController productController = new ProductController();
        char ch;
        do {
            RenderMenu.mainMenu();
            ch = ValidateInput.validateInputChar("> Choose option : ",
                    STR."\{"+".repeat(60)}\n# Option must be alphabet\n\{"+".repeat(60)}",
                    "[a-zA-Z]+",
                    scanner);
            switch (ch){
                case 'l' -> productController.readDataFromFile(products);
                case 'm' -> productController.addRandom();
                case 'w' -> productController.writeDataToFile(products);
                case 'r' -> productController.readSingleProduct(products);
                case 'd' -> productController.deleteProduct(products);
                case 'e' -> productController.editAllProduct(products);
                case 's' -> productController.searchProduct(products);
                case 'c' -> productController.commitToDataSource();
                case 'k' -> productController.backUpFile();
                case 't' -> productController.restoreFile();
                case 'h' -> RenderMenu.helpMenu();
                case 'x' -> {
                    System.out.println("Good bye See you again!!");
                    Commit.closeProgram();
                    System.exit(0);
                }
                default -> System.out.println(STR."\{"+".repeat(60)}\n# Please input option from A-F\n\{"+".repeat(60)}");
            }
            Commit.closeProgram();
        }while (true);
    }

}

