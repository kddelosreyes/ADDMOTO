package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SalesItems {
    
    private int productID;
    private String itemCode;
    private String itemName;
    private int quantity;
    private double sellingPrice;
    private double extPrice;
    
    public SalesItems(int productID, String itemCode, String itemName, int quantity,
            double sellingPrice, double extPrice) {
        this.productID = productID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.extPrice = extPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getExtPrice() {
        return extPrice;
    }

    public void setExtPrice(double extPrice) {
        this.extPrice = extPrice;
    }
}