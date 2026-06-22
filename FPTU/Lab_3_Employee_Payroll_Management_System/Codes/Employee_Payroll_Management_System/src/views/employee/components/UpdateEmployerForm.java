package views.employee.components;

import controllers.EmployeeController;
import enums.EmployeeRole;
import enums.EmployeeStatus;
import models.Employee;
import utils.Inputter;

public class UpdateEmployerForm {
    
    private final Employee updatedEmployee;

    public UpdateEmployerForm(Employee updatedEmployee) {
        this.updatedEmployee = updatedEmployee;
    }
    
    public Employee input() {
        inputRole();
        inputBaseSalary();
        inputBonus();
        inputStatus();
        
        return updatedEmployee;
    }
    
    public void inputRole() {
        String newRole = Inputter.getString(
                "Enter role (Developer/Tester/Manager/HR): ",
                false, null,
                false, null, null,
                false, null,
                input -> {
                    for (EmployeeRole rol : EmployeeRole.values()) {
                        if (rol.name().equalsIgnoreCase(input.trim())) {
                            return true;
                        }
                    }
                    return false;
                }
        );
        if (!newRole.isEmpty()) {
            // Parse
            EmployeeRole parsedRole = EmployeeRole.parseRole(newRole);
            updatedEmployee.setRole(parsedRole);
        }
    }
    
    public void inputBaseSalary() {
        while (true) {
            int newBaseSalary = Inputter.getInt(
                    "Enter new base salary: ",
                    1, 999999,
                    true
            );

            if (newBaseSalary == -1) {
                break;
            }

            updatedEmployee.setBaseSalary(newBaseSalary);
        }
    }
    
    public void inputBonus() {
        while (true) {
            int newBonus = Inputter.getInt(
                    "Enter new bonus: ",
                    1, 999999,
                    true
            );

            if (newBonus == -1) {
                break;
            }

            updatedEmployee.setBonus(newBonus);
        }
    }
    
    public void inputStatus() {
        String newStatus = Inputter.getString(
                "Enter new status (Active/InActive): ",
                true, null,
                false, null, null,
                false, null,
                input -> {
                    for (EmployeeStatus stat : EmployeeStatus.values()) {
                        if (stat.name().equalsIgnoreCase(input.trim())) {
                            return true;
                        }
                    }
                    return false;
                }
        );
        if (!newStatus.isEmpty()) {
            // Parse
            EmployeeStatus parsedStatus = EmployeeStatus.parseStatus(newStatus);
            updatedEmployee.setStatus(parsedStatus);
        }
    }
}
