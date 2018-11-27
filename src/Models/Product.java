package Models;


import Controllers.AlertController;

import java.io.File;

public class Product {

    private String productName;
    private String description;
    private int numberOfStock;
    private double price;
    private String imageFile;


    public Product(String productName, String description, int numberOfStock, double price, String imageFile) {
        setProductName(productName);
        setDescription(description);
        setNumberOfStock(numberOfStock);
        setPrice(price);
        setImageFile(imageFile);
    }


    /**
     * returns product name
     *
     * @return String
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the product name
     *
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
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the product description
     *
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
     *
     * @return Int
     */
    public int getNumberOfStock() {
        return numberOfStock;
    }

    /**
     * Sets the number of product stock
     *
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
     *
     * @return Double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product price
     *
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
     * returns product image
     *
     * @return File
     */
    public String getImageFile() {
        return imageFile;
    }

    /**
     * Sets the product image
     *
     * @param imageFile File
     */

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    /**
     *
     *returns Decreasing the number of stock by 1 for each selling
     * @return numberOfStock
     */
    public void sellUnit() {
    if(this.numberOfStock > 0 ){
         this.numberOfStock--;

    } else
        AlertController.alertError("There is no available item to sell.");
        throw new IllegalArgumentException("There is no available item to sell.");
    }

    /**
     *
     *returns new ordered String defined by Product Object Properties
     * @return String
     */
    @Override
    public String toString(){
        return "Product Name:" + productName + " Units In Stock:" + numberOfStock + " Price:" + price + "$";
    }
}
