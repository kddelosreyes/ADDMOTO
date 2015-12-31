package project.addmoto.datacollections;

import java.util.ArrayList;
import project.addmoto.data.SupplierSummary;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierSummaryList extends ArrayList<SupplierSummary> {
    
    public SupplierSummary getSupplierDetailsWithCompanyName(String supplierName) {
        for(SupplierSummary sDetails : this) {
            if(sDetails.getSupplierName().equals(supplierName)) {
                return sDetails;
            }
        }
        return null;
    }
    
    public SupplierSummary getSupplierDetailsWithContactPerson(String contactName) {
        for(SupplierSummary sDetails : this) {
            if(sDetails.getContactName().equals(contactName)) {
                return sDetails;
            }
        }
        return null;
    }
}