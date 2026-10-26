/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author jso
 */
public class ElectricProduct extends Product {
    private int guaranty;
    private double voltage;
    private double power;

    public ElectricProduct() {
        super();
    }

    public ElectricProduct(String code, String name, String make,
                           double price, int guaranty,
                           double voltage, double power) {
        super(code, name, make, price);
        this.guaranty = guaranty;
        this.voltage = voltage;
        this.power = power;
    }

    public int getGuaranty() {
        return guaranty;
    }

    public void setGuaranty(int guaranty) {
        this.guaranty = guaranty;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public String getCategory() {
        return "E";
    }

    @Override
    public String getDetails() {
        return "Warranty: " + guaranty + " months, "
                + "Voltage: " + voltage + " V, "
                + "Power: " + power + " W";
    }
}
