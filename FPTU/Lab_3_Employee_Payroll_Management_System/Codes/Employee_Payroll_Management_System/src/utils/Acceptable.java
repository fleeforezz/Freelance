package utils;

public class Acceptable {
    
    public static final String EMPLOYEE_VALID_ID = "^E\\d{3}$"; // Exxx (e.g., E001)

    public static boolean isValid(String data, String pattern) {
        return data.matches(pattern);
    }
}
