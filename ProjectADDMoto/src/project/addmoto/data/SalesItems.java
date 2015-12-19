package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SalesItems {
    
    private String itemCode;
    private String itemName;
    private int quantity;
    private double sellingPrice;
    private double extPrice;
    
    public SalesItems(String itemCode, String itemName, int quantity,
            double sellingPrice, double extPrice) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.extPrice = extPrice;
    }
}