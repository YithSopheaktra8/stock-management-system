package file;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import modal.Product;
import org.objenesis.strategy.StdInstantiatorStrategy;
import utils.CheckCommit;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileHandler {
    public static final String DATA_SOURCE = "data.txt";
    public static final String TRANSACTION_SOURCE = "transaction.txt";
    public static final String BACK_UP_SOURCE = "backup/";
    public static Boolean isCommitted = false;
    public void writeListToFile(List<Product> products, String source) {
        try (Output output = new Output(new BufferedOutputStream(new FileOutputStream(source)))) {
            Kryo kryo = new Kryo();
            kryo.setReferences(false); // Disable reference tracking for better performance
            kryo.register(ArrayList.class); // Register ArrayList class
            kryo.register(Product.class); // Register the class to be serialized
            // Use StdInstantiatorStrategy for better instantiation performance
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            // Serialize each Product object individually
            for (Product product : products) {
                kryo.writeObject(output, product);
            }
            isCommitted = true;
            CheckCommit.dataCommitted = true;
            CheckCommit.saveCommitStatus();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Product> readListFile(String source) {
        List<Product> productLists = new ArrayList<>();
        try (Input input = new Input(new BufferedInputStream(new FileInputStream(source),8192))) {
            Kryo kryo = new Kryo();
            kryo.setReferences(false); // Disable reference tracking for better performance
            kryo.register(ArrayList.class); // Register ArrayList class
            kryo.register(Product.class); // Register the class to be deserialized
            // Use StdInstantiatorStrategy for better instantiation performance
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());

            while (input.available() > 0) {
                Product product = kryo.readObject(input, Product.class);
                product.setCode(product.getCode().trim());
                product.setImported(product.getImported().trim());
                productLists.add(product);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return productLists;
    }

}
