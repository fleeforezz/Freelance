package enums;

public enum EmployeeStatus {
    Active,
    InActive;

    // Parse from String to Enum
    public static EmployeeStatus parseStatus(String status) {
        switch (status.trim().toLowerCase()) {
            case "active":
                return EmployeeStatus.Active;
            case "inactive":
                return EmployeeStatus.InActive;
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
    }
    
    // Parse from Enum to String
    public static String parseStatus(EmployeeStatus status) {
        switch (status) {
            case Active:
                return "Active";
            case InActive:
                return "InActive";
            default:
                throw new IllegalArgumentException("Invalid status: " + status);
        }
    }
}
