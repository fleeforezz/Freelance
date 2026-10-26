/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author jso
 */
public class CeramicProduct extends Product {
    private String type;

    public CeramicProduct() {
        super();
    }

    public CeramicProduct(String code, String name, String make,
                          double price, String type) {
        super(code, name, make, price);
        this.type = type;
    }

    public String getTypeDetail() {
        return type;
    }

    public void setTypeDetail(String type) {
        this.type = type;
    }

    @Override
    public String getCategory() {
        return "C";
    }

    @Override
    public String getDetails() {
        return "Type: " + type;
    }
}
