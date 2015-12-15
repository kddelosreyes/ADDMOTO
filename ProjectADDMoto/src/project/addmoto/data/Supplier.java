package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Supplier {
    
    private int supplierID;
    private String supplierName;
    private String supplierAddress;
    
    public Supplier(int supplierID, String supplierName, String supplierAddress) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
}