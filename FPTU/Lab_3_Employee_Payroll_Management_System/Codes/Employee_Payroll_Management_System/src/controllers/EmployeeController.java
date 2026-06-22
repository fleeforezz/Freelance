package controllers;

import enums.EmployeeRole;
import enums.EmployeeStatus;
import interfaces.IList;
import models.Employee;
import utils.Acceptable;
import utils.DataSource;
import utils.Inputter;

import java.util.Collections;
import java.util.List;

public class EmployeeController implements IList<Employee> {
    
    DataSource context = DataSource.getInstance();
    
    @Override
    public Employee Add() {
        
        // Input ID
        String id = Inputter.getString(
                "\nEnter id: ",
                true, "Id cannot be empty!!!",
                true, Acceptable.EMPLOYEE_VALID_ID, "Id must be Exxx (e.g., E001)",
                true, "Employee already exists!!!",
                employeeId -> {
                    if (FindById(employeeId) != null) {
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
                    for (EmployeeRole rol : enums.EmployeeRole.values()) {
                        if (rol.equals(input.trim())) {
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
        String status = Inputter.getString(
                "Enter status (Active/InActive): ",
                true, "Status cannot be empty!!!",
                false, null, null,
                true, "Invalid status! Must be one of: Active, InActive",
                input -> {
                    for (EmployeeStatus stat : enums.EmployeeStatus.values()) {
                        if (stat.equals(input.trim())) {
                            return true;
                        }
                    }
                    return false;
                }
        );
        // Parse
        EmployeeStatus parsedStatus = EmployeeStatus.parseStatus(status);
        
        // Create new employee
        Employee newEmployee = new Employee(
                id,
                name,
                parsedRole,
                baseSalary,
                workingDays,
                bonus,
                parsedStatus
        );
        
        // Add new employee object to Employee List
        context.employeeList().add(newEmployee);
        context.markChanged();
        
        // Confirm and save
        if (Inputter.confirmSave(
                "Employee"
        )) {
            return newEmployee;
        }
        
        return null;
    }

    @Override
    public Employee Update(Employee entity) {
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

    @Override
    public List<Employee> ListAll() {
        return context.employeeList();
    }

    @Override
    public Employee FindById(String id) {
        for (Employee employee : context.employeeList()) {
            if (employee.getId().equalsIgnoreCase(id)) {
                return employee;
            }
        }
        
        return null;
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
