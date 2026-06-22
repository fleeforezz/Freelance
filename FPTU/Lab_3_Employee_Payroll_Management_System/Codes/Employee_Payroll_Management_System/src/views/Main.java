package views;

import controllers.EmployeeController;
import utils.Inputter;
import views.employee.EmployeeActionView;
import views.employee.EmployeeReportView;
import views.employee.EmployeeSearchView;
import views.employee.EmployeeView;
import views.menu.MenuView;

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
            MenuView.showMainMenu(appTitle);

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
                        MenuView.showSearchMenu();

                        searchOption = Inputter.getInt(
                                "Enter your choice: ",
                                Inputter.MIN,
                                Inputter.MAX,
                                false
                        );
                        
                        switch (searchOption) {
                            case 1:
                                EmployeeSearchView.SearchEmployeeById(
                                        employeeController, 
                                        employeeCols, 
                                        employeeColWidth
                                );
                                break;
                            case 2:
                                EmployeeSearchView.SearchEmployeeByName(
                                        employeeController,
                                        employeeCols,
                                        employeeColWidth
                                );
                                break;
                            case 3:
                                EmployeeSearchView.SearchEmployeeByRole(
                                        employeeController,
                                        employeeCols,
                                        employeeColWidth
                                );
                                break;
                            case 4:
                                EmployeeSearchView.SearchEmployeeByStatus(
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
                    EmployeeReportView.CalculatePayroll(employeeController);
                    break;
                case 7:
                    EmployeeReportView.ListAllEmployee(
                            employeeController,
                            employeeCols,
                            employeeColWidth
                    );
                    break;
                case 8:
                    EmployeeActionView.SaveToFile(employeeController);
                    break;
                case 9:
                    EmployeeActionView.QuitProgram(employeeController);
                    break;
                default:
                    System.out.println("Invalid choice, please try again !!!");
                    break;
            }

        } while (choice >= 1 && choice <= 8);
    }
}
