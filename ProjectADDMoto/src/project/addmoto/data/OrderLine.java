package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class OrderLine {
    
    private int orderLineID;
    private int productID;
    private int orderID;
    private int orderQuantity;
    private double unitPrice;
    private double totalPrice;
    
    public OrderLine(int orderLineID, int productID, int orderID,
            int orderQuantity, double unitPrice, double totalPrice) {
        this.orderLineID = orderLineID;
        this.productID = productID;
        this.orderID = orderID;
        this.orderQuantity = orderQuantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    public int getOrderLineID() {
        return orderLineID;
    }

    public void setOrderLineID(int orderLineID) {
        this.orderLineID = orderLineID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}