/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.Product;
import models.ElectricProduct;
import models.CeramicProduct;
import models.FoodProduct;

/**
 *
 * @author jso
 */
public class DataSource {
    private static DataSource instance;

    private static final Path PRODUCT_FILE_PATH =
            Paths.get("src", "data", "product.txt");

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private List<Product> products;
    private boolean dataChanged;

    private DataSource() {
        products = null;
        dataChanged = false;
    }

    public static synchronized DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    public synchronized List<Product> productList() {
        if (products == null) {
            products = loadProducts();
        }
        return products;
    }

    public void markChanged() {
        dataChanged = true;
    }

    public boolean isDataChanged() {
        return dataChanged;
    }

    public boolean reloadAll() {
        List<Product> loaded = loadProducts();

        if (loaded == null) {
            return false;
        }

        products = loaded;
        dataChanged = false;
        return true;
    }

    public List<Product> loadProducts() {
        List<Product> result = new ArrayList<>();

        if (!Files.exists(PRODUCT_FILE_PATH)) {
            try {
                Path parent = PRODUCT_FILE_PATH.getParent();
                if (parent != null) {
                    Files.createDirectories(parent);
                }
                Files.createFile(PRODUCT_FILE_PATH);
            } catch (IOException e) {
                System.out.println("Cannot create data file: "
                        + e.getMessage());
                return null;
            }
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(PRODUCT_FILE_PATH.toFile()))) {

            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    String[] p = line.split(",", -1);

                    for (int i = 0; i < p.length; i++) {
                        p[i] = p[i].trim();
                    }

                    Product product;

                    switch (p[0].toUpperCase()) {
                        case "E":
                            if (p.length != 8) {
                                throw new IllegalArgumentException(
                                        "Electric product needs 8 fields");
                            }

                            product = new ElectricProduct(
                                p[1], p[2], p[3],
                                Double.parseDouble(p[4]),
                                Integer.parseInt(p[5]),
                                Double.parseDouble(p[6]),
                                Double.parseDouble(p[7])
                            );
                            break;

                        case "C":
                            if (p.length != 6) {
                                throw new IllegalArgumentException(
                                        "Ceramic product needs 6 fields");
                            }

                            product = new CeramicProduct(
                                p[1], p[2], p[3],
                                Double.parseDouble(p[4]),
                                p[5]
                            );
                            break;

                        case "F":
                            if (p.length != 7) {
                                throw new IllegalArgumentException(
                                        "Food product needs 7 fields");
                            }

                            product = new FoodProduct(
                                p[1], p[2], p[3],
                                Double.parseDouble(p[4]),
                                LocalDate.parse(p[5], FORMATTER),
                                LocalDate.parse(p[6], FORMATTER)
                            );
                            break;

                        default:
                            throw new IllegalArgumentException(
                                    "Unknown category: " + p[0]);
                    }

                    result.add(product);

                } catch (RuntimeException e) {
                    System.out.println("Invalid record at line "
                            + lineNumber + ": " + e.getMessage());
                }
            }

            return result;

        } catch (IOException e) {
            System.out.println("Cannot read product file: "
                    + e.getMessage());
            return null;
        }
    }

    public boolean saveProduct(List<Product> productToSave) {
        if (productToSave == null) {
            return false;
        }

        try {
            Path parent = PRODUCT_FILE_PATH.getParent();

            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(PRODUCT_FILE_PATH.toFile()))) {

                for (Product p : productToSave) {
                    String line;

                    if (p instanceof ElectricProduct) {
                        ElectricProduct e = (ElectricProduct) p;

                        line = String.join(", ",
                            "E",
                            e.getCode(),
                            e.getName(),
                            e.getMake(),
                            Double.toString(e.getPrice()),
                            Integer.toString(e.getGuaranty()),
                            Double.toString(e.getVoltage()),
                            Double.toString(e.getPower())
                        );

                    } else if (p instanceof CeramicProduct) {
                        CeramicProduct c = (CeramicProduct) p;

                        line = String.join(", ",
                            "C",
                            c.getCode(),
                            c.getName(),
                            c.getMake(),
                            Double.toString(c.getPrice()),
                            c.getTypeDetail()
                        );

                    } else if (p instanceof FoodProduct) {
                        FoodProduct f = (FoodProduct) p;

                        line = String.join(", ",
                            "F",
                            f.getCode(),
                            f.getName(),
                            f.getMake(),
                            Double.toString(f.getPrice()),
                            f.getProductDate().format(FORMATTER),
                            f.getExpiredDate().format(FORMATTER)
                        );

                    } else {
                        continue;
                    }

                    bw.write(line);
                    bw.newLine();
                }
            }

            dataChanged = false;
            return true;

        } catch (IOException | RuntimeException e) {
            System.out.println("Cannot save product file: "
                    + e.getMessage());
            return false;
        }
    }

    public String getProductFilePath() {
        return PRODUCT_FILE_PATH.toString();
    }
}
