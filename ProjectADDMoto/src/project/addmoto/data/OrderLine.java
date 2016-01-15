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
    private String productName;
    
    public OrderLine(int orderLineID, int productID, int orderID,
            int orderQuantity, double unitPrice, double totalPrice,
            String productName) {
        this.orderLineID = orderLineID;
        this.productID = productID;
        this.orderID = orderID;
        this.orderQuantity = orderQuantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.productName = productName;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "OrderLine{" + "orderLineID=" + orderLineID + ", productID=" + productID + ", orderID=" + orderID + ", orderQuantity=" + orderQuantity + ", unitPrice=" + unitPrice + ", totalPrice=" + totalPrice + ", productName=" + productName + '}';
    }
}