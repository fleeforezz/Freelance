package controllers;

import enums.EmployeeRole;
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
    }

    @Override
    public Employee Update(Employee entity) {
        return null;
    }

    @Override
    public boolean Delete(String id) {
        return false;
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
