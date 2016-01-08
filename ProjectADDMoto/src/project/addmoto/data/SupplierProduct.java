/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.data;

import java.io.Serializable;
import project.addmoto.annotation.Column;
import project.addmoto.annotation.Table;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierProduct implements Serializable {
    
    @Column(columnName = "P.productID")
    private int productID;
    
    @Column(columnName = "P.product_supplier_code")
    private String supplierCode;
    
    @Column(columnName = "P.product_addmoto_code")
    private String addmotoCode;
    
    @Column(columnName = "L.product_line_name")
    private String productLine;
    
    @Column(columnName = "P.product_description + P.product_characteristic + p.motors")
    private String description;
    
    @Column(columnName = "P.product_current_quantity")
    private int quantity;
    
    @Column(columnName = "P.product_threshold_count")
    private int threshold;
    
    @Column(columnName = "P.product_unit_price")
    private double unitPrice;
    
    @Column(columnName = "P.product_selling_price")
    private double sellingPrice;
    
    @Table(tableName = "SUPPLIER_TABLE S join PRODUCTS_TABLE P join PRODUCT_LINE_TABLE L")
    public SupplierProduct(
            int productID,
            String supplierCode,
            String addmotoCode,
            String productLine,
            String description,
            int quantity,
            int threshold,
            double unitPrice,
            double sellingPrice) {
        
        this.productID = productID;
        this.supplierCode = supplierCode;
        this.addmotoCode = addmotoCode;
        this.productLine = productLine;
        this.description = description;
        this.quantity = quantity;
        this.threshold = threshold;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getAddmotoCode() {
        return addmotoCode;
    }

    public void setAddmotoCode(String addmotoCode) {
        this.addmotoCode = addmotoCode;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
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

    @Override
    public String toString() {
        return "SupplierProduct{" + "productID=" + productID + ", supplierCode=" + supplierCode + ", addmotoCode=" + addmotoCode + ", productLine=" + productLine + ", description=" + description + ", quantity=" + quantity + ", threshold=" + threshold + ", unitPrice=" + unitPrice + ", sellingPrice=" + sellingPrice + '}';
    }
}