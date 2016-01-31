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
public class Return {
    
    private String date;
    private int productID;
    private int qty;
    private int receiptID;
    private int sellerID;

    public Return(String date, int productID, int qty,
            int receiptID, int sellerID) {
        this.date = date;
        this.productID = productID;
        this.qty = qty;
        this.receiptID = receiptID;
        this.sellerID = sellerID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
}