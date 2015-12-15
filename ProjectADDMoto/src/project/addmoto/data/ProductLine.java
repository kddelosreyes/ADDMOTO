package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ProductLine {

    private int productLineID;
    private String productLineName;
    
    public ProductLine(int productLineID, String productLineName) {
        this.productLineID = productLineID;
        this.productLineName = productLineName;
    }

    public int getProductLineID() {
        return productLineID;
    }

    public void setProductLineID(int productLineID) {
        this.productLineID = productLineID;
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }
}