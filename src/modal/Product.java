package modal;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private String code;
    private String name;
    private Double price;
    private Integer quantity;
    private LocalDate imported;
}
