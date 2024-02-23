import controller.ProductController;
import modal.Product;

import java.time.LocalDate;

public class Main {


    public static void main(String[] args) {
        Product product = Product.builder()
                .code("111")
                .name("COCA")
                .price(2.5)
                .quantity(50)
                .imported(LocalDate.now())
                .build();

//        ProductController.savedToFile(product);
        ProductController.readFromFile();
    }
}
