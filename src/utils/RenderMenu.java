package utils;

import modal.Product;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Scanner;

public class RenderMenu {
    public static void updateMenu(){
        System.out.println(STR."\{Helper.textGreen}# What do you want to update?\{Helper.resetColor}");
        System.out.println(STR."\{Helper.textAccentBlue}1. All\{Helper.resetColor}");
        System.out.println(STR."\{Helper.textAccentBlue}2. NAME\{Helper.resetColor}");
        System.out.println(STR."\{Helper.textAccentBlue}3. UNIT PRICE\{Helper.resetColor}");
        System.out.println(STR."\{Helper.textAccentBlue}4. QTY\{Helper.resetColor}");
        System.out.println(STR."\{Helper.textAccentBlue}5. Back to Menu\{Helper.resetColor}");
    }
    public static Product insertProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("#".repeat(40));
        System.out.println(STR."\{Helper.textGreen}# Create a new product\{Helper.resetColor}");
        String name = ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Enter Product name : \{Helper.resetColor}",STR."\{Helper.textOrange}! Product name can not be special character !\{Helper.resetColor}","^[a-zA-Z0-9 ]+$",scanner);
        double price = Double.parseDouble(ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Enter Product price : \{Helper.resetColor}",STR."\{Helper.textOrange}! Product price must be number !\{Helper.resetColor}","[+]?\\d*\\.?\\d+$",scanner));
        int quantity = Integer.parseInt(ValidateInput.validateInputString(STR."\{Helper.textAccentBlue}> Enter Product quantity : \{Helper.resetColor}",STR."\{Helper.textOrange}! Product quantity must be number !\{Helper.resetColor}","[0-9]+",scanner));
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
        System.out.println(STR."\{Helper.textAccentBlue}#\{Helper.resetColor}".repeat(125));
        String menu = "Disp(l)ay | Rando(m) | (W)rite | (R)ead | (E)dit | (D)elete | (S)earch ";
        String menu1 = "| Set r(o)w | (C)ommit | Bac(k) up | Res(t)ore | (H)elp  | Cle(a)r | E(x)it";
        Table table = new Table(1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.SURROUND);
        CellStyle cellCenter = new CellStyle(CellStyle.HorizontalAlign.center);
        table.setColumnWidth(0,120,120);
        table.addCell(menu,cellCenter);
        table.addCell(menu1,cellCenter);
        System.out.println(table.render());
        System.out.println(STR."\{Helper.textAccentBlue}#\{Helper.resetColor}".repeat(125));


    }
}
