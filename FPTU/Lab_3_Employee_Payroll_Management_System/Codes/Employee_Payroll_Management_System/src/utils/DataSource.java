package utils;

import models.Employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataSource {
    
    private static DataSource instance;
    private boolean dataChanged = false;
    
    public void markChanged() {
        dataChanged = true;
    }
    
    public boolean isDataChanged() {
        return dataChanged;
    }
    
    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
    
    private DataSource() { }

    /*
     * ###################
     * Club
     * ###################
     */
//    private static final String CLUB_FILE_PATH = "D:\\Repository\\Github\\Fleeforezz\\Lab\\teaching\\Coding\\clubs.txt";
    private final String CLUB_FILE_PATH = "D:\\Github\\fleeforezz\\Freelance\\FPTU\\Lab_3_Employee_Payroll_Management_System\\Codes\\Employee_Payroll_Management_System\\src\\data\\employees.txt";
    
    private List<Employee> employees = null;
    
    public List<Employee> employeeList() {
        if (employees == null) {
            employees = loadEmployees();
        }
        
        return employees;
    }
    
    /*
    * #####################################################
    * Combine 2 list for load the data up
    * #####################################################
    */
    public boolean reloadAll() {
        List<Employee> newEmployees;
        try {
            newEmployees = loadEmployees();
        } catch (Exception e) {
            System.out.println("Load data failed!");
            return false;
        }

        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.clear();
        employees.addAll(newEmployees);

        System.out.println("Load data successfully!");
        return true;
    }
    
    /*
    * #############################################################
    * Load employees.txt file from machine and add to Employee List
    * #############################################################
    */
    public List<Employee> loadEmployees() {
        List<Employee> clubs = new ArrayList<>();

         try (BufferedReader br = new BufferedReader(new FileReader(CLUB_FILE_PATH))) {
             String line;
             
             while ((line = br.readLine()) != null) {                 
                 if (line.trim().isEmpty()) {
                     continue;
                 }
                 
                 String[] parts = line.split(",");
                 
                 if (parts.length != 4) {
                     System.out.println("Invalid record" + line);
                     continue;
                 }
                 
                 String id = parts[0].trim();
                 String name = parts[1].trim();
                 String role = parts[2].trim();
                 int baseSalary = Integer.parseInt(parts[3].trim());
                 int workingDays = Integer.parseInt(parts[4].trim());
                 int bonus = Integer.parseInt(parts[5].trim());
                 String status = parts[6].trim();
                 
                 Employee club = new Employee(

                 );
                 
                 clubs.add(club);
             }
             
         } catch (IOException e) {
             System.out.println("Cannot read file: " + e.getMessage());
         } catch (NumberFormatException e) {
             System.out.println("Invalid budget format: " + e.getMessage());
         }
        
        return clubs;
    }
    
    /*
    * ########################################################
    * Save employees list to file
    * ########################################################
    */
    public void saveClubs(List<Employee> employeesToSave) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CLUB_FILE_PATH))) {
            for (Employee e : employeesToSave) {
                bw.write(
                        e.getId() + ", " + e.getName() + ", "
                        + e.getRole() + ", " + e.getBaseSalary() + ", "
                        + e.getWorkingDays() + ", " + e.getBonus() + ", "
                        + e.getStatus());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot write club file: " + e.getMessage());
        }
    }

    public String getClubFilePath() {
        return CLUB_FILE_PATH;
    }
}
