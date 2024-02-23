package service.impl;

import modal.Product;
import service.ProductService;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.StringTemplate.STR;

public class ProductServiceImpl implements ProductService {
    @Override
    public void writeObjectToFile(Product product) {
        try{
            FileWriter fileWriter = new FileWriter("data.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String productInfo = STR."\{product.getCode()},\{product.getName()},\{product.getPrice()},\{product.getQuantity()},\{product.getImported()}";
            bufferedWriter.write(productInfo);
            bufferedWriter.newLine();
            // Close the resources
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void readObjectFromFile() {
        try{
            FileReader fileReader = new FileReader("data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String[]> lists = new ArrayList<>();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                String[] splitWhiteSpace = data.split("\\s+");
                lists.add(splitWhiteSpace);
            }

            for(String[] info : lists){
                System.out.println(Arrays.toString(info));
            }
            fileReader.close();
            bufferedReader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void writeObjectToFile(List<Product> products) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("data.txt"));){



        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
