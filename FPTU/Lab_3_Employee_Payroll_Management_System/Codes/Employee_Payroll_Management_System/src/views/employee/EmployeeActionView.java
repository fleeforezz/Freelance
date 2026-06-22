package views.employee;

import controllers.EmployeeController;

public class EmployeeActionView {

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
