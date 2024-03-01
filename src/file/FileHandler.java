package file;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Write the list of products to a JSON file
            objectMapper.writeValue(new File(source), products);

            isCommitted = true;
            CheckCommit.dataCommitted = true;
            CheckCommit.saveCommitStatus();
        } catch (IOException e) {
            System.out.println("Error writing to JSON file: " + e.getMessage());
        }
    }

    public List<Product> readListFile(String source) {
        List<Product> productLists = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON file into a List<Product>
            productLists = objectMapper.readValue(new File(source), new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }

        return productLists;
    }

}
