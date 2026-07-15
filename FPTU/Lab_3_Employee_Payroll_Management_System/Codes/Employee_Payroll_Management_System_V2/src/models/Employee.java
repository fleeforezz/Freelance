package models;

import enums.EmployeeRole;
import enums.EmployeeStatus;

public class Employee {
    private String id;
    private String name;
    private EmployeeRole role;
    private int baseSalary;
    private int workingDays;
    private int bonus;
    private EmployeeStatus status;
    
    public Employee() { }

    public Employee(String id, String name, EmployeeRole role, int baseSalary, int workingDays, int bonus, EmployeeStatus status) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.baseSalary = baseSalary;
        this.workingDays = workingDays;
        this.bonus = bonus;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %d, %d, %d, %s",
                id,
                name,
                role,
                baseSalary,
                workingDays,
                bonus,
                status
        );
    }

    /*
        #####################
        Display Employee Info
        #####################
     */
    public String display() {
        return String.format(
                "| %-8s | %-20s | %-13s | %-11d | %-12d | %-8d | %-8s |\n",
                this.id, this.name, this.role, 
                this.baseSalary, this.workingDays, 
                this.bonus, this.status
        );
    }
}
