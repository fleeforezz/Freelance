package views;

import controllers.EmployeeController;
import models.Employee;
import utils.Inputter;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        int choice = 0;

        // UI Components
        String appTitle = "Employee Payroll Management System";

        String[] employeeCols = {
            "Id",
            "Name",
            "Role",
            "Base Salary",
            "Working Days",
            "Bonus",
            "Status"
        };

        int[] employeeColWidth = {
            8, 20, 13, 8, 8, 8, 8
        };

        // Controllers
        EmployeeController employeeController = new EmployeeController();

        // Load Data from file then add to this list
        List<Employee> employeeList = employeeController.ListAll();

        do {
            UI.titleHeader(appTitle, 74);
            System.out.println("| 1. Load employee data from file                                        |"); 
            System.out.println("| 2. Add a new employee                                                  |"); 
            System.out.println("| 3. Update employee information                                         |"); 
            System.out.println("| 4. Remove an employee by ID                                            |"); 
            System.out.println("| 5. Search employees by attribute                                       |"); 
            System.out.println("| 6. Calculate monthly payroll                                           |"); 
            System.out.println("| 7. Display employee list                                               |"); 
            System.out.println("| 8. Save data to file                                                   |"); 
            System.out.println("| 9. Quit program                                                        |"); 
            UI.border(74);

            choice = Inputter.getInt(
                    "Enter your choice: ",
                    Inputter.MIN,
                    Inputter.MAX,
                    false
            );
            
            switch (choice) {
                case 1:
                    employeeController.reloadAll();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    if (employeeList != null) {
                        UI.tableHeader(employeeCols, employeeColWidth);
                        for (Employee employee : employeeList) {
                            System.out.print(employee.display());
                        }
                        UI.border(102);
                    } else {
                        UI.tableHeader(employeeCols, employeeColWidth);
                        UI.error("No data in system");
                        UI.border(102);
                    }
                    break;
                case 8:
                    employeeController.save();
                    System.out.println("\nData has been saved to files successfully!\n");
                    break;
                case 9:
                    if (employeeController.hasUnsavedChanges()) {
                        employeeController.save();
                        System.out.println("\\nUnsaved changes detected — data has been saved.");
                    }
                    System.out.println("Goodbye !!!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again !!!");
                    break;
            }

        } while (choice >= 1 && choice <= 8);
    }
}
