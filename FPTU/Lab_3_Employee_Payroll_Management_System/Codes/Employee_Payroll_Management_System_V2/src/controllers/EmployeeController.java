package controllers;

import enums.EmployeeRole;
import enums.EmployeeStatus;
import interfaces.IList;
import models.Employee;
import utils.DataSource;
import utils.Inputter;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements IList<Employee> {

    DataSource context = DataSource.getInstance();

    @Override
    public Employee add(Employee employee) {
        // Add new employee object to Employee List
        context.employeeList().add(employee);
        context.markChanged();

        // Confirm and save
        if (Inputter.confirmSave("Employee")) {
            return employee;
        }

        return null;
    }

    @Override
    public Employee update(String id, Employee entity) {
        return null;
    }

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

    @Override
    public List<Employee> listAll() {
        return context.employeeList();
    }

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
