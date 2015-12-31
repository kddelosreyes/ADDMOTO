/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import project.addmoto.data.SupplierSummary;
import project.addmoto.database.Database;
import project.addmoto.datacollections.SupplierSummaryList;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierModel {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public SupplierModel(final Connection connection) {
        this.connection = connection;
        
        System.out.println("Supplier Model is called");
    }
    
    public SupplierSummaryList getSuppliersWithContacts() {
        SupplierSummaryList supplierList = new SupplierSummaryList();
        
        try {
            query = "SELECT S.*, " +
                    "IFNULL(SU." + Database.SUPPLIER_CONTACTS_NAME + ", 'Not Applicable') AS 'CONTACT_NAME', " +
                    "IFNULL(SU." + Database.SUPPLIER_CONTACTS_CONTACT_NO + ", 'Not Applicable') AS 'CONTACT_NO', " +
                    "IFNULL(SU." + Database.SUPPLIER_CONTACTS_EMAIL + ", 'Not Applicable') AS 'CONTACT_EMAIL', " +
                    "IFNULL(SU." + Database.SUPPLIER_CONTACTS_POSITION + ", 'Not Applicable') AS 'CONTACT_POSITION', " +
                    "IFNULL(SU." + Database.SUPPLIER_IS_MAIN + ", 0) AS 'IS_MAIN' " +
                    "FROM " + Database.SUPPLIER_TABLE + " S LEFT JOIN " + Database.SUPPLIER_CONTACTS_TABLE + " SU ON " +
                    "S." + Database.SUPPLIER_ID + " = SU." + Database.SUPPLIER_SUPPLIER_ID_FK +
                    " WHERE SU." + Database.SUPPLIER_IS_MAIN + " = 1 OR SU." + Database.SUPPLIER_IS_MAIN + " IS NULL;";
            
            System.out.println("QUERY:\n" + query);
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int supplierID = resultSet.getInt(Database.SUPPLIER_ID);
                String supplierName = resultSet.getString(Database.SUPPLIER_NAME);
                String supplierCity = resultSet.getString(Database.SUPPLIER_CITY);
                String supplierCountry = resultSet.getString(Database.SUPPLIER_COUNTRY);
                String supplierAddress = resultSet.getString(Database.SUPPLIER_ADDRESS);
                int supplierPostal = resultSet.getInt(Database.SUPPLIER_POSTAL);
                String contactName = resultSet.getString(Database.CONTACT_NAME);
                String contactNo = resultSet.getString(Database.CONTACT_NO);
                String contactEmail = resultSet.getString(Database.CONTACT_EMAIL);
                String contactPosition = resultSet.getString(Database.CONTACT_POSITION);
                boolean isMain = resultSet.getBoolean(Database.CONTACT_IS_MAIN);
                
                SupplierSummary sSummary = new SupplierSummary(
                        supplierID, supplierName, supplierCity,
                        supplierCountry, supplierAddress, supplierPostal,
                        contactName, contactNo, contactEmail,
                        contactPosition, isMain);
                
                supplierList.add(sSummary);
            }
        } catch(Exception exc) {
            System.out.println(exc.getLocalizedMessage() + "\n" + exc.getMessage() + "\n" + exc.toString());
        }
        
        return supplierList;
    }
}