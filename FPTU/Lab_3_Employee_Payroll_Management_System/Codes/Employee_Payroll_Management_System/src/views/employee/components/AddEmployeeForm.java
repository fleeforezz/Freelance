package views.employee.components;

import controllers.EmployeeController;
import enums.EmployeeRole;
import enums.EmployeeStatus;
import models.Employee;
import utils.Acceptable;
import utils.Inputter;

public class AddEmployeeForm {
    
    private final EmployeeController controller;

    public AddEmployeeForm(EmployeeController controller) {
        this.controller = controller;
    }
    
    public Employee input() {
        String id = inputId();
        String name = inputName();
        EmployeeRole role = inputRole();
        int salary = inputBaseSalary();
        int workingDays = inputWorkingDays();
        int bonus = inputBonus();

        return new Employee(
                id,
                name,
                role,
                salary,
                workingDays,
                bonus,
                EmployeeStatus.Active
        );
    }
    
    public String inputId() {
        return Inputter.getString(
                "\nEnter id: ",
                true, "Id cannot be empty!!!",
                true, Acceptable.EMPLOYEE_VALID_ID, "Id must be Exxx (e.g., E001)",
                true, "Employee already exists!!!",
                employeeId -> {
                    if (controller.FindById(employeeId) != null) {
                        return false;
                    }
                    return true;
                }
        );
    }
    
    public String inputName() {
        return Inputter.getString(
                "Enter name: ",
                "Employee name cannot be empty!!!"
        );
    }
    
    public EmployeeRole inputRole() {
        String role = Inputter.getString(
                "Enter role (Developer/Tester/Manager/HR): ",
                true, "Role cannot be empty!!!",
                false, null, null,
                true, "Invalid role! Must be one of: Developer, Tester, Manager, HR",
                input -> {
                    for (EmployeeRole rol : EmployeeRole.values()) {
                        if (rol.name().equalsIgnoreCase(input.trim())) {
                            return true;
                        }
                    }
                    return false;
                }
        );

        return EmployeeRole.parseRole(role);
    }
    
    public int inputBaseSalary() {
        return Inputter.getInt(
                "Enter base salary: ",
                1, 999999,
                false
        );
    }
    
    public int inputWorkingDays() {
        return Inputter.getInt(
                "Enter working days: ",
                0, 26,
                false
        );
    }
    
    public int inputBonus() {
        return Inputter.getInt(
                "Enter bonus: ",
                0, 99999999,
                false
        );
    }
}
