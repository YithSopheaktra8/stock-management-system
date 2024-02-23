package utils;

import modal.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TableFormatter {
    public static void displayTable() {
        Product product = new Product();
        Product product1 = new Product();
        product.setCode("168");
        product.setName("Iphone15");
        product.setPrice(2500.0);
        product.setQuantity(10);
        product.setImported(LocalDate.now());
        product1.setCode("169");
        product1.setName("Iphone10");
        product1.setPrice(3500.0);
        product1.setQuantity(100);
        product1.setImported(LocalDate.now());
        List<Product> products = new ArrayList<>(
                List.of(product, product1)
        );
        Table table = new Table(5, BorderStyle.CLASSIC_WIDE);
        table.addCell("CODE");
        table.addCell("NAME");
        table.addCell("UNIT_PRICE");
        table.addCell("QTY");
        table.addCell("IMPORTED_AT");
        for(Product e : products) {
            table.addCell(e.getCode());
            table.addCell(e.getName());
            table.addCell(e.getPrice().toString());
            table.addCell(e.getQuantity().toString());
            table.addCell(e.getImported().toString());
        }
        System.out.println(table.render());
    }
}
