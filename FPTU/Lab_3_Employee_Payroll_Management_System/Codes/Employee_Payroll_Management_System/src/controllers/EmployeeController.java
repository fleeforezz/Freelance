package controllers;

import interfaces.IList;
import models.Employee;
import utils.DataSource;

import java.util.Collections;
import java.util.List;

public class EmployeeController implements IList<Employee> {
    
    DataSource context = DataSource.getInstance();
    
    @Override
    public Employee Add() {
        return null;
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
