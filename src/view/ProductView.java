package view;

import controller.ProductController;
import file.FileHandler;
import modal.Product;
import utils.CheckCommit;
import utils.Helper;
import utils.RenderMenu;
import utils.ValidateInput;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    public static final ProductController productController = new ProductController();
    public static final List<Product> products = productController.loadFromFile();

    public static void displayToConsole(){
        CheckCommit.loadCommitStatus();
        if(CheckCommit.dataCommitted){
            productController.commitToDataSource();
            CheckCommit.dataCommitted = false;
            CheckCommit.saveCommitStatus();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Code to execute before program exits
            if (FileHandler.isCommitted) {
                productController.commitToDataSource();
                CheckCommit.dataCommitted = false;
                CheckCommit.saveCommitStatus();
            }
        }));

        Scanner scanner = new Scanner(System.in);
        char ch;
        do {
            RenderMenu.mainMenu();
            ch = ValidateInput.validateInputChar(STR."\{Helper.textAccentBlue}> Choose option : \{Helper.resetColor}",
                    STR."\{"+".repeat(60)}\n\{Helper.textOrange}# Option must be alphabet \{Helper.resetColor}\n\{"+".repeat(60)}",
                    "[a-zA-Z]+",
                    scanner);
            switch (ch){
                case 'l' -> productController.readDataFromFile(products);
                case 'm' -> productController.addRandom(products);
                case 'w' -> productController.writeDataToFile(products);
                case 'r' -> productController.readSingleProduct(products);
                case 'd' -> productController.deleteProduct(products);
                case 'e' -> productController.editAllProduct(products);
                case 's' -> productController.searchProduct(products);
                case 'c' -> productController.commitToDataSource();
                case 'k' -> productController.backUpFile();
                case 't' -> productController.restoreFile(products);
                case 'h' -> RenderMenu.helpMenu();
                case 'a' -> productController.clearData(products);
                case 'o' -> productController.setRow();
                case 'x' -> {
                    System.out.println("Good bye See you again!!");
                    System.exit(0);
                }
                default -> System.out.println(STR."\{"+".repeat(60)}\n\{Helper.textOrange}# Please input option from A-F\{Helper.resetColor}\n\{"+".repeat(60)}");
            }
        }while (true);
    }

}

