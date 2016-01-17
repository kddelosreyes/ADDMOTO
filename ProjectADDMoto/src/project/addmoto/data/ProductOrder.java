/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ProductOrder {
    
    private int productID;
    private String supplierCode;
    private String product;
    private int quantity;
    private double unitPrice;
    private double subtotal;
    private int orderID;
    
    public ProductOrder(int productID, String supplierCode, String product,
            double unitPrice) {
        this.productID = productID;
        this.supplierCode = supplierCode;
        this.product = product;
        this.unitPrice = unitPrice;
    }
    
    public ProductOrder(String supplierCode, String product, int quantity,
            double unitPrice, double subtotal) {
        this.supplierCode = supplierCode;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "ProductOrder{" + "productID=" + productID + ", supplierCode=" + supplierCode + ", product=" + product + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", subtotal=" + subtotal + '}';
    }
}
