package utils;

import modal.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Scanner;

public class RenderMenu {
    public static void updateMenu(){
        System.out.println("# What do you want to update?");
        System.out.println("1. All");
        System.out.println("2. NAME");
        System.out.println("3. UNIT PRICE");
        System.out.println("4. QTY");
        System.out.println("5. Back to Menu");
    }public static Product insertProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("#".repeat(40));
        System.out.println("# Create a new product");
        String name = ValidateInput.validateInputString("> Enter Product name : ","! Product name can not be special character !","^[a-zA-Z0-9]+",scanner);
        double price = Double.parseDouble(ValidateInput.validateInputString("> Enter Product price : ","! Product price must be number !","[+]?\\d*\\.?\\d+$",scanner));
        int quantity = Integer.parseInt(ValidateInput.validateInputString("> Enter Product quantity : ","! Product quantity must be number !","[0-9]+",scanner));
        return new Product(name,price,quantity);
    }
    public static void helpMenu(){
        Table tableHelp = new Table(1, BorderStyle.CLASSIC_COMPATIBLE_LIGHT_WIDE, ShownBorders.SURROUND);
        tableHelp.addCell("1.   Press   l : Display products as table                               ");
        tableHelp.addCell("2.   Press   w : Create a new product                                    ");
        tableHelp.addCell("3.   Press   r : View product detail by code                             ");
        tableHelp.addCell("4.   Press   e : Edit an existing product by code                        ");
        tableHelp.addCell("5.   Press   d : Delete an existing product by code                      ");
        tableHelp.addCell("6.   Press   s : Search an existing product by name                      ");
        tableHelp.addCell("7.   Press   c : Commit transactional date                               ");
        tableHelp.addCell("8.   Press   k : Backup data                                             ");
        tableHelp.addCell("9.   Press   t : Restore data                                            ");
        tableHelp.addCell("10.  Press   f : Navigate pagination to the last page                    ");
        tableHelp.addCell("11.  Press   p : Navigate pagination to the previous                     ");
        tableHelp.addCell("12.  Press   n : Navigate pagination to the next page                    ");
        tableHelp.addCell("13.  Press   l : Navigate pagination to the last page                    ");
        tableHelp.addCell("14.  Press   h : Help                                                    ");
        tableHelp.addCell("15.  Press   b : Step back of the application                            ");
        tableHelp.addCell("16.  Press   x : Exit the application                                    ");
        System.out.println(tableHelp.render());
    }
    public static void mainMenu(){
        String menu = "Disp(l)ay | Rando(m) | (W)rite | (R)ead | (E)dit | (D)elete | (S)earch | Set r(o)w | (C)ommit | Bac(k) up | Res(t)ore | (H)elp | E(x)it";
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        table.setColumnWidth(0,140,140);
        table.addCell(menu,cellCenter);
        System.out.println(table.render());
    }

}
