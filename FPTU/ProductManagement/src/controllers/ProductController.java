/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import interfaces.IList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import models.FoodProduct;
import models.Product;
import utils.DataSource;

/**
 *
 * @author jso
 */
public class ProductController implements IList<Product> {
    
    private final DataSource context = DataSource.getInstance();

    @Override
    public Product Add(Product entity) {
        if (entity == null
                || entity.getCode() == null
                || entity.getCode().trim().isEmpty()
                || entity.getName() == null
                || entity.getName().trim().isEmpty()
                || entity.getMake() == null
                || entity.getMake().trim().isEmpty()
                || !Double.isFinite(entity.getPrice())
                || entity.getPrice() <= 0
                || FindById(entity.getCode()) != null) {
            return null;
        }

        // Validate dữ liệu riêng của từng loại sản phẩm
        if (entity instanceof FoodProduct) {
            FoodProduct food = (FoodProduct) entity;

            if (food.getProductDate() == null
                    || food.getExpiredDate() == null
                    || !food.getExpiredDate().isAfter(food.getProductDate())) {
                return null;
            }
        }

        context.productList().add(entity);
        context.markChanged();

        return entity;
    }

    @Override
    public Product Update(String id, Product entity) {
        Product old = FindById(id);

        if (old == null || entity == null
                || entity.getName() == null
                || entity.getName().trim().isEmpty()
                || entity.getMake() == null
                || entity.getMake().trim().isEmpty()
                || !Double.isFinite(entity.getPrice())
                || entity.getPrice() <= 0
                || !old.getCategory().equals(entity.getCategory())) {
            return null;
        }

        old.setName(entity.getName());
        old.setMake(entity.getMake());
        old.setPrice(entity.getPrice());

        if (old instanceof models.ElectricProduct
                && entity instanceof models.ElectricProduct) {
            models.ElectricProduct a = (models.ElectricProduct) old;
            models.ElectricProduct b = (models.ElectricProduct) entity;

            if (b.getGuaranty() < 0
                    || !Double.isFinite(b.getVoltage())
                    || b.getVoltage() <= 0
                    || !Double.isFinite(b.getPower())
                    || b.getPower() < 0) {
                return null;
            }

            a.setGuaranty(b.getGuaranty());
            a.setVoltage(b.getVoltage());
            a.setPower(b.getPower());

        } else if (old instanceof models.CeramicProduct
                && entity instanceof models.CeramicProduct) {
            models.CeramicProduct a = (models.CeramicProduct) old;
            models.CeramicProduct b = (models.CeramicProduct) entity;

            if (b.getTypeDetail() == null
                    || b.getTypeDetail().trim().isEmpty()) {
                return null;
            }

            a.setTypeDetail(b.getTypeDetail());

        } else if (old instanceof models.FoodProduct
                && entity instanceof models.FoodProduct) {
            models.FoodProduct a = (models.FoodProduct) old;
            models.FoodProduct b = (models.FoodProduct) entity;

            if (b.getProductDate() == null
                    || b.getExpiredDate() == null
                    || b.getExpiredDate().isBefore(b.getProductDate())) {
                return null;
            }

            a.setProductDate(b.getProductDate());
            a.setExpiredDate(b.getExpiredDate());
        }

        context.markChanged();
        return old;
    }

    @Override
    public boolean Delete(String id) {
        Product product = FindById(id);

        if (product == null) {
            return false;
        }

        context.productList().remove(product);
        context.markChanged();
        return true;
    }

    @Override
    public List<Product> ListAll() {
        return new ArrayList<>(context.productList());
    }

    @Override
    public Product FindById(String id) {
        if (id == null) {
            return null;
        }

        for (Product p : context.productList()) {
            if (p.getCode().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }

        return null;
    }

    public List<Product> Search(String keyword) {
        List<Product> result = new ArrayList<>();

        if (keyword == null) {
            return result;
        }

        String key = keyword.trim().toLowerCase(Locale.ROOT);

        for (Product p : context.productList()) {
            if (p.getCode().toLowerCase(Locale.ROOT).contains(key)
                    || p.getName().toLowerCase(Locale.ROOT).contains(key)
                    || p.getMake().toLowerCase(Locale.ROOT).contains(key)) {
                result.add(p);
            }
        }

        return result;
    }

    public List<Product> FindByCategory(String category) {
        List<Product> result = new ArrayList<>();

        if (category == null) {
            return result;
        }

        for (Product p : context.productList()) {
            if (p.getCategory().equalsIgnoreCase(category.trim())) {
                result.add(p);
            }
        }

        return result;
    }

    @Override
    public void display() {
        displayProducts(ListAll());
    }

    public void displayProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.println(
            "+-------+------------+----------------------+"
            + "-----------------+--------------+-------------------------------------+"
        );
        System.out.printf(
            "| %-5s | %-10s | %-20s | %-15s | %12s | %-35s |%n",
            "Type", "Code", "Name", "Make", "Price", "Details"
        );
        System.out.println(
            "+-------+------------+----------------------+"
            + "-----------------+--------------+-------------------------------------+"
        );

        for (Product p : products) {
            System.out.print(p.display());
        }

        System.out.println(
            "+-------+------------+----------------------+"
            + "-----------------+--------------+-------------------------------------+"
        );
    }

    @Override
    public void save() {
        if (context.saveProduct(context.productList())) {
            System.out.println("Data saved successfully.");
        } else {
            System.out.println("Failed to save data.");
        }
    }

    @Override
    public boolean hasUnsavedChanges() {
        return context.isDataChanged();
    }
    
}
