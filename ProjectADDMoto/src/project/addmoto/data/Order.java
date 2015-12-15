package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Order {
    
    private int orderID;
    private String orderDatetime;
    private double orderTotalPrice;
    private int supplierID;
    
    public Order(int orderID, String orderDatetime, double orderTotalPrice,
            int supplierID) {
        this.orderID = orderID;
        this.orderDatetime = orderDatetime;
        this.orderTotalPrice = orderTotalPrice;
        this.supplierID = supplierID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
}