package file;

import modal.Product;
import utils.Helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public static final String DATA_SOURCE = "data.txt";
    public static final String TRANSACTION_SOURCE = "transaction.txt";
    public static final String BACK_UP_SOURCE = "backup/";
    public static Boolean isCommitted = false;

    public void writeListToFile(List<Product> products,String source){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(source))){
            List<List<Product>> allProduct = new ArrayList<>();
            allProduct.add(products);
            objectOutputStream.writeObject(allProduct);
            isCommitted = true;
            objectOutputStream.flush();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Product> readListFile(String source) {
        List<Product> productLists = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(source))) {
            List<List<Product>> products = (List<List<Product>>) objectInputStream.readObject();
            for (List<Product> productList : products){
                productLists.addAll(productList);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return productLists;
    }
}
