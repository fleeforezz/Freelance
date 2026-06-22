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

        if (employeeController.Update(updateId) != null) {
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
