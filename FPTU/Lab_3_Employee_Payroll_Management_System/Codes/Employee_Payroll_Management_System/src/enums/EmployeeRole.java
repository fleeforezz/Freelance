package enums;

public enum EmployeeRole {
    Developer,
    Tester,
    Manager,
    HR;

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
}
