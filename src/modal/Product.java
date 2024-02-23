package modal;

import lombok.*;
import utils.Helper;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;


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
    private Double price;
    private Integer quantity;
    private LocalDate imported = LocalDate.now();

    public Product(String name, Double price, Integer quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(String code, String name, Double price, Integer quantity, LocalDate imported){
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imported = imported;
    }
}
