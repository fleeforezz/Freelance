/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author jso
 */
public class Inputter {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Inputter() {
    }

    public static String getString(String message) {
        while (true) {
            System.out.print(message);
            String value = SCANNER.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Value cannot be empty.");
        }
    }

    public static String getOptionalString(
            String message, String currentValue) {
        System.out.print(message + " [" + currentValue + "]: ");
        String value = SCANNER.nextLine().trim();

        return value.isEmpty() ? currentValue : value;
    }

    public static int getInt(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int value = Integer.parseInt(SCANNER.nextLine().trim());

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println("Enter a value from "
                        + min + " to " + max + ".");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public static double getDouble(String message, double min) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(
                        SCANNER.nextLine().trim());

                if (Double.isFinite(value) && value >= min) {
                    return value;
                }

                System.out.println("Value must be at least " + min + ".");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static LocalDate getDate(String message) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                System.out.print(message + " (dd/MM/yyyy): ");
                return LocalDate.parse(
                        SCANNER.nextLine().trim(), formatter);

            } catch (DateTimeParseException e) {
                System.out.println(
                        "Invalid date. Example: 01/10/2026");
            }
        }
    }

    public static boolean confirm(String message) {
        System.out.print(message + " (y/n): ");
        return SCANNER.nextLine().trim().equalsIgnoreCase("y");
    }
}
