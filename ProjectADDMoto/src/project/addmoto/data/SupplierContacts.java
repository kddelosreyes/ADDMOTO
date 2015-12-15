package project.addmoto.data;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierContacts {
    
    private int supplierContactID;
    private String contactName;
    private String contactNumber;
    private int supplierID;
    
    public SupplierContacts(int supplierContactID, String contactName, String contactNumber,
            int supplierID) {
        this.supplierContactID = supplierContactID;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.supplierID = supplierID;
    }

    public int getSupplierContactID() {
        return supplierContactID;
    }

    public void setSupplierContactID(int supplierContactID) {
        this.supplierContactID = supplierContactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
}