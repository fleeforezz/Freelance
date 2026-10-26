/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author jso
 */
public class Product {
    private String code;
    private String name;
    private String make;
    private double price;

    public Product() {
    }

    public Product(String code, String name, String make, double price) {
        this.code = code;
        this.name = name;
        this.make = make;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return "P";
    }

    public String getDetails() {
        return "";
    }

    public String display() {
        return String.format(
            "| %-5s | %-10s | %-20s | %-15s | %12.2f | %-35s |%n",
            getCategory(), code, name, make, price, getDetails()
        );
    }

    @Override
    public String toString() {
        return code + " - " + name + " (" + getCategory() + ")";
    }
}
