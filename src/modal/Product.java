package modal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import utils.Helper;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;


@NoArgsConstructor
@Setter
@Getter
@ToString
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

    @JsonCreator
    public Product(@JsonProperty("code") String code,
                   @JsonProperty("name") String name,
                   @JsonProperty("price") double price,
                   @JsonProperty("quantity") int quantity,
                   @JsonProperty("imported") String imported){
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imported = imported;
    }
}
