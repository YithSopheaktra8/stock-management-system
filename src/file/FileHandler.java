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

    private static final String DATA_SOURCE = "data.txt";

    public void writeListToFile(List<Product> products){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(DATA_SOURCE))){
            List<List<Product>> allProduct = new ArrayList<>();
            allProduct.add(products);
            objectOutputStream.writeObject(allProduct);
            objectOutputStream.flush();
            System.out.println("Write success");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Product> readListFile() {
        List<Product> productLists = new ArrayList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(DATA_SOURCE))) {
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
