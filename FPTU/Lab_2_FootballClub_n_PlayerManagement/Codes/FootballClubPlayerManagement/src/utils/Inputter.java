package utils;

import java.util.Scanner;
import java.util.function.Predicate;

public class Inputter {

    public static final int MIN = 1;
    public static final int MAX = 200;

    /*
        ###############################
        Get String (Not empty input)
        ###############################
     */
    public static String getString(
            String inputMessage,
            String emptyMessage) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(inputMessage);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(emptyMessage);
                continue;
            }

            return input;
        }
    }
    
    /*
        ###############################
        Get String (Allow empty input)
        ###############################
     */
    public static String getString(
            String inputMessage
    ) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(inputMessage);
            String input = sc.nextLine().trim();

            return input;
        }
    }

    /*
        ###############################
        Get String (Regex validation)
        ###############################
     */
    public static String getPattern(
            String inputMessage,
            String emptyMessage,
            String regex,
            String regexMessage) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(inputMessage);
            String input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(emptyMessage);
                continue;
            }

            if (!input.matches(regex)) {
                System.out.println(regexMessage);
                continue;
            }

            return input;
        }
    }
    
    /*
        ################################################
        Get String (Regex validation, allow empty input)
        ################################################
     */
    public static String getPattern(
            String inputMessage,
            String regex,
            String regexMessage) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(inputMessage);
            String input = sc.nextLine().trim();

            if (!input.matches(regex)) {
                System.out.println(regexMessage);
                continue;
            }

            return input;
        }
    }

    /*
        ###############################
        Get String (Custom validation)
        ###############################
     */
    public static String getString(
            String inputMessage,
            boolean allowEmptyInput, String emptyMessage,
            boolean allowRegex, String regex, String regexMessage,
            boolean allowUnique, String uniqueMessage,
            Predicate<String> validator
    ) {
        Scanner sc = new Scanner(System.in, "UTF-8");

        while (true) {
            System.out.print(inputMessage);
            String input = sc.nextLine().trim();

            if (allowEmptyInput && input.isEmpty()) {
                System.out.println(emptyMessage);
                continue;
            }

            if (allowRegex && !input.matches(regex)) {
                System.out.println(regexMessage);
                continue;
            }

            if (validator != null && !validator.test(input)) {
                if (allowUnique) {
                    System.out.println(uniqueMessage);
                }
                continue;
            }

            return input;
        }
    }

    /*
        ###############################
        Get Int
        ###############################
     */
    public static int getInt(
            String welcomeMessage,
            int min, int max,
            boolean allowEmptyInput
    ) {

        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print(welcomeMessage);
            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                return -1;
            }

            try {
                int number = Integer.parseInt(input);

                if (number < min || number > max) {
                    System.out.println("Input must be between: " + min + " and " + max + ".");
                    continue;
                }

                return number;

            } catch (NumberFormatException e) {
                System.out.println("Input must be a valid integer.");
            }
        }
    }

    /*
        ###############################
        Get Double
        ###############################
     */
    public static double getDouble(String welcome, int min, int max) {
        boolean check = true;
        double number = 0;

        do {

        } while (check || number > max || number < min);

        return number;
    }

    /*
        ###############################
        Ask to continue
        ###############################
     */
    public static void askToContinue(Runnable action) {
        String decision = getString(
                "Do want to continues or go back? (Y/y | N/n): ",
                "Input cannot be empty"
        ).trim().toUpperCase();

        switch (decision) {
            case "Y":
                action.run();
                break;
            case "N":
                System.out.println("Returning to main menu");
                break;
            default:
                System.out.println("Invalid choice !!! Only (Y/y | N/n) are allowed");
        }
    }

    /*
        ###############################
        Confirm save file
        ###############################
     */
    public static boolean confirmSave(
            String recordName
    ) {
        String decision = getString(
                "Do you want to save this " + recordName + " to file ? (Y/y | N/n): ",
                "Input must not be empty"
        ).trim().toUpperCase();

        switch (decision) {
            case "Y":
                return true;
            case "N":
                break;
            default:
                System.out.println("Invalid choice !!! Only (Y/y | N/n) are allowed");
        }
        
        return false;
    }
}
