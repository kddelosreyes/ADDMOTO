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
public class OrderLineData {
    
    private int orderID;
    private String orderNo;
    private String timestamp;
    private String supplier;
    private double orderTotalPrice;
    private int totalQuantity;
    private boolean isPaid;
    private String status;
    private String targetDate;
    private int sellerID;

    public OrderLineData(int orderID, String orderNo, String timestamp,
            String supplier, double orderTotalPrice, int totalQuantity,
            boolean isPaid, String status, String targetDate,
            int sellerID) {
        this.orderID = orderID;
        this.orderNo = orderNo;
        this.timestamp = timestamp;
        this.supplier = supplier;
        this.orderTotalPrice = orderTotalPrice;
        this.totalQuantity = totalQuantity;
        this.isPaid = isPaid;
        this.status = status;
        this.targetDate = targetDate;
        this.sellerID = sellerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    @Override
    public String toString() {
        return "OrderLineData{" + "orderID=" + orderID + ", orderNo=" + orderNo + ", timestamp=" + timestamp + ", supplier=" + supplier + ", orderTotalPrice=" + orderTotalPrice + ", totalQuantity=" + totalQuantity + ", isPaid=" + isPaid + ", status=" + status + ", targetDate=" + targetDate + ", sellerID=" + sellerID + '}';
    }
}