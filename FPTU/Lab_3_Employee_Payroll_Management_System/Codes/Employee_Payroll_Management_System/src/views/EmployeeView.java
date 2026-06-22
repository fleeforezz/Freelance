package views;

import controllers.EmployeeController;
import enums.EmployeeRole;
import enums.EmployeeStatus;
import models.Employee;
import utils.Acceptable;
import utils.Inputter;

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
    
    /*
     * ####################################################
     * Save Changes
     * ####################################################
     */
    public static void SaveToFile(EmployeeController employeeController) {
        employeeController.save();
        System.out.println("\nData has been saved to files successfully!\n");
    }
    
    /*
     * ####################################################
     * Quit program
     * ####################################################
     */
    public static void QuitProgram(EmployeeController employeeController) {
        if (employeeController.hasUnsavedChanges()) {
            employeeController.save();
            System.out.println("\nUnsaved changes detected — data has been saved.");
        }
        System.out.println("Goodbye !!!");
    }
}
