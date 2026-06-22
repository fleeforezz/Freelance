package views;

import controllers.EmployeeController;
import models.Employee;
import utils.Inputter;
import views.Components.TestUI;

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
            System.out.println("| 1. Load employee data from file                                        |"); // Done
            System.out.println("| 2. Add a new employee                                                  |"); // Done
            System.out.println("| 3. Update employee information                                         |"); 
            System.out.println("| 4. Remove an employee by ID                                            |"); // Done
            System.out.println("| 5. Search employees by attribute                                       |"); // Done
            System.out.println("| 6. Calculate monthly payroll                                           |"); 
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
                    if (employeeController.Add() != null) {
                        System.out.println("\nNew employee created!!!\n");
                    } else {
                        System.out.println("\nCannot create new club!!!\n");
                    }
                    break;
                case 3:
                    String updateId = Inputter.getString(
                            "\nEnter employee id: ",
                            "ID cannot be empty!!!"
                    );
                    
                    if (employeeController.Update(updateId) != null) {
                        System.out.println("\nEmployee updated!!!\n");
                    } else {
                        System.out.println("\nCannot update current employee!!!\n");
                    }
                    break;
                case 4:
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
                                
                                Employee idEmployee = employeeController.FindById(searchId);
                                
                                if (idEmployee != null) {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    System.out.print(idEmployee.display());
                                    UI.border(102);
                                } else {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    System.out.println("| No matching employee found!!!");
                                    UI.border(102);
                                }
                                
                                break;
                            case 2:
                                String searchName = Inputter.getString(
                                        "Enter employee name: ",
                                        "Name cannot be empty!!!"
                                );
                                
                                List<Employee> nameEmployees = employeeController.FindByName(searchName);
                                
                                if (!nameEmployees.isEmpty()) {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    for (Employee employee : nameEmployees) {
                                        System.out.print(employee.display());
                                    }
                                    UI.border(102);
                                } else {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    System.out.println("| No matching employee found!!!");
                                    UI.border(102);
                                }
                                break;
                            case 3:
                                String searchRole = Inputter.getString(
                                        "Enter employee role: ",
                                        "Role cannot be empty!!!"
                                );
                                
                                List<Employee> roleEmployees = employeeController.FindByRole(searchRole);
                                
                                if (!roleEmployees.isEmpty()) {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    for (Employee employee : roleEmployees) {
                                        System.out.print(employee.display());
                                    }
                                    UI.border(102);
                                } else {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    System.out.println("| No matching employee found!!!");
                                    UI.border(102);
                                }
                                break;
                            case 4:
                                String searchStatus = Inputter.getString(
                                        "Enter employee status: ",
                                        "Status cannot be empty!!!"
                                );

                                List<Employee> statusEmployees = employeeController.FindByStatus(searchStatus);

                                if (!statusEmployees.isEmpty()) {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    for (Employee employee : statusEmployees) {
                                        System.out.print(employee.display());
                                    }
                                    UI.border(102);
                                } else {
                                    UI.tableHeader(employeeCols, employeeColWidth);
                                    System.out.println("| No matching employee found!!!");
                                    UI.border(102);
                                }
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice, please try again!!!");
                        }
                        
                    } while (searchOption >= 1 && searchOption <= 4);
                    
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
