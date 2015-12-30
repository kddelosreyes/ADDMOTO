package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Receipt {
    
    private String receiptNo;
    private String transactionTimeStamp;
    private double totalPrice;
    private int sellerID;
    private double pricePaid;
    private boolean isFullyPaid;
    
    public Receipt(String receiptNo, String transactionTimeStamp,
            double totalPrice, int sellerID, double pricePaid,
            boolean isFullyPaid) {
        this.receiptNo = receiptNo;
        this.transactionTimeStamp = transactionTimeStamp;
        this.totalPrice = totalPrice;
        this.sellerID = sellerID;
        this.pricePaid = pricePaid;
        this.isFullyPaid = isFullyPaid;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
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

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public boolean isIsFullyPaid() {
        return isFullyPaid;
    }

    public void setIsFullyPaid(boolean isFullyPaid) {
        this.isFullyPaid = isFullyPaid;
    }
}