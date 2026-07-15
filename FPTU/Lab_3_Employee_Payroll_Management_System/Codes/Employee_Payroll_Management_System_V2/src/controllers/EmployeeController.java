package controllers;

import enums.EmployeeRole;
import enums.EmployeeStatus;
import interfaces.IList;
import models.Employee;
import utils.Acceptable;
import utils.DataSource;
import utils.Inputter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements IList<Employee> {

    DataSource context = DataSource.getInstance();

    /*
     * ####################################################
     * Add new employee
     * ####################################################
     */
    @Override
    public Employee add() {

        String newId = Inputter.getString(
                "\nEnter id: ",
                true, "Id cannot be empty!!!",
                true, Acceptable.EMPLOYEE_VALID_ID, "Id must be Exxx (e.g., E001)",
                true, "Employee already exists!!!",
                employeeId -> {
                    if (findById(employeeId) != null) {
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

        // Add new employee object to Employee List
        context.employeeList().add(newEmployee);
        context.markChanged();

        // Confirm and save
        if (Inputter.confirmSave("Employee")) {
            return newEmployee;
        }

        return null;
    }

    /*
     * ####################################################
     * Update current employee
     * ####################################################
     */
    @Override
    public Employee update(String id) {

        // Get current employee
        Employee existEmployee = findById(id);

        if (existEmployee == null) {
            return null;
        }

        String newRole = Inputter.getString(
                "Enter role (Developer/Tester/Manager/HR): ",
                true, null,
                false, null, null,
                false, null,
                input -> {
                    for (EmployeeRole rol : EmployeeRole.values()) {
                        if (rol.name().equalsIgnoreCase(input.trim())) {
                            return true;
                        }
                    }
                    return false;
                }
        );
        if (!newRole.isEmpty()) {
            // Parse
            EmployeeRole parsedRole = EmployeeRole.parseRole(newRole);
            existEmployee.setRole(parsedRole);
        }

        while (true) {
            int newBaseSalary = Inputter.getInt(
                    "Enter new base salary: ",
                    1, 999999,
                    true
            );

            if (newBaseSalary == -1) {
                break;
            }

            existEmployee.setBaseSalary(newBaseSalary);
        }

        while (true) {
            int newBonus = Inputter.getInt(
                    "Enter new bonus: ",
                    1, 999999,
                    true
            );

            if (newBonus == -1) {
                break;
            }

            existEmployee.setBonus(newBonus);
        }

        String newStatus = Inputter.getString(
                "Enter new status (Active/InActive): ",
                true, null,
                false, null, null,
                false, null,
                input -> {
                    for (EmployeeStatus stat : EmployeeStatus.values()) {
                        if (stat.name().equalsIgnoreCase(input.trim())) {
                            return true;
                        }
                    }
                    return false;
                }
        );
        if (!newStatus.isEmpty()) {
            // Parse
            EmployeeStatus parsedStatus = EmployeeStatus.parseStatus(newStatus);
            existEmployee.setStatus(parsedStatus);
        }

        return existEmployee;
    }

    /*
     * ####################################################
     * Delete current employee
     * ####################################################
     */
    @Override
    public boolean delete(String id) {

        // Get exists employee
        Employee employee = findById(id);

        // Return null if no employee found
        if (employee == null) {
            return false;
        }

        // Remove employee from list
        context.employeeList().remove(employee);
        context.markChanged();

        // Confirm and save changes
        Inputter.confirmSave(
                "Employee"
        );

        return true;
    }

    /*
     * ####################################################
     * List all employee
     * ####################################################
     */
    @Override
    public List<Employee> listAll() {
        return context.employeeList();
    }

    /*
     * ####################################################
     * Find employee by ID
     * ####################################################
     */
    @Override
    public Employee findById(String id) {
        for (Employee employee : context.employeeList()) {
            if (employee.getId().equalsIgnoreCase(id)) {
                return employee;
            }
        }

        return null;
    }

    /*
     * ####################################################
     * Calculate payroll
     * ####################################################
     */
    public int calculatePayroll() {

        int total = 0;

        List<Employee> employees = findByStatus("Active");

        if (employees.isEmpty()) {
            return 0;
        }
        for (Employee employee : employees) {
            total += employee.getBaseSalary();
        }

        return total;
    }

    /*
     * ####################################################
     * Find employee by status
     * ####################################################
     */
    public List<Employee> findByStatus(String status) {

        List<Employee> foundEmployee = new ArrayList<>();

        for (Employee employee : context.employeeList()) {
            String parsedStatus = EmployeeStatus.parseStatus(employee.getStatus());
            if (parsedStatus.equalsIgnoreCase(status)) {
                foundEmployee.add(employee);
            }
        }

        return foundEmployee;
    }

    /*
     * ####################################################
     * Find employee by name
     * ####################################################
     */
    public List<Employee> findByName(String name) {

        List<Employee> foundEmployee = new ArrayList<>();

        for (Employee employee : context.employeeList()) {
            if (employee.getName().contains(name)) {
                foundEmployee.add(employee);
            }
        }

        return foundEmployee;
    }

    /*
     * ####################################################
     * Find employee by role
     * ####################################################
     */
    public List<Employee> findByRole(String role) {

        List<Employee> foundEmployee = new ArrayList<>();

        for (Employee employee : context.employeeList()) {
            String parsedRole = EmployeeRole.parseRole(employee.getRole());
            if (parsedRole.contains(role)) {
                foundEmployee.add(employee);
            }
        }

        return foundEmployee;
    }

    /*
     * ####################################################
     * Display
     * ####################################################
     */
    @Override
    public void display() {
        List<Employee> employees = context.employeeList();

        if (employees.isEmpty()) {
            System.out.println(
                    "|                        No data in the system                       |"
            );
        }

        for (Employee employee : employees) {
            System.out.print(employee.display());
        }
    }

    /*
     * ####################################################
     * Save
     * ####################################################
     */
    @Override
    public void save() {
        context.saveEmployee(context.employeeList());
    }

    /*
     * ####################################################
     * Has unsaved changes ?
     * ####################################################
     */
    @Override
    public boolean hasUnsavedChanges() {
        return context.isDataChanged();
    }

    /*
     * ####################################################
     * Reload all (load from file)
     * ####################################################
     */
    public void reloadAll() {
        context.reloadAll();
    }
}
