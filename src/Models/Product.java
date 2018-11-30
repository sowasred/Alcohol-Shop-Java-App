package Models;


import Controllers.AlertController;

import java.io.File;

public class Product {

    private String productName;
    private String description;
    private int numberOfStock;
    private double price;
    private File imageFile;
    private String category;


    public Product(String productName, String description, int numberOfStock, double price, File imageFile, String category) {
        setProductName(productName);
        setDescription(description);
        setNumberOfStock(numberOfStock);
        setPrice(price);
        setImageFile(imageFile);
        setCategory(category);
    }
    /**
     * returns product image
     * @return File
     */
    public File getImageFile() {
        return imageFile;
    }

    /**
     * Sets the product image
     * @param imageFile File
     */
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    /**
     * returns product category
     * @return String
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the product category
     * @param category String
     */
    public void setCategory(String category) {
        this.category = category;
    }

    public Product(String productName, String description, int numberOfStock, double price, String imageFile) {

    }

    /**
     * returns product name
     * @return String
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name
     * @param productName String
     */
    public void setProductName(String productName) {
        if (!productName.isEmpty())
            this.productName = productName;
        else {
            AlertController.alertError("Product name cannot be empty");
            throw new IllegalArgumentException("Product name is supposed to be filled.");
        }
    }

    /**
     * returns description of product
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the product description
     * @param description String
     */

    public void setDescription(String description) {
        if (!description.isEmpty())
            this.description = description;
        else {
            AlertController.alertError("Description cannot be empty");
            throw new IllegalArgumentException("Product description is supposed to be filled.");
        }
    }

    /**
     * returns number of stock for each product
     * @return Int
     */
    public int getNumberOfStock() {
        return numberOfStock;
    }

    /**
     * Sets the number of product stock
     * @param numberOfStock int
     */
    public void setNumberOfStock(int numberOfStock) {
        if (this.numberOfStock >= 0)
            this.numberOfStock = numberOfStock;
        else {
            AlertController.alertError("Number Of product stock cannot be minus value.");
            throw new IllegalArgumentException("Number Of product stock is supposed to be filled according to restriction.");
        }
    }
    /**
     * returns product price
     * @return Double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product price
     * @param price double
     */

    public void setPrice(double price) {
        if (this.price >= 0)
            this.price = price;
        else {
            AlertController.alertError("Price cannot be 0 or minus value.");
            throw new IllegalArgumentException("Product price is supposed to be filled according to restriction.");
        }
    }
    /**
     *returns new ordered String defined by Product Object Properties
     * @return String
     */
    @Override
    public String toString(){


        return String.format("%s \tNumber Of Stock:%d \t%.2f$", productName,numberOfStock,price);

    }
}
