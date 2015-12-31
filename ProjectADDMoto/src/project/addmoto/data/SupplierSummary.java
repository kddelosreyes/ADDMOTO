package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierSummary {
    
    private int supplierID;
    private String supplierName;
    private String supplierCity;
    private String supplierCountry;
    private String supplierAddress;
    private int supplierPostal;
    private String contactName;
    private String contactNo;
    private String contactEmail;
    private String contactPosition;
    private boolean isMain;

    public SupplierSummary(int supplierID, String supplierName, String supplierCity,
            String supplierCountry, String supplierAddress, int supplierPostal,
            String contactName, String contactNo, String contactEmail,
            String contactPosition, boolean isMain) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierCity = supplierCity;
        this.supplierCountry = supplierCountry;
        this.supplierAddress = supplierAddress;
        this.supplierPostal = supplierPostal;
        this.contactName = contactName;
        this.contactNo = contactNo;
        this.contactEmail = contactEmail;
        this.contactPosition = contactPosition;
        this.isMain = isMain;
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

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public int getSupplierPostal() {
        return supplierPostal;
    }

    public void setSupplierPostal(int supplierPostal) {
        this.supplierPostal = supplierPostal;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public boolean isIsMain() {
        return isMain;
    }

    public void setIsMain(boolean isMain) {
        this.isMain = isMain;
    }

    @Override
    public String toString() {
        return "SupplierDetails{" +
                "supplierID=" + supplierID +
                ", supplierName=" + supplierName +
                ", supplierCity=" + supplierCity +
                ", supplierCountry=" + supplierCountry +
                ", supplierAddress=" + supplierAddress +
                ", supplierPostal=" + supplierPostal +
                ", contactName=" + contactName +
                ", contactNo=" + contactNo +
                ", contactEmail=" + contactEmail +
                ", contactPosition=" + contactPosition +
                ", isMain=" + isMain +
                '}';
    }
    
}