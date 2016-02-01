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
public class ReturnsData {
    
    private String returnDate;
    private String addmotoCode;
    private String itemName;
    private int quantity;
    private double sellingPrice;
    private double totalPrice;

    public ReturnsData(String returnDate, String addmotoCode, String itemName,
            int quantity, double sellingPrice, double totalPrice) {
        this.returnDate = returnDate;
        this.addmotoCode = addmotoCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.totalPrice = totalPrice;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getAddmotoCode() {
        return addmotoCode;
    }

    public void setAddmotoCode(String addmotoCode) {
        this.addmotoCode = addmotoCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ReturnsData{" + "returnDate=" + returnDate + ", addmotoCode=" + addmotoCode + ", itemName=" + itemName + ", quantity=" + quantity + ", sellingPrice=" + sellingPrice + ", totalPrice=" + totalPrice + '}';
    }
}
