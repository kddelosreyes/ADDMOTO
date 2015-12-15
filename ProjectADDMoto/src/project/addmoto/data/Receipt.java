package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Receipt {
    
    private int receiptID;
    private String transactionTimeStamp;
    private double totalPrice;
    private int sellerID;
    
    public Receipt(int receiptID, String transactionTimeStamp, double totalPrice,
            int sellerID) {
        this.receiptID = receiptID;
        this.transactionTimeStamp = transactionTimeStamp;
        this.totalPrice = totalPrice;
        this.sellerID = sellerID;
    }

    public int getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    public String getTransactionTimeStamp() {
        return transactionTimeStamp;
    }

    public void setTransactionTimeStamp(String transactionTimeStamp) {
        this.transactionTimeStamp = transactionTimeStamp;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }
}