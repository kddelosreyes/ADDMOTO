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
import project.addmoto.data.Supplier;
import project.addmoto.database.Database;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class AddEditSupplierModel {
   
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public AddEditSupplierModel(final Connection connection) {
        this.connection = connection;
        
        System.out.println("Add Edit Supplier Model called.");
    }
    
    public int insertSupplier(Supplier supplier) {
        try {
            query = "INSERT INTO " + Database.SUPPLIER_TABLE + " (" +
                    Database.SUPPLIER_NAME + ", " + Database.SUPPLIER_CITY + ", " + Database.SUPPLIER_COUNTRY +
                    ", " + Database.SUPPLIER_ADDRESS + ", " + Database.SUPPLIER_POSTAL +
                    ") VALUES (?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getSupplierCity());
            preparedStatement.setString(3, supplier.getSupplierCountry());
            preparedStatement.setString(4, supplier.getSupplierAddress());
            preparedStatement.setInt(5, supplier.getSupplierPostal());
            
            return preparedStatement.executeUpdate();
        } catch(Exception e) {
            return 0;
        }
    }
    
    public int updateSupplier(Supplier supplier) {
        try {
            query = "UPDATE " + Database.SUPPLIER_TABLE + " SET " +
                    Database.SUPPLIER_NAME + " = ?, " + Database.SUPPLIER_CITY + " = ?, " + Database.SUPPLIER_COUNTRY +
                    " = ?, " + Database.SUPPLIER_ADDRESS + " = ?, " + Database.SUPPLIER_POSTAL +
                    " = ? WHERE " + Database.SUPPLIER_ID + " = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getSupplierCity());
            preparedStatement.setString(3, supplier.getSupplierCountry());
            preparedStatement.setString(4, supplier.getSupplierAddress());
            preparedStatement.setInt(5, supplier.getSupplierPostal());
            preparedStatement.setInt(6, supplier.getSupplierID());
            
            return preparedStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}