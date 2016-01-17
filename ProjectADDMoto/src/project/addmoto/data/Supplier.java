package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Supplier {
    
    private int supplierID;
    private String supplierName;
    private String supplierCity;
    private String supplierCountry;
    private String supplierAddress;
    private int supplierPostal;
    
    public Supplier(int supplierID, String supplierName) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
    }
    
    public Supplier(int supplierID, String supplierName, String supplierCity,
            String supplierCountry, String supplierAddress, int supplierPostal) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierCity = supplierCity;
        this.supplierCountry = supplierCountry;
        this.supplierAddress = supplierAddress;
        this.supplierPostal = supplierPostal;
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

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getSupplierCountry() {
        return supplierCountry;
    }

    public void setSupplierCountry(String supplierCountry) {
        this.supplierCountry = supplierCountry;
    }

    public int getSupplierPostal() {
        return supplierPostal;
    }

    public void setSupplierPostal(int supplierPostal) {
        this.supplierPostal = supplierPostal;
    }

    @Override
    public String toString() {
        return supplierName;
    }
}