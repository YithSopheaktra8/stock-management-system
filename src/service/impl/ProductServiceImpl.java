package service.impl;

import file.FileHandler;
import modal.Product;
import org.nocrala.tools.texttablefmt.Table;
import service.ProductService;
import utils.*;
import view.ProductView;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ProductServiceImpl implements ProductService {

    private static final FileHandler fileHandler = new FileHandler();
    public static int setRow = 0;
    public static int pageSize = 4;


    @Override
    public void writeObject(List<Product> products) {
        Product product = RenderMenu.insertProduct();
        products.add(product);
        fileHandler.writeListToFile(products,FileHandler.TRANSACTION_SOURCE);
    }

    @Override
    public void readSingleObject(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#".repeat(40));
        String productCode = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Enter product code (CSTAD- followed by numbers) : \{Helper.resetColor}",STR."\{Helper.textOrange}! Must be follow by instruction!\{Helper.resetColor}","^CSTAD-\\d+$",scanner);
        boolean isFound = false;
        for (Product product : productList){
            if(product.getCode().toLowerCase().contains(productCode.toLowerCase())){
                TableFormatter.showOneProduct(product);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println(STR."\{Helper.textOrange}! Product Not Found !\{Helper.resetColor}");
        }
    }

    @Override
    public void showAllProduct(List<Product> products) {
        int currentPage = 1;
        String choice;
        int totalRecord = products.size();
        int totalPages = (int) Math.ceil((double) totalRecord / pageSize);
        do {
            currentPage = Math.max(1, Math.min(currentPage, totalPages));
            TableFormatter.displayPagination(products,currentPage,pageSize);
            Scanner scanner = new Scanner(System.in);
            Table table = TableFormatter.getTable(currentPage, totalPages, totalRecord);
            System.out.println(table.render());
            System.out.print(STR."\{Helper.textAccentBlue}> (B)ack or Navigate page : \{Helper.resetColor}");
            choice = scanner.nextLine().toLowerCase();
            switch (choice){
                case "n" -> {
                    if(currentPage == totalPages){
                        currentPage = 1;
                    }else {
                        currentPage++;
                    }
                }
                case "p" ->{
                    if (currentPage == 1) {
                        currentPage = totalPages;
                    } else {
                        currentPage = Math.max(1, currentPage - 1);
                    }
                }
                case "l" -> currentPage = totalPages;
                case "f" -> currentPage = 1;
                case "g" ->{
                    int toPage = ValidateInput.validateInputNumber(STR."\{Helper.textAccentBlue}> Enter page number : \{Helper.resetColor}",STR."\{Helper.textOrange}! Page must be Number !\{Helper.resetColor}",scanner);
                    currentPage = Helper.goToSpecificPage(toPage, totalPages);
                }
            }
        }while (!choice.equalsIgnoreCase("b"));
    }

    @Override
    public void setRow() {
        Scanner scanner = new Scanner(System.in);
        ProductServiceImpl.setRow = ValidateInput.validateInputNumber(STR."\{Helper.textAccentBlue}> Set row to display in table : \{Helper.resetColor}",STR."\{Helper.textOrange}! Row must be number !\{Helper.resetColor}",scanner);
        pageSize = ProductServiceImpl.setRow;
    }
    @Override
    public void editProduct(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        String productCode = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Enter product code (CSTAD- followed by numbers) : \{Helper.resetColor}",STR."\{Helper.textOrange}! Must be follow by instruction!\{Helper.resetColor}","^CSTAD-\\d+$",scanner);
        for (Product product : products){
            if (product.getCode().equals(productCode)){
                TableFormatter.showOneProduct(product);
                RenderMenu.updateMenu();
                String userInput = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Option [1-5] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please Input from 1-5\{Helper.resetColor}","[1-5]+",scanner);
                switch (userInput) {
                    case "1" -> Helper.editAllProductInfo(product, scanner);
                    case "2" -> Helper.editProductName(product, scanner);
                    case "3" -> Helper.editProductPrice(product, scanner);
                    case "4" -> Helper.editQuantity(product, scanner);
                    case "5" -> {
                        return;
                    }
                }
            }
        }
        fileHandler.writeListToFile(products,FileHandler.TRANSACTION_SOURCE);
    }
    @Override
    public void searchProductByName(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#".repeat(40));
        System.out.println(STR."\{Helper.textAccentBlue}# Search product by name");
        String productName = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Enter product name : \{Helper.resetColor}",STR."\{Helper.textOrange}! Must be Alphabet and Number only!\{Helper.resetColor}","[0-9a-zA-Z\\s]+",scanner);
        List<Product> searchList = new ArrayList<>();
        boolean isFound = false;
        for (Product product : productList){
            if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                searchList.add(product);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println(STR."\{Helper.textOrange}! Product Not Found !\{Helper.resetColor}");
        }else {
            TableFormatter.displayTable(searchList);
        }
    }
    @Override
    public void deleteProductByCode(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#".repeat(40));
        System.out.println(STR."\{Helper.textAccentBlue}# Delete a product\{Helper.resetColor}");
        String productCode = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Enter product code (CSTAD- followed by numbers) : \{Helper.resetColor}",STR."\{Helper.textOrange}! Must be follow by instruction!\{Helper.resetColor}","^CSTAD-\\d+$",scanner);
        String isSure;
        boolean isFound = false;
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getCode().equalsIgnoreCase(productCode)) {
                System.out.println("#".repeat(40));
                System.out.println(STR."\{Helper.textAccentBlue}# Product detail of \{Helper.resetColor}\{product.getCode()}");
                TableFormatter.showOneProduct(product);
                isSure = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Are you sure to delete? [Y/n] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please input y or n (y = yes),(n = no)\{Helper.resetColor}","^[yYnN]+$",scanner);
                if (isSure.equalsIgnoreCase("y")) {
                    iterator.remove(); // Remove the product using the iterator
                    fileHandler.writeListToFile(productList,FileHandler.TRANSACTION_SOURCE);
                    System.out.println(STR."\{Helper.textGreen}! Product has been deleted successfully !\{Helper.resetColor}");
                }
                isFound = true;
                break; // Stop iterating once the product is found
            }
        }
        if (!isFound) {
            System.out.println(STR."\{Helper.textOrange}! Product not found.\{Helper.resetColor}");
        }
    }

    @Override
    public void commitToDataSource() {
        Scanner scanner = new Scanner(System.in);
        if(FileHandler.isCommitted){
            System.out.println("#".repeat(40));
            System.out.println(STR."\{Helper.textOrange}You have uncommitted transaction.\{Helper.resetColor}");
            String isSure = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Do you want to save or lose data? [Y/n] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please input y or n (y = yes),(n = no)\{Helper.resetColor}","^[yYnN]+$",scanner);
            if(isSure.equalsIgnoreCase("y")){
                List<Product> productList = fileHandler.readListFile(FileHandler.TRANSACTION_SOURCE);
                fileHandler.writeListToFile(productList,FileHandler.DATA_SOURCE);
                FileHandler.isCommitted = false;
                CheckCommit.dataCommitted = false;
                CheckCommit.saveCommitStatus();
                System.out.println(STR."\{Helper.textGreen}> Commit completed\{Helper.resetColor}");
            }
        }else {
            System.out.println(STR."\{Helper.textGreen}> No commit change\{Helper.resetColor}");
        }
    }

    @Override
    public void backUpFile() {
        try{
            Files.createDirectories(Paths.get(FileHandler.BACK_UP_SOURCE));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String filename = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}Enter backup filename : \{Helper.resetColor}",STR."\{Helper.textOrange}! Filename cannot be empty !\{Helper.resetColor}","[a-zA-Z0-9_ ]+",new Scanner(System.in));
            String backupFileName = STR."\{FileHandler.BACK_UP_SOURCE}backup_\{filename}\{timestamp}.txt";
            Files.copy(Paths.get("data.txt"), Paths.get(backupFileName), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(STR."\{Helper.textGreen}Backup completed. backup filename : \{Helper.resetColor} \{Helper.textAccentBlue}\{backupFileName} \{Helper.resetColor}");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void restoreFile(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        AtomicInteger i = new AtomicInteger(1);
        try{
            Path backupFilePath = Paths.get("backup");
            try(Stream<Path> fileStream = Files.list(backupFilePath)){
                System.out.println(STR."\{Helper.textAccentBlue}> List of file : \{Helper.resetColor}");
                System.out.println(STR."\{Helper.textOrange}NOTE!! (The latest backup file is on top)\{Helper.resetColor}");
                fileStream.forEach((path) -> System.out.println(STR."\{i.getAndIncrement()}. \{path.getFileName()}"));
            }
            // Ask the user to input a number of the file
            int fileNumber = ValidateInput.validateInputNumber(STR."\{Helper.textAccentBlue}> Enter the number of the Backup file to restore: \{Helper.resetColor}",STR."\{Helper.textOrange}> Input must be number !\{Helper.resetColor}",scanner);

            // Restore the selected file
            try (Stream<Path> fileStream = Files.list(backupFilePath)) {
                AtomicInteger currentFileNumber = new AtomicInteger(1);
                Path selectedFilePath = fileStream
                        .filter(_ -> currentFileNumber.getAndIncrement() == fileNumber)
                        .findFirst()
                        .orElse(null);

                if (selectedFilePath != null) {
                    Files.copy(selectedFilePath, Paths.get(FileHandler.TRANSACTION_SOURCE), StandardCopyOption.REPLACE_EXISTING);
                    List<Product> products = fileHandler.readListFile(FileHandler.TRANSACTION_SOURCE);
                    productList.clear();
                    productList.addAll(products);
                    FileHandler.isCommitted = true;
                    CheckCommit.dataCommitted = true;
                    CheckCommit.saveCommitStatus();
                    System.out.println(STR."\{Helper.textGreen}Restore completed from \{Helper.resetColor}\{Helper.textAccentBlue}\{selectedFilePath.getFileName()} to \{FileHandler.TRANSACTION_SOURCE} \{Helper.resetColor}");
                } else {
                    System.out.println(STR."\{Helper.resetColor}Invalid file number.\{Helper.resetColor}");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addRandomRecord(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        int previousData = ProductView.products.size();
        int random = Integer.parseInt(ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Enter random amount : \{Helper.resetColor}",STR."\{Helper.textOrange}! Input must be number \{Helper.resetColor}","[0-9]+",scanner));
        String isSure = ValidateInput.validateInputString(STR."\{Helper.textOrange}Are you sure to add \{random+previousData} product to DATA_SOURCE? [Y/n] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please input y or n (y = yes),(n = no)\{Helper.resetColor}","^[yYnN]+$",scanner);
        List<Product> newProducts = new ArrayList<>(products);
        if(isSure.equalsIgnoreCase("y")){
            for(int i=0; i<random; i++){
                newProducts.add(new Product("PRODUCT123",100.0,10));
            }
        }
        // start animation loading
        Thread loadingThread = Helper.getThread("Writing","to");

        Long start = System.nanoTime();
        fileHandler.writeListToFile(newProducts,FileHandler.DATA_SOURCE);
        Long end = System.nanoTime();

        // Stop the loading animation
        loadingThread.interrupt();
        FileHandler.isCommitted = false;
        CheckCommit.dataCommitted = false;
        CheckCommit.saveCommitStatus();

        long elapsedTime = (end - start);
        double seconds = (double) elapsedTime / 1_000_000_000; // Convert nanoseconds to seconds
        System.out.println(STR."\{Helper.textGreen}\r# Write \{random} products spend : \{seconds} s \{Helper.resetColor}");
    }



    @Override
    public List<Product> loadDataFromFile() {
        //start animation loading
        Thread loadingThread = Helper.getThread("Loading","from");

        Long start = System.nanoTime();
        List<Product> productList = fileHandler.readListFile(FileHandler.DATA_SOURCE);
        Long end = System.nanoTime();

        // Stop the loading animation
        loadingThread.interrupt();
        long elapsedTime = (end - start);
        double seconds = (double) elapsedTime / 1_000_000_000; // Convert nanoseconds to seconds
        System.out.println(STR."\{Helper.textGreen}\n# Load \{productList.size()} products spend : \{seconds} s\{Helper.resetColor}");
        return productList;
    }

    @Override
    public void clearDataInFile(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        List<Product> emptyList = new ArrayList<>();
        String validate = ValidateInput.validateInputString(STR."\{Helper.textOrange}> Do you want to reset all data ? [Y/n] : \{Helper.resetColor}",STR."\{Helper.textOrange}! Please input y or n (y = yes),(n = no)\{Helper.resetColor}","^[yYnN]+$",scanner);
        if (validate.equalsIgnoreCase("y")){
            fileHandler.writeListToFile(emptyList,FileHandler.TRANSACTION_SOURCE);
            fileHandler.writeListToFile(emptyList,FileHandler.DATA_SOURCE);
            products.clear();
            System.out.println(STR."\{Helper.textGreen}> Reset data successfully\{Helper.resetColor}");
        }else {
            System.out.println(STR."\{Helper.textOrange}> Reset data unsuccessful\{Helper.resetColor}");
        }
    }




}
