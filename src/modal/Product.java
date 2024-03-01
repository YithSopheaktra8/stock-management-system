package modal;

import lombok.*;
import utils.Helper;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private String code = Helper.generateProductCode();
    private String name;
    private double price;
    private int quantity;
    private String imported = LocalDate.now().toString();

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(String code, String name, double price, int quantity, String imported){
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imported = imported;
    }
}
