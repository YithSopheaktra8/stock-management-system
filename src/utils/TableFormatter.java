package utils;

import modal.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TableFormatter {
    public static void displayTable(List<Product> products) {
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
    public static void showOneProduct() {
        Product product = new Product();
        product.setCode("168");
        product.setName("Iphone15");
        product.setPrice(2500.0);
        product.setQuantity(10);
        product.setImported(LocalDate.now());
        List<Product> products = new ArrayList<>(
                List.of(product)
        );
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        for(Product e : products) {
            table.addCell("CODE" + " ".repeat(18) + ": " + e.getCode());
            table.addCell("Name" + " ".repeat(18) + ": " + e.getName());
            table.addCell("Unit Price" + " ".repeat(12) + ": " + e.getPrice());
            table.addCell("Qty" + " ".repeat(19) + ": " + e.getQuantity());
            table.addCell("Imported Date" + " ".repeat(9) + ": " + e.getImported());
        }
        System.out.println(table.render());
    }
}
