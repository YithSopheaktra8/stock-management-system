package utils;

import modal.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;


import java.util.List;

public class TableFormatter {
    public static void displayPagination(List<Product> products,int currentPage, int pageSize) {
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(5, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
        table.setColumnWidth(0, 20, 20);
        table.setColumnWidth(1, 20, 20);
        table.setColumnWidth(2, 20, 20);
        table.setColumnWidth(3, 20, 20);
        table.setColumnWidth(4, 20, 20);
        table.addCell("CODE", cellCenter);
        table.addCell("NAME", cellCenter);
        table.addCell("UNIT_PRICE", cellCenter);
        table.addCell("QTY", cellCenter);
        table.addCell("IMPORTED_AT", cellCenter);
        int totalRecords = products.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        currentPage = Math.max(1, Math.min(currentPage, totalPages));
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalRecords);
        for (int i = startIndex; i < endIndex; i++) {
            Product e = products.get(i);
            table.addCell(e.getCode(), cellCenter);
            table.addCell(e.getName(), cellCenter);
            table.addCell(e.getPrice().toString(), cellCenter);
            table.addCell(e.getQuantity().toString(), cellCenter);
            table.addCell(e.getImported().toString(), cellCenter);
        }
        System.out.println(table.render());
    }

    public static void displayTable(List<Product> products) {
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(5, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
        table.setColumnWidth(0,20,30);
        table.setColumnWidth(1,20,30);
        table.setColumnWidth(2,20,30);
        table.setColumnWidth(3,20,30);
        table.setColumnWidth(4,20,30);
        table.addCell("CODE",cellCenter);
        table.addCell("NAME",cellCenter);
        table.addCell("UNIT_PRICE",cellCenter);
        table.addCell("QTY",cellCenter);
        table.addCell("IMPORTED_AT",cellCenter);

        for(Product e : products) {
            table.addCell(e.getCode(),cellCenter);
            table.addCell(e.getName(),cellCenter);
            table.addCell(e.getPrice().toString(),cellCenter);
            table.addCell(e.getQuantity().toString(),cellCenter);
            table.addCell(e.getImported().toString(),cellCenter);

        }
        System.out.println(table.render());
    }
    public static void showOneProduct(Product e) {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
            table.addCell(STR."CODE\{" ".repeat(18)}: \{e.getCode()}");
            table.addCell(STR."Name\{" ".repeat(18)}: \{e.getName()}");
            table.addCell(STR."Unit Price\{" ".repeat(12)}: \{e.getPrice()}");
            table.addCell(STR."Qty\{" ".repeat(19)}: \{e.getQuantity()}");
            table.addCell(STR."Imported Date\{" ".repeat(9)}: \{e.getImported()}");
        System.out.println(table.render());
    }

    public static Table getTable(int currentPage, int totalPages, int totalRecord) {
        CellStyle cellRight = new CellStyle(CellStyle.HorizontalAlign.right);
        CellStyle cellLeft = new CellStyle(CellStyle.HorizontalAlign.left);
        Table table = new Table(2, BorderStyle.DESIGN_PAPYRUS);
        table.setColumnWidth(0,55,55);
        table.setColumnWidth(1,55,55);
        table.addCell(STR."Page : \{currentPage} of \{totalPages}",cellLeft);
        table.addCell(STR."Total Record : \{totalRecord}",cellRight);
        table.addCell("Page Navigation",cellLeft);
        table.addCell("(F)irst  (P)revious  (G)oto  (N)ext  (L)ast",cellRight);
        return table;
    }
}
