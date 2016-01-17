package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Order {
    
    private int orderID;
    private String orderNo;
    private String orderDatetime;
    private double orderTotalPrice;
    private int supplierID;
    private boolean isPaid;
    private int sellerID;
    private String status;
    private String targetDate;

    public Order(String orderNo, String orderDatetime, double orderTotalPrice,
            int supplierID, boolean isPaid, int sellerID,
            String status, String targetDate) {
        this.orderNo = orderNo;
        this.orderDatetime = orderDatetime;
        this.orderTotalPrice = orderTotalPrice;
        this.supplierID = supplierID;
        this.isPaid = isPaid;
        this.sellerID = sellerID;
        this.status = status;
        this.targetDate = targetDate;
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

    public String getOrderDatetime() {
        return orderDatetime;
    }

    public void setOrderDatetime(String orderDatetime) {
        this.orderDatetime = orderDatetime;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
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

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", orderNo=" + orderNo + ", orderDatetime=" + orderDatetime + ", orderTotalPrice=" + orderTotalPrice + ", supplierID=" + supplierID + ", isPaid=" + isPaid + ", sellerID=" + sellerID + ", status=" + status + ", targetDate=" + targetDate + '}';
    }
}