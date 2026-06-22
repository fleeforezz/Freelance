package controllers;

import enums.EmployeeRole;
import enums.EmployeeStatus;
import interfaces.IList;
import models.Employee;
import utils.Acceptable;
import utils.DataSource;
import utils.Inputter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeController implements IList<Employee> {
    
    DataSource context = DataSource.getInstance();

    /*
     * ####################################################
     * Add new employee
     * ####################################################
     */
    @Override
    public Employee Add(Employee employee) {
        
        // Add new employee object to Employee List
        context.employeeList().add(employee);
        context.markChanged();
        
        // Confirm and save
        if (Inputter.confirmSave("Employee")) {
            return employee;
        }
        
        return null;
    }

    /*
     * ####################################################
     * Update current employee
     * ####################################################
     */
    @Override
    public Employee Update(String id) {
        
        // Get exists Employee
        Employee employee = FindById(id);
        
        // Return null if no employee found
        if (employee == null) {
            return null;
        }
        
        // Input new role
        String newRole = Inputter.getString(
                "Enter role (Developer/Tester/Manager/HR): ",
                false, null,
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
            employee.setRole(parsedRole);
        }
        
        // Input new base salary
        while (true) {
            int newBaseSalary = Inputter.getInt(
                    "Enter new base salary: ",
                    1, 999999,
                    true
            );
            
            if (newBaseSalary == -1) {
                break;
            }
            
            employee.setBaseSalary(newBaseSalary);
            context.markChanged();
        }
        
        // Input new bonus
        while (true) {
            int newBonus = Inputter.getInt(
                    "Enter new bonus: ",
                    1, 999999,
                    true
            );

            if (newBonus == -1) {
                break;
            }

            employee.setBonus(newBonus);
            context.markChanged();
        }
        
        // Input new status
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
            employee.setStatus(parsedStatus);
        }
        
        // Confirm and save changes
        if (Inputter.confirmSave("Employee")) {
            return employee;
        }
        
        return null;
    }

    @Override
    public boolean Delete(String id) {
        
        // Get exists employee
        Employee employee = FindById(id);
        
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
    public List<Employee> ListAll() {
        return context.employeeList();
    }

    /*
     * ####################################################
     * Find employee by ID
     * ####################################################
     */
    @Override
    public Employee FindById(String id) {
        for (Employee employee : context.employeeList()) {
            if (employee.getId().equalsIgnoreCase(id)) {
                return employee;
            }
        }
        
        return null;
    }

    /*
     * ####################################################
     * Find employee by name
     * ####################################################
     */
    public List<Employee> FindByName(String name) {
        
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
    public List<Employee> FindByRole(String role) {

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
     * Find employee by status
     * ####################################################
     */
    public List<Employee> FindByStatus(String status) {

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
     * Calculate payroll
     * ####################################################
     */
    public int CalculatePayroll() {
        
        int total = 0;
        
        List<Employee> employees = FindByStatus("Active");
        
        if (employees.isEmpty()) {
            return 0;
        }
        for (Employee employee : employees) {
            total += employee.getBaseSalary();
        }
        
        return total;
    }

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

    @Override
    public void save() {
        context.saveEmployee(context.employeeList());
    }

    @Override
    public boolean hasUnsavedChanges() {
        return context.isDataChanged();
    }
    
    public void reloadAll() {
        context.reloadAll();
    }
}
