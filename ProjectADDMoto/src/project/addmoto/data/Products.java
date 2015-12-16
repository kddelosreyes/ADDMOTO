package project.addmoto.data;

import java.awt.Image;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Products {
    
    private int productID;
    private String addmotoCode;
    private String supplierCode;
    private int currentQuantity;
    private double unitPrice;
    private double sellingPrice;
    private double profitMargin;
    private int thresholdCount;
    private Image imagePicture;
    private String description;
    private String characteristics;
    private String motors;
    private int productLineID;
    private int supplierID;
    
    public Products(int productID, String addmotoCode, String supplierCode,
            int currentQuantity, double unitPrice, double sellingPrice,
            double profitMargin, int thresholdCount, Image imagePicture,
            String description, String characteristics, String motors,
            int productLineID, int supplierID) {
        this.productID = productID;
        this.addmotoCode = addmotoCode;
        this.supplierCode = supplierCode;
        this.currentQuantity = currentQuantity;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
        this.profitMargin = profitMargin;
        this.thresholdCount = thresholdCount;
        this.imagePicture = imagePicture;
        this.description = description;
        this.characteristics = characteristics;
        this.productLineID = productLineID;
        this.motors = motors;
        this.supplierID = supplierID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getAddmotoCode() {
        return addmotoCode;
    }

    public void setAddmotoCode(String addmotoCode) {
        this.addmotoCode = addmotoCode;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public int getThresholdCount() {
        return thresholdCount;
    }

    public void setThresholdCount(int thresholdCount) {
        this.thresholdCount = thresholdCount;
    }

    public Image getImagePicture() {
        return imagePicture;
    }

    public void setImagePicture(Image imagePicture) {
        this.imagePicture = imagePicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }
    
    public String getMotors() {
        return motors;
    }

    public void setMotors(String motors) {
        this.motors = motors;
    }

    public int getProductLineID() {
        return productLineID;
    }

    public void setProductLineID(int productLineID) {
        this.productLineID = productLineID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
}