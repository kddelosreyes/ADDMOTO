package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class TopSelling {
    
    private String productName;
    private int quantity;
    
    public TopSelling(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TopSelling{" + "productName=" + productName + ", quantity=" + quantity + '}';
    }
}