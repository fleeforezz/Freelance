package views;

import controllers.EmployeeController;
import enums.EmployeeRole;
import enums.EmployeeStatus;
import models.Employee;
import utils.Acceptable;
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

        do {
            UI.titleHeader(appTitle, 74);
            System.out.println("| 1. Load employee data from file                                        |"); // Done
            System.out.println("| 2. Add a new employee                                                  |"); // Done
            System.out.println("| 3. Update employee information                                         |"); // Done
            System.out.println("| 4. Remove an employee by ID                                            |"); // Done
            System.out.println("| 5. Search employees by attribute                                       |"); // Done
            System.out.println("| 6. Calculate monthly payroll                                           |"); // Done
            System.out.println("| 7. Display employee list                                               |"); // Done
            System.out.println("| 8. Save data to file                                                   |"); // Done
            System.out.println("| 9. Quit program                                                        |"); // Done
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
                    String newId = Inputter.getString(
                            "\nEnter id: ",
                            true, "Id cannot be empty!!!",
                            true, Acceptable.EMPLOYEE_VALID_ID, "Id must be Exxx (e.g., E001)",
                            true, "Employee already exists!!!",
                            employeeId -> {
                                if (employeeController.findById(employeeId) != null) {
                                    return false;
                                }
                                return true;
                            }
                    );

                    String newName = Inputter.getString(
                            "Enter name: ",
                            "Employee name cannot be empty!!!"
                    );

                    String newRole = Inputter.getString(
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
                    EmployeeRole parsedNewRole = EmployeeRole.parseRole(newRole);

                    int newBaseSalary = Inputter.getInt(
                            "Enter base salary: ",
                            1, 999999,
                            false
                    );

                    int newWorkingDays = Inputter.getInt(
                            "Enter working days: ",
                            0, 26,
                            false
                    );

                    int newBonus = Inputter.getInt(
                            "Enter bonus: ",
                            0, 99999999,
                            false
                    );

                    Employee newEmployee = new Employee(
                            newId,
                            newName,
                            parsedNewRole,
                            newBaseSalary,
                            newWorkingDays,
                            newBonus,
                            EmployeeStatus.Active
                    );

                    if (employeeController.add(newEmployee) != null) {
                        System.out.println("\nNew employee created!!!\n");
                    } else {
                        System.out.println("\nCannot create new employee!!!\n");
                    }

                    break;
                case 3:

                    break;
                case 4:
                    String removeId = Inputter.getString(
                            "\nEnter employee id: ",
                            "ID cannot be empty!!!"
                    );

                    if (employeeController.delete(removeId)) {
                        UI.success("\nEmployee has been removed!!!\n");
                    } else {
                        UI.error("\nEmployee does not exists!!!\n");
                    }
                    break;
                case 5:
                    int searchOption = 0;

                    do {
                        UI.titleHeader("Search", 50);
                        System.out.println("| 1. Search by ID                                |"); // Done
                        System.out.println("| 2. Search by Name                              |"); // Done
                        System.out.println("| 3. Search by Role                              |"); // Done
                        System.out.println("| 4. Search by Status                            |"); // Done
                        System.out.println("| 5. Return to Main Menu                         |"); // Done
                        UI.border(50);

                        searchOption = Inputter.getInt(
                                "Enter your choice: ",
                                Inputter.MIN,
                                Inputter.MAX,
                                false
                        );

                        switch (searchOption) {
                            case 1:
                                String searchId = Inputter.getString(
                                        "Enter employee id: ",
                                        "Id cannot be empty!!!"
                                );

                                Employee foundEmployee = employeeController.findById(searchId);

                                if (foundEmployee != null) {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    System.out.print(foundEmployee.display());
                                    UI.border(50);
                                } else {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    System.out.println("| No matching employee found!!!");
                                    UI.border(50);
                                }
                                break;
                            case 2:
                                String searchName = Inputter.getString(
                                        "Enter employee name: ",
                                        "Name cannot be empty!!!"
                                );

                                List<Employee> foundEmployees = employeeController.findByName(searchName);

                                UI.tableHeader(employeeCols, employeeColWidth);
                                if (foundEmployees.isEmpty()) {
                                    System.out.println("| No matching employee found!");
                                } else {
                                    for (Employee e : foundEmployees) {
                                        System.out.print(e.display());
                                    }
                                }
                                UI.border(102);
                                break;
                            case 3:
                                String searchRole = Inputter.getString(
                                        "Enter employee role: ",
                                        "Role cannot be empty!!!"
                                );

                                foundEmployees = employeeController.findByRole(searchRole);

                                UI.tableHeader(employeeCols, employeeColWidth);
                                if (foundEmployees.isEmpty()) {
                                    System.out.println("| No matching employee found!");
                                } else {
                                    for (Employee e : foundEmployees) {
                                        System.out.print(e.display());
                                    }
                                }
                                UI.border(102);
                                break;
                            case 4:
                                String searchStatus = Inputter.getString(
                                        "Enter employee status: ",
                                        "Status cannot be empty!!!"
                                );

                                foundEmployees = employeeController.findByRole(searchStatus);
                                UI.tableHeader(employeeCols, employeeColWidth);
                                if (foundEmployees.isEmpty()) {
                                    System.out.println("| No matching employee found!");
                                } else {
                                    for (Employee e : foundEmployees) {
                                        System.out.print(e.display());
                                    }
                                }
                                UI.border(102);
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice, please try again!!!");
                        }

                    } while (searchOption >= 1 && searchOption <= 4);

                    break;
                case 6:
                    employeeController.calculatePayroll();
                    break;
                case 7:
                    // Load Data from file then add to this list
                    List<Employee> employeeList = employeeController.listAll();

                    UI.tableHeader(employeeCols, employeeColWidth);
                    if (employeeList != null) {
                        for (Employee employee : employeeList) {
                            System.out.print(employee.display());
                        }
                    } else {
                        UI.error("No data in system");
                    }
                    UI.border(102);
                    break;
                case 8:
                    employeeController.save();
                    System.out.println("\nData has been saved to files successfully!\n");
                    break;
                case 9:
                    if (employeeController.hasUnsavedChanges()) {
                        employeeController.save();
                        System.out.println("\nUnsaved changes detected — data has been saved.");
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
