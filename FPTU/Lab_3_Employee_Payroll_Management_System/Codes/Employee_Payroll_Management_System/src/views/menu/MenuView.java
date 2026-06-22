package views.menu;

import views.UI;

public class MenuView {
    
    public static void showMainMenu(String appTitle) {
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
    }
    
    public static void showSearchMenu() {
        UI.titleHeader("Search", 50);
        System.out.println("| 1. Search by ID                                |"); // Done
        System.out.println("| 2. Search by Name                              |"); // Done
        System.out.println("| 3. Search by Role                              |"); // Done
        System.out.println("| 4. Search by Status                            |"); // Done
        System.out.println("| 5. Return to Main Menu                         |"); // Done
        UI.border(50);
    }
}
