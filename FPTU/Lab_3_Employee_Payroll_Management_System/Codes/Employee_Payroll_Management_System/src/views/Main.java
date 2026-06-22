package views;

import controllers.EmployeeController;
import utils.Inputter;

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
                    EmployeeView.LoadEmployeeData(employeeController);
                    break;
                case 2:
                    EmployeeView.AddEmployee(employeeController);
                    break;
                case 3:
                    EmployeeView.UpdateEmployee(employeeController);
                    break;
                case 4:
                    EmployeeView.RemoveEmployee(employeeController);
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
                                EmployeeView.SearchEmployeeById(
                                        employeeController, 
                                        employeeCols, 
                                        employeeColWidth
                                );
                                break;
                            case 2:
                                EmployeeView.SearchEmployeeByName(
                                        employeeController,
                                        employeeCols,
                                        employeeColWidth
                                );
                                break;
                            case 3:
                                EmployeeView.SearchEmployeeByRole(
                                        employeeController,
                                        employeeCols,
                                        employeeColWidth
                                );
                                break;
                            case 4:
                                EmployeeView.SearchEmployeeByStatus(
                                        employeeController,
                                        employeeCols,
                                        employeeColWidth
                                );
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println("Invalid choice, please try again!!!");
                        }
                        
                    } while (searchOption >= 1 && searchOption <= 4);
                    
                    break;
                case 6:
                    EmployeeView.CalculatePayroll(employeeController);
                    break;
                case 7:
                    EmployeeView.ListAllEmployee(
                            employeeController,
                            employeeCols,
                            employeeColWidth
                    );
                    break;
                case 8:
                    EmployeeView.SaveToFile(employeeController);
                    break;
                case 9:
                    EmployeeView.QuitProgram(employeeController);
                    break;
                default:
                    System.out.println("Invalid choice, please try again !!!");
                    break;
            }

        } while (choice >= 1 && choice <= 8);
    }
}
