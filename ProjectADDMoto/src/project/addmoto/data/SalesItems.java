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
    
    /**
     *
     * @param productID
     * @param itemCode
     * @param itemName
     * @param quantity
     * @param sellingPrice
     * @param extPrice
     */
    public SalesItems(int productID, String itemCode, String itemName, int quantity,
            double sellingPrice, double extPrice) {
        this.productID = productID;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.extPrice = extPrice;
    }

    /**
     *
     * @return The product ID of the items
     */
    public int getProductID() {
        return productID;
    }

    /**
     *
     * @param productID Sets the product ID of the item stored in the database
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     *
     * @return The ADD Moto code for the item
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     *
     * @param itemCode Sets the ADD Moto code for the item
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     *
     * @return The item name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     *
     * @param itemName Sets the name of the item
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     *
     * @return The current quantity of the item on the list
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity Sets a new quantity for the item on the list
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return The Selling Price of the item
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     *
     * @param sellingPrice Sets the selling price of the item
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     *
     * @return The Exit Price or total price for the item with respect to selling price and quantity
     */
    public double getExtPrice() {
        return extPrice;
    }

    /**
     *
     * @param extPrice Sets the exit price or total price for the item with respect to selling price and quantity
     */
    public void setExtPrice(double extPrice) {
        this.extPrice = extPrice;
    }
}