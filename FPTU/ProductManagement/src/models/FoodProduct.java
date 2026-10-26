/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jso
 */
public class FoodProduct extends Product {
    private LocalDate productDate;
    private LocalDate expiredDate;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public FoodProduct() {
        super();
    }

    public FoodProduct(String code, String name, String make,
                       double price, LocalDate productDate,
                       LocalDate expiredDate) {
        super(code, name, make, price);
        this.productDate = productDate;
        this.expiredDate = expiredDate;
    }

    public LocalDate getProductDate() {
        return productDate;
    }

    public void setProductDate(LocalDate productDate) {
        this.productDate = productDate;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String getCategory() {
        return "F";
    }

    @Override
    public String getDetails() {
        return "MFG: " + productDate.format(FORMATTER)
                + ", EXP: " + expiredDate.format(FORMATTER);
    }
}
