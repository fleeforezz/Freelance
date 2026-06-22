package views.employee;

import controllers.EmployeeController;
import models.Employee;
import views.UI;

import java.util.List;

public class EmployeeReportView {

    public static final int TABLE_WIDTH = 102;
    
    /*
     * ####################################################
     * Calculate total salary
     * ####################################################
     */
    public static void CalculatePayroll(EmployeeController employeeController) {
        int calPayroll = employeeController.CalculatePayroll();
        if (calPayroll > 0) {
            System.out.println("\nTotal Salary: " + calPayroll + "\n");
        } else {
            System.out.println("\nNo active employees found for calculate\n");
        }
    }

    /*
     * ####################################################
     * List all employee
     * ####################################################
     */
    public static void ListAllEmployee(
            EmployeeController employeeController,
            String[] employeeCols,
            int[] employeeColWidth
    ) {
        // Load Data from file then add to this list
        List<Employee> employeeList = employeeController.ListAll();

        UI.tableHeader(employeeCols, employeeColWidth);
        if (employeeList != null) {
            for (Employee employee : employeeList) {
                System.out.print(employee.display());
            }
        } else {
            UI.error("No data in system");
        }
        UI.border(TABLE_WIDTH);
    }
}
