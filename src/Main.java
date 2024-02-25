import controller.ProductController;
import file.FileHandler;
import modal.Product;
import utils.Commit;
import utils.RenderMenu;
import utils.ValidateInput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Commit.checkUncommittedChanges();

        ProductController productController = new ProductController();
        Scanner scanner = new Scanner(System.in);
        char ch;
        do {
            RenderMenu.mainMenu();
            ch = ValidateInput.validateInputChar("> Choose option : ",
                    STR."\{"+".repeat(60)}\n# Option must be alphabet\n\{"+".repeat(60)}",
                    "[a-zA-Z]+",
                    scanner);
            switch (ch){
                case 'l' -> productController.readDataFromFile();
                case 'm' -> productController.addRandom();
//                case 'w' -> productController.writeDataToFile();
//                case 'r' -> productController.readSingleProduct();
                case 'e' -> productController.editAllProduct();
                case 's' -> productController.searchProduct();
                case 'c' -> productController.commitToDataSource();
                case 'k' -> productController.backUpFile();
                case 't' -> productController.restoreFile();
                case 'h' -> RenderMenu.helpMenu();
                case 'x' -> {
                    System.out.println("Good bye See you again!!");
                    Commit.closeProgram();
                    System.exit(0);
                }
                default -> System.out.println("+".repeat(60)+"\n# Please input option from A-F\n"+"+".repeat(60));
            }
            Commit.closeProgram();
        }while (true);

    }
}
