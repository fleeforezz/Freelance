package views.employee;

import controllers.EmployeeController;
import enums.EmployeeRole;
import enums.EmployeeStatus;
import models.Employee;
import utils.Acceptable;
import utils.Inputter;
import views.UI;

import java.util.List;

public class EmployeeView {

    public static final int TABLE_WIDTH = 102;

    /*
     * ####################################################
     * Load data from file
     * ####################################################
     */
    public static void LoadEmployeeData(EmployeeController employeeController) {
        employeeController.reloadAll();
    }

    /*
     * ####################################################
     * Add new employee
     * ####################################################
     */
    public static void AddEmployee(EmployeeController employeeController) {
        // Input ID
        String id = Inputter.getString(
                "\nEnter id: ",
                true, "Id cannot be empty!!!",
                true, Acceptable.EMPLOYEE_VALID_ID, "Id must be Exxx (e.g., E001)",
                true, "Employee already exists!!!",
                employeeId -> {
                    if (employeeController.FindById(employeeId) != null) {
                        return false;
                    }
                    return true;
                }
        );

        // Input name
        String name = Inputter.getString(
                "Enter name: ",
                "Employee name cannot be empty!!!"
        );

        // Input role
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
        // Parse
        EmployeeRole parsedRole = EmployeeRole.parseRole(role);

        // Input base salary
        int baseSalary = Inputter.getInt(
                "Enter base salary: ",
                1, 999999,
                false
        );

        // Input working days
        int workingDays = Inputter.getInt(
                "Enter working days: ",
                0, 26,
                false
        );

        // Input bonus
        int bonus = Inputter.getInt(
                "Enter bonus: ",
                0, 99999999,
                false
        );

        // Input status
        EmployeeStatus status = EmployeeStatus.Active;

        // Create new employee
        Employee newEmployee = new Employee(
                id,
                name,
                parsedRole,
                baseSalary,
                workingDays,
                bonus,
                status
        );

        if (employeeController.Add(newEmployee) != null) {
            System.out.println("\nNew employee created!!!\n");
        } else {
            System.out.println("\nCannot create new club!!!\n");
        }
    }
    
    /*
     * ####################################################
     * Update current employee
     * ####################################################
     */
    public static void UpdateEmployee(EmployeeController employeeController) {

        String updateId = Inputter.getString(
                "\nEnter employee id: ",
                "ID cannot be empty!!!"
        );
        
        Employee oldEmployee  = employeeController.FindById(updateId);

        if (oldEmployee == null) {
            System.out.println("Employee not found.");
            return;
        }

        Employee updatedEmployee = new Employee(
                oldEmployee.getId(),
                oldEmployee.getRole(),
                oldEmployee.getBaseSalary(),
                oldEmployee.getBonus(),
                oldEmployee.getStatus()
        );
        
        // Input new role
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

        // Input new base salary
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

        // Input new bonus
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

        // Input new status
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

        if (employeeController.Update(updateId,  updatedEmployee) != null) {
            System.out.println("\nEmployee updated!!!\n");
        } else {
            System.out.println("\nEmployee not found!!!\n");
        }
    }
    
    /*
     * ####################################################
     * Remove current employee
     * ####################################################
     */
    public static void RemoveEmployee(EmployeeController employeeController) {
        String removeId = Inputter.getString(
                "\nEnter employee id: ",
                "ID cannot be empty!!!"
        );

        boolean isDeleted = employeeController.Delete(removeId);
        if (isDeleted) {
            UI.success("\nEmployee has been removed!!!\n");
        } else {
            UI.error("\nEmployee does not exists!!!\n");
        }
    }
    
}
