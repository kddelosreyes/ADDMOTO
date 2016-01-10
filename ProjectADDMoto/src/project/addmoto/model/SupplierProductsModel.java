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
import java.util.ArrayList;
import project.addmoto.data.SupplierProduct;
import project.addmoto.database.Database;
import project.addmoto.javasqlquerybuilder.Factory;
import project.addmoto.javasqlquerybuilder.Select;
import project.addmoto.javasqlquerybuilder.generic.FactoryImp;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class SupplierProductsModel {
 
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    private final Factory sqlFactory;
    
    public SupplierProductsModel(final Connection connection) {
        this.connection = connection;
        sqlFactory = new FactoryImp();
        
        System.out.println("Supplier Products Model is called.");
    }
    
    public ArrayList<SupplierProduct> getSupplierProducts(int supplierID) {
        ArrayList<SupplierProduct> supplierProductList = new ArrayList<SupplierProduct>();
        
        Select selectStatement = sqlFactory.newSelectQuery();
        selectStatement.select(
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_ID),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_SUPPLIER_CODE),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_ADDMOTO_CODE),
                sqlFactory.newQualifiedField(Database.PRODUCT_LINE_TABLE, Database.PRODUCT_LINE_NAME),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_DESCRIPTION),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_CHARACTERISTICS),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_MOTORS),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_CURRENT_QUANTITY),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_THRESHOLD_COUNT),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_UNIT_PRICE),
                sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_SELLING_PRICE))
                .from(Database.PRODUCTS_TABLE)
                .join(Database.PRODUCT_LINE_TABLE,
                        sqlFactory.newQualifiedField(Database.PRODUCTS_TABLE, Database.PRODUCT_LINE_ID_FK),
                        sqlFactory.newQualifiedField(Database.PRODUCT_LINE_TABLE, Database.PRODUCT_LINE_ID))
                .where()
                .where(sqlFactory.newStdField(Database.PRODUCT_SUPPLIER_ID_FK), ":supplierID");
        
        try {
            preparedStatement = connection.prepareStatement(selectStatement.getQueryString());
            preparedStatement.setInt(selectStatement.getPlaceholderIndex(":supplierID"), supplierID);
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                int productID = resultSet.getInt(Database.PRODUCT_ID);
                String supplierCode = resultSet.getString(Database.PRODUCT_SUPPLIER_CODE);
                String addmotoCode = resultSet.getString(Database.PRODUCT_ADDMOTO_CODE);
                String productLine = resultSet.getString(Database.PRODUCT_LINE_NAME);
                String description = resultSet.getString(Database.PRODUCT_DESCRIPTION);
                String characteristics = resultSet.getString(Database.PRODUCT_CHARACTERISTICS);
                String motors = resultSet.getString(Database.PRODUCT_MOTORS);
                int quantity = resultSet.getInt(Database.PRODUCT_CURRENT_QUANTITY);
                int threshold = resultSet.getInt(Database.PRODUCT_THRESHOLD_COUNT);
                double unitPrice = resultSet.getDouble(Database.PRODUCT_UNIT_PRICE);
                double sellingPrice = resultSet.getDouble(Database.PRODUCT_SELLING_PRICE);
                
                SupplierProduct supplierProduct = new SupplierProduct(
                        productID,
                        supplierCode,
                        addmotoCode,
                        productLine,
                        (description + " " + characteristics + " " + motors).replaceAll("\\s+", " ").trim(),
                        quantity,
                        threshold,
                        unitPrice,
                        sellingPrice
                );
                System.out.println(supplierProduct.toString());
                supplierProductList.add(supplierProduct);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return supplierProductList;
    }
}