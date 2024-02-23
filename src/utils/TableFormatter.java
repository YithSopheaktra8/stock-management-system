package utils;

import modal.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
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
}
