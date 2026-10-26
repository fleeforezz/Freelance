/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import controllers.ProductController;
import java.time.LocalDate;
import java.util.Collections;
import models.Product;
import models.ElectricProduct;
import models.CeramicProduct;
import models.FoodProduct;
import utils.Inputter;

/**
 *
 * @author jso
 */
public class Main {

    private static final ProductController controller
            = new ProductController();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Display all products");
            System.out.println("2. Add a new product");
            System.out.println("3. Update product");
            System.out.println("4. Remove product");
            System.out.println("5. Search product");
            System.out.println("6. Search by category");
            System.out.println("7. Exit");

            int choice = Inputter.getInt("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1:
                    controller.display();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchProduct();
                    break;
                case 6:
                    searchByCategory();
                    break;
                case 7:
                    if (controller.hasUnsavedChanges()) {
                        controller.save();
                    }
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addProduct() {
        System.out.println("\n--- ADD PRODUCT ---");

        String category = getCategory();
        String code = Inputter.getString("Enter product code: ");

        if (controller.FindById(code) != null) {
            System.out.println("Product code already exists!");
            return;
        }

        Product product = readProduct(category, code);

        if (controller.Add(product) != null) {
            System.out.println("Product added successfully!");

            if (Inputter.confirm("Save to file now?")) {
                controller.save();
            }
        } else {
            System.out.println("Cannot add product. Check the input.");
        }
    }

    private static void updateProduct() {
        System.out.println("\n--- UPDATE PRODUCT ---");

        String code = Inputter.getString("Enter product code: ");
        Product old = controller.FindById(code);

        if (old == null) {
            System.out.println("Product not found!");
            return;
        }

        System.out.println("Current product:");
        controller.displayProducts(Collections.singletonList(old));

        System.out.println("Enter the new information.");
        System.out.println("The product code and category remain unchanged.");

        Product updated = readProduct(old.getCategory(), old.getCode());

        if (controller.Update(code, updated) != null) {
            System.out.println("Product updated successfully!");

            if (Inputter.confirm("Save to file now?")) {
                controller.save();
            }
        } else {
            System.out.println("Update failed. Check the input.");
        }
    }

    private static void deleteProduct() {
        System.out.println("\n--- DELETE PRODUCT ---");

        String code = Inputter.getString("Enter product code: ");
        Product product = controller.FindById(code);

        if (product == null) {
            System.out.println("Product not found!");
            return;
        }

        controller.displayProducts(Collections.singletonList(product));

        if (Inputter.confirm("Are you sure you want to delete this product?")) {
            if (controller.Delete(code)) {
                System.out.println("Product deleted successfully!");

                if (Inputter.confirm("Save to file now?")) {
                    controller.save();
                }
            }
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void searchProduct() {
        System.out.println("\n--- SEARCH PRODUCT ---");

        String keyword = Inputter.getString(
                "Enter code, name, or manufacturer: ");

        controller.displayProducts(controller.Search(keyword));
    }

    private static void searchByCategory() {
        System.out.println("\n--- SEARCH BY CATEGORY ---");
        System.out.println("E - Electric product");
        System.out.println("C - Ceramic product");
        System.out.println("F - Food product");

        String category = getCategory();

        controller.displayProducts(
                controller.FindByCategory(category));
    }

    private static String getCategory() {
        while (true) {
            String category = Inputter.getString(
                    "Enter category (E/C/F): ").toUpperCase();

            if (category.equals("E")
                    || category.equals("C")
                    || category.equals("F")) {
                return category;
            }

            System.out.println("Invalid category. Choose E, C, or F.");
        }
    }

    private static Product readProduct(String category, String code) {
        String name = Inputter.getString("Enter product name: ");
        String make = Inputter.getString("Enter manufacturer: ");
        double price = Inputter.getDouble("Enter price: ", 0);

        switch (category) {
            case "E": {
                int guaranty = Inputter.getInt(
                        "Enter warranty (months): ", 0, 1200);
                double voltage = Inputter.getDouble(
                        "Enter voltage (V): ", 0.000001);
                double power = Inputter.getDouble(
                        "Enter power (W): ", 0);

                return new ElectricProduct(
                        code, name, make, price,
                        guaranty, voltage, power);
            }

            case "C": {
                String type = Inputter.getString(
                        "Enter ceramic type: ");

                return new CeramicProduct(
                        code, name, make, price, type);
            }

            case "F": {
                LocalDate productDate
                        = Inputter.getDate("Enter manufacturing date");

                LocalDate expiredDate;

                while (true) {
                    expiredDate
                            = Inputter.getDate("Enter expiration date");

                    if (expiredDate.isAfter(productDate)) {
                        break;
                    }

                    System.out.println(
                            "Expiration date must be AFTER manufacturing date!"
                    );
                }

                return new FoodProduct(
                        code, name, make, price,
                        productDate, expiredDate
                );
            }

            default:
                throw new IllegalArgumentException(
                        "Unsupported product category.");
        }
    }
}
