package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SoldItems {
    
    private int soldID;
    private int itemQuantity;
    private double totalPrice;
    private double totalProfit;
    private int productID;
    private int receiptID;
    
    public SoldItems(int soldID, int itemQuantity, double totalPrice,
            double totalProfit, int productID, int receiptID) {
        this.soldID = soldID;
        this.itemQuantity = itemQuantity;
        this.totalPrice = totalPrice;
        this.totalProfit = totalProfit;
        this.productID = productID;
        this.receiptID = receiptID;
    }

    public int getSoldID() {
        return soldID;
    }

    public void setSoldID(int soldID) {
        this.soldID = soldID;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }
}