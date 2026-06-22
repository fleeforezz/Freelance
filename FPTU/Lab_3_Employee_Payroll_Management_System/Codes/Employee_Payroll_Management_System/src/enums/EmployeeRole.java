package enums;

public enum EmployeeRole {
    Developer,
    Tester,
    Manager,
    HR;

    // Parse from String to Enum
    public static EmployeeRole parseRole(String role) {
        switch (role.trim().toLowerCase()) {
            case "developer":
                return EmployeeRole.Developer;
            case "tester":
                return EmployeeRole.Tester;
            case "manager":
                return EmployeeRole.Manager;
            case "hr":
                return EmployeeRole.HR;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

    // Parse from Enum to String
    public static String parseRole(EmployeeRole role) {
        switch (role) {
            case Developer:
                return "Developer";
            case Tester:
                return "Tester";
            case Manager:
                return "Manager";
            case HR:
                return "HR";
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }
}
