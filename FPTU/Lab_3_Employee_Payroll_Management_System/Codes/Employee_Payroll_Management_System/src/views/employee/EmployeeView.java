package views.employee;

import controllers.EmployeeController;
import enums.EmployeeRole;
import enums.EmployeeStatus;
import models.Employee;
import utils.Inputter;
import views.UI;
import views.employee.components.AddEmployeeForm;
import views.employee.components.UpdateEmployerForm;

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

        // Create new employee
        Employee newEmployee = new AddEmployeeForm(employeeController).input();

        if (employeeController.Add(newEmployee) != null) {
            System.out.println("\nNew employee created!!!\n");
        } else {
            System.out.println("\nCannot create new employee!!!\n");
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
        
        // Find target Employee
        Employee oldEmployee  = employeeController.FindById(updateId);
        if (oldEmployee == null) {
            System.out.println("Employee not found.");
            return;
        }

        // Map target employee to updated employee
        Employee updatedEmployee = new Employee(oldEmployee);
        updatedEmployee = new UpdateEmployerForm(updatedEmployee).input();

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
