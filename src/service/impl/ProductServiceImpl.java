package service.impl;

import file.FileHandler;
import modal.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.Table;
import service.ProductService;
import utils.*;


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
    public static int pageSize = 3;


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
        String productCode = ValidateInput.validateInputString("Enter product code (CSTAD- followed by numbers) : ","! Must be follow by instruction!","^CSTAD-\\d+$",scanner);
        boolean isFound = false;
        for (Product product : productList){
            if(product.getCode().toLowerCase().contains(productCode.toLowerCase())){
                TableFormatter.showOneProduct(product);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("! Product Not Found !");
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
            Table table = getTable(currentPage, totalPages, totalRecord);
            System.out.println(table.render());
            System.out.print("> (B)ack or Navigate page : ");
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
                    int toPage = ValidateInput.validateInputNumber("> Enter page number : ","! Page must be Number !",scanner);
                    currentPage = Helper.goToSpecificPage(toPage, totalPages);
                }
            }
        }while (!choice.equalsIgnoreCase("b"));
    }

    private static Table getTable(int currentPage, int totalPages, int totalRecord) {
        CellStyle cellRight = new CellStyle(CellStyle.HorizontalAlign.right);
        CellStyle cellLeft = new CellStyle(CellStyle.HorizontalAlign.left);
        Table table = new Table(2, BorderStyle.DESIGN_PAPYRUS);
        table.setColumnWidth(0,55,55);
        table.setColumnWidth(1,55,55);
        table.addCell(STR."Page : \{currentPage} of \{totalPages}",cellLeft);
        table.addCell(STR."Total Record : \{totalRecord}",cellRight);
        table.addCell("Page Navigation",cellLeft);
        table.addCell("(F)irst  (P)revious  (G)oto  (N)ext  (L)ast",cellRight);
        return table;
    }

    @Override
    public void setRow() {
        Scanner scanner = new Scanner(System.in);
        ProductServiceImpl.setRow = ValidateInput.validateInputNumber("> Set row to display in table : ","! Row must be number !",scanner);
        pageSize = ProductServiceImpl.setRow;
    }
    @Override
    public void editProduct(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code to update : ");
        String code = scanner.nextLine();
        for (Product product : products){
            if (product.getCode().equals(code)){
                TableFormatter.showOneProduct(product);
                RenderMenu.updateMenu();
                String userInput = ValidateInput.validateInputString("> Option [1-5] : ","! Please Input from 1-5","[1-5]+",scanner);
                switch (userInput) {
                    case "1" -> {
                        System.out.print("Enter NAME : ");
                        String name = scanner.nextLine();
                        System.out.print("Enter PRICE : ");
                        double price = Double.parseDouble(scanner.nextLine());
                        Integer quantity = ValidateInput.validateInputNumber("Enter product Quantity : ","! Product quantity cannot be decimal number",scanner);
                        Product product1 = new Product(product.getCode(),name,price,quantity,product.getImported());
                        System.out.println("#".repeat(40));
                        System.out.println(STR."# New Product detail of \{product.getCode()}");
                        TableFormatter.showOneProduct(product1);
                        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                        if (isSure.equalsIgnoreCase("y")){
                            product.setCode(product1.getCode());
                            product.setName(product1.getName());
                            product.setPrice(product1.getPrice());
                            product.setQuantity(product1.getQuantity());
                            product.setImported(product1.getImported());
                        }
                    }
                    case "2" -> {
                        String newName = ValidateInput.validateInputString("> Please Enter new Product name : ", "! Please Input Alphabet and Number only", "[0-9a-zA-Z\\s]+", scanner);
                        Product product1 = new Product(product.getCode(),newName, product.getPrice(), product.getQuantity(), product.getImported());
                        System.out.println("#".repeat(40));
                        System.out.println(STR."# New Product detail of \{product.getCode()}");
                        TableFormatter.showOneProduct(product1);
                        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                        if (isSure.equalsIgnoreCase("y"))
                            product.setName(product1.getName());

                    }
                    case "3" -> {
                        double newPrice = Double.parseDouble(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
                        Product product1 = new Product(product.getCode(),product.getName(), newPrice, product.getQuantity(), product.getImported());
                        System.out.println("#".repeat(40));
                        System.out.println(STR."# New Product detail of \{product.getCode()}");
                        TableFormatter.showOneProduct(product1);
                        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                        if (isSure.equalsIgnoreCase("y"))
                            product.setPrice(product1.getPrice());
                    }
                    case "4" -> {
                        int newQty = Integer.parseInt(ValidateInput.validateInputString("> Please Enter new Product price : ", "! Please Input Decimal only", "[0-9]+", scanner));
                        Product product1 = new Product(product.getCode(),product.getName(), product.getPrice(), newQty, product.getImported());
                        System.out.println("#".repeat(40));
                        System.out.println(STR."# New Product detail of \{product.getCode()}");
                        TableFormatter.showOneProduct(product1);
                        String isSure = ValidateInput.validateInputString("Are you sure to update? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
                        if (isSure.equalsIgnoreCase("y"))
                            product.setQuantity(product1.getQuantity());
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
        System.out.println("# Search product by name");
        String productName = ValidateInput.validateInputString("Enter product name : ","! Must be Alphabet and Number only!","[0-9a-zA-Z\\s]+",scanner);
        List<Product> searchList = new ArrayList<>();
        boolean isFound = false;
        for (Product product : productList){
            if(product.getName().toLowerCase().contains(productName.toLowerCase())){
                searchList.add(product);
                isFound = true;
            }
        }
        if(!isFound){
            System.out.println("! Product Not Found !");
        }else {
            TableFormatter.displayTable(searchList);
        }

    }

    @Override
    public void deleteProductByCode(List<Product> productList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#".repeat(40));
        System.out.println("# Delete a product");
        String productCode = ValidateInput.validateInputString("Enter product code (CSTAD- followed by numbers) : ","! Must be follow by instruction!","^CSTAD-\\d+$",scanner);
        String isSure;
        boolean isFound = false;
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equalsIgnoreCase(productCode)) {
                System.out.println("#".repeat(40));
                System.out.println(STR."# Product detail of \{product.getCode()}");
                TableFormatter.showOneProduct(product);
                isSure = ValidateInput.validateInputString("Are you sure to delete? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);

                if (isSure.equalsIgnoreCase("y")) {
                    iterator.remove(); // Remove the product using the iterator
                    fileHandler.writeListToFile(productList,FileHandler.TRANSACTION_SOURCE);
                    System.out.println("! Product has been deleted successfully !");
                }
                isFound = true;
                break; // Stop iterating once the product is found
            }
        }

        if (!isFound) {
            System.out.println("! Product not found.");
        }
    }

    @Override
    public void commitToDataSource() {
        Scanner scanner = new Scanner(System.in);
        if(FileHandler.isCommitted){
            System.out.println("#".repeat(40));
            System.out.println("You have uncommitted transaction.");
            String isSure = ValidateInput.validateInputString("> Do you want to save or lose data? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
            if(isSure.equalsIgnoreCase("y")){
                List<Product> productList = fileHandler.readListFile(FileHandler.TRANSACTION_SOURCE);
                fileHandler.writeListToFile(productList,FileHandler.DATA_SOURCE);
                FileHandler.isCommitted = false;
            }
        }else {
            System.out.println("> No commit change");
        }
    }

    @Override
    public void backUpFile() {
        try{
            Files.createDirectories(Paths.get(FileHandler.BACK_UP_SOURCE));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());
            String backupFileName = STR."\{FileHandler.BACK_UP_SOURCE}backup_\{timestamp}.txt";
            Files.copy(Paths.get("data.txt"), Paths.get(backupFileName), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(STR."Backup completed. backup filename : \{backupFileName}");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public  void restoreFile(List<Product> productList) {
        AtomicInteger i = new AtomicInteger(1);
        try{
            Path backupFilePath = Paths.get("backup");
            try(Stream<Path> fileStream = Files.list(backupFilePath)){
                System.out.println("> List of file : ");
                System.out.println("NOTE!! (The latest backup file is on top)");
                fileStream.forEach((path) -> System.out.println(STR."\{i.getAndIncrement()}. \{path.getFileName()}"));
            }
            // Ask the user to input a number of the file
            Scanner scanner = new Scanner(System.in);
            int fileNumber = ValidateInput.validateInputNumber("> Enter the number of the Backup file to restore: ","> Input must be number !",scanner);

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
                    System.out.println(STR."Restore completed from \{selectedFilePath.getFileName()} to \{FileHandler.TRANSACTION_SOURCE}");
                } else {
                    System.out.println("Invalid file number.");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addRandomRecord(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        int random = Integer.parseInt(ValidateInput.validateInputString("> Enter random amount : ","! Input must be number ","[0-9]+",scanner));
        String isSure = ValidateInput.validateInputString(STR."Are you sure to add \{random} product to DATA_SOURCE? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
        if(isSure.equalsIgnoreCase("y")){
            for(int i=0; i<random; i++){
                products.add(new Product("ABC",100.0,10));
            }
        }
        Long start = System.nanoTime();
        fileHandler.writeListToFile(products,FileHandler.DATA_SOURCE);
        Long end = System.nanoTime();
        long elapsedTime = (end - start);
        long milliTime = elapsedTime / 1_000_000;
        double seconds = (double) elapsedTime / 1_000_000_000;
        double convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        System.out.println(STR."# Write \{random} products spend : \{convert} s");
    }
    @Override
    public void clearDataInFile(List<Product> products) {
        Scanner scanner = new Scanner(System.in);
        List<Product> emptyList = new ArrayList<>();
        String validate = ValidateInput.validateInputString("> Do you want to reset all data ? [Y/n] : ","! Please input y or n (y = yes),(n = no)","^[yYnN]+$",scanner);
        if (validate.equalsIgnoreCase("y")){
            fileHandler.writeListToFile(emptyList,FileHandler.TRANSACTION_SOURCE);
            fileHandler.writeListToFile(emptyList,FileHandler.DATA_SOURCE);
            products.clear();
        }else {
            System.out.println("> Reset data unsuccessful");
        }
    }

    @Override
    public List<Product> loadDataFromFile() {
        System.out.println("Loading from file...");
        Long start = System.nanoTime();
        List<Product> productList = fileHandler.readListFile(FileHandler.DATA_SOURCE);
        Long end = System.nanoTime();
        long elapsedTime = (end - start);
        long milliTime = elapsedTime / 1_000_000;
        double seconds = (double) elapsedTime / 1_000_000_000;
        double convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        System.out.println(STR."# Load \{productList.size()} products spend : \{convert} s");
        return productList;
    }


}
