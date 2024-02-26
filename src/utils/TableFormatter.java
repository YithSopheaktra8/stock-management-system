package utils;

import modal.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.Helper.goToSpecificPage;

public class TableFormatter {
    public static void displayPagination(List<Product> products,int currentPage, int pageSize) {
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        CellStyle cellRight = new CellStyle(CellStyle.HorizontalAlign.right);
        CellStyle cellLeft = new CellStyle(CellStyle.HorizontalAlign.left);
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
            table.addCell("CODE" + " ".repeat(18) + ": " + e.getCode());
            table.addCell("Name" + " ".repeat(18) + ": " + e.getName());
            table.addCell("Unit Price" + " ".repeat(12) + ": " + e.getPrice());
            table.addCell("Qty" + " ".repeat(19) + ": " + e.getQuantity());
            table.addCell("Imported Date" + " ".repeat(9) + ": " + e.getImported());
        System.out.println(table.render());
    }
}
