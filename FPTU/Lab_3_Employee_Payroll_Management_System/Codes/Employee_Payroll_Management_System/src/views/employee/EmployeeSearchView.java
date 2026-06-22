package views.employee;

import controllers.EmployeeController;
import models.Employee;
import utils.Inputter;
import views.UI;

import java.util.List;

public class EmployeeSearchView {

    public static final int TABLE_WIDTH = 102;
    
    private static void displayEmployees(
            List<Employee> employees,
            String[] cols,
            int[] widths
    ) {
        UI.tableHeader(cols, widths);

        if (employees.isEmpty()) {
            System.out.println("| No matching employee found!");
        } else {
            for (Employee e : employees) {
                System.out.print(e.display());
            }
        }

        UI.border(102);
    }

    /*
     * ####################################################
     * Search by ID
     * ####################################################
     */
    public static void SearchEmployeeById(
            EmployeeController employeeController,
            String[] employeeCols,
            int[] employeeColWidth
    ) {
        String searchId = Inputter.getString(
                "Enter employee id: ",
                "Id cannot be empty!!!"
        );

        Employee idEmployee = employeeController.FindById(searchId);

        if (idEmployee != null) {
            UI.tableHeader(employeeCols, employeeColWidth);
            System.out.print(idEmployee.display());
            UI.border(TABLE_WIDTH);
        } else {
            UI.tableHeader(employeeCols, employeeColWidth);
            System.out.println("| No matching employee found!!!");
            UI.border(TABLE_WIDTH);
        }
    }

    /*
     * ####################################################
     * Search by Name
     * ####################################################
     */
    public static void SearchEmployeeByName(
            EmployeeController employeeController,
            String[] employeeCols,
            int[] employeeColWidth
    ) {
        String searchName = Inputter.getString(
                "Enter employee name: ",
                "Name cannot be empty!!!"
        );

        List<Employee> nameEmployees = employeeController.FindByName(searchName);

        displayEmployees(
                nameEmployees,
                employeeCols,
                employeeColWidth
        );
    }

    /*
     * ####################################################
     * Search by Role
     * ####################################################
     */
    public static void SearchEmployeeByRole(
            EmployeeController employeeController,
            String[] employeeCols,
            int[] employeeColWidth
    ) {
        String searchRole = Inputter.getString(
                "Enter employee role: ",
                "Role cannot be empty!!!"
        );

        List<Employee> roleEmployees = employeeController.FindByRole(searchRole);

        displayEmployees(
                roleEmployees,
                employeeCols,
                employeeColWidth
        );
    }

    /*
     * ####################################################
     * Search by Status
     * ####################################################
     */
    public static void SearchEmployeeByStatus(
            EmployeeController employeeController,
            String[] employeeCols,
            int[] employeeColWidth
    ) {
        String searchStatus = Inputter.getString(
                "Enter employee status: ",
                "Status cannot be empty!!!"
        );

        List<Employee> statusEmployees = employeeController.FindByStatus(searchStatus);

        displayEmployees(
                statusEmployees,
                employeeCols,
                employeeColWidth
        );
    }
}
