package utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateInput {
    public static Integer validateInputNumber(String message, String error, Scanner input){
        while (true){
            System.out.print(message);
            String choice = input.nextLine();
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher matcher = pattern.matcher(choice);
            if(matcher.matches()){
                if(!(Integer.parseInt(choice) == 0))
                    return Integer.parseInt(choice);
                else
                    System.out.println("Can not be ZERO");
            }
            else
                System.out.println(error);
        }
    }
    // validate String
    public static String validateInputString(String message, String error, String patternString, Scanner input ){
        while (true){
            System.out.print(message);
            String choice = input.nextLine();
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(choice);
            if(matcher.matches()){
                return choice;
            }
            else
                System.out.println(error);
        }
    }
}