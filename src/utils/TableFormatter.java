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
        table.setColumnWidth(1, 20, 50);
        table.setColumnWidth(2, 20, 50);
        table.setColumnWidth(3, 20, 50);
        table.setColumnWidth(4, 20, 50);
        headerDisplayTable(cellCenter, table);
        int totalRecords = products.size();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        currentPage = Math.max(1, Math.min(currentPage, totalPages));
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalRecords);
        for (int i = startIndex; i < endIndex; i++) {
            Product e = products.get(i);
            table.addCell(e.getCode(),cellCenter);
            table.addCell(e.getName(),cellCenter);
            table.addCell(e.getPrice()+"",cellCenter);
            table.addCell(e.getQuantity()+"",cellCenter);
            table.addCell(e.getImported(),cellCenter);
        }
        System.out.println(table.render());
    }

    public static void headerDisplayTable(CellStyle cellCenter, Table table) {
        table.addCell(STR."\{Helper.textAccentBlue}CODE\{Helper.resetColor}");
        table.addCell(STR."\{Helper.textAccentBlue}NAME\{Helper.resetColor}", cellCenter);
        table.addCell(STR."\{Helper.textAccentBlue}UNIT_PRICE\{Helper.resetColor}", cellCenter);
        table.addCell(STR."\{Helper.textAccentBlue}QTY\{Helper.resetColor}", cellCenter);
        table.addCell(STR."\{Helper.textAccentBlue}IMPORTED_AT\{Helper.resetColor}", cellCenter);
    }

    public static void displayTable(List<Product> products) {
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        Table table = new Table(5, BorderStyle.UNICODE_DOUBLE_BOX_WIDE);
        table.setColumnWidth(0,20,30);
        table.setColumnWidth(1,20,30);
        table.setColumnWidth(2,20,30);
        table.setColumnWidth(3,20,30);
        table.setColumnWidth(4,20,30);
        headerDisplayTable(cellCenter, table);

        for(Product e : products) {
            table.addCell(e.getCode(),cellCenter);
            table.addCell(e.getName(),cellCenter);
            table.addCell(e.getPrice()+"",cellCenter);
            table.addCell(e.getQuantity()+"",cellCenter);
            table.addCell(e.getImported(),cellCenter);

        }
        System.out.println(table.render());
    }
    public static void showOneProduct(Product e) {
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
            table.addCell(STR."\{Helper.textAccentBlue}CODE\{" ".repeat(18)}: \{e.getCode()}");
            table.addCell(STR."\{Helper.textAccentBlue}Name\{" ".repeat(18)}: \{e.getName()}");
            table.addCell(STR."\{Helper.textAccentBlue}Unit Price\{" ".repeat(12)}: \{e.getPrice()}");
            table.addCell(STR."\{Helper.textAccentBlue}Qty\{" ".repeat(19)}: \{e.getQuantity()}");
            table.addCell(STR."\{Helper.textAccentBlue}Imported Date\{" ".repeat(9)}: \{e.getImported()}");
        System.out.println(table.render());
    }

    public static Table getTable(int currentPage, int totalPages, int totalRecord) {
        CellStyle cellRight = new CellStyle(CellStyle.HorizontalAlign.right);
        CellStyle cellLeft = new CellStyle(CellStyle.HorizontalAlign.left);
        Table table = new Table(2, BorderStyle.DESIGN_PAPYRUS);
        table.setColumnWidth(0,55,55);
        table.setColumnWidth(1,55,55);
        table.addCell(STR."\{Helper.textGreen}Page : \{Helper.resetColor}\{currentPage} of \{totalPages}",cellLeft);
        table.addCell(STR."\{Helper.textGreen}Total Record : \{Helper.resetColor}\{totalRecord}",cellRight);
        table.addCell(STR."\{Helper.textGreen}Page Navigation\{Helper.resetColor}",cellLeft);
        table.addCell(STR."\{Helper.textGreen}(F)irst  (P)revious  (G)oto  (N)ext  (L)ast\{Helper.resetColor}",cellRight);
        return table;
    }
}
