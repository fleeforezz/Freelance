package enums;

public enum EmployeeStatus {
    Active,
    InActive;

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
}
