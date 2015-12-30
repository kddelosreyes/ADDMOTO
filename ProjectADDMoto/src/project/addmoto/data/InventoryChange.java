package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class InventoryChange {
    
    private String timestamp;
    private int changeBefore;
    private int changeAfter;
    private int changeQty;
    private int productID;

    public InventoryChange(String timestamp, int changeBefore, int changeAfter, int changeQty, int productID) {
        this.timestamp = timestamp;
        this.changeBefore = changeBefore;
        this.changeAfter = changeAfter;
        this.changeQty = changeQty;
        this.productID = productID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getChangeBefore() {
        return changeBefore;
    }

    public void setChangeBefore(int changeBefore) {
        this.changeBefore = changeBefore;
    }

    public int getChangeAfter() {
        return changeAfter;
    }

    public void setChangeAfter(int changeAfter) {
        this.changeAfter = changeAfter;
    }

    public int getChangeQty() {
        return changeQty;
    }

    public void setChangeQty(int changeQty) {
        this.changeQty = changeQty;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    public String toString() {
        return "Timestamp: " + timestamp + "\n" +
                "Change Before: " + changeBefore + "\n" + 
                "Change After: " + changeAfter + "\n" +
                "Change Quantity: " + changeQty + "\n" +
                "Product ID: " + productID;
    }
}