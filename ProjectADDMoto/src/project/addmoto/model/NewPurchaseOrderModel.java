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
import project.addmoto.data.Order;
import project.addmoto.data.ProductOrder;
import project.addmoto.data.Supplier;
import project.addmoto.data.SupplierProduct;
import project.addmoto.database.Database;
import project.addmoto.javasqlquerybuilder.Factory;
import project.addmoto.javasqlquerybuilder.Select;
import project.addmoto.javasqlquerybuilder.generic.FactoryImp;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class NewPurchaseOrderModel {
    
    private final Connection connection;
    
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    private Factory sqlFactory;
    
    public NewPurchaseOrderModel(final Connection connection) {
        this.connection = connection;
        sqlFactory = new FactoryImp();
        
        System.out.println("NewPurchaseOrderModel called.");
    }
    
    public ArrayList<Supplier> getSuppliers() {
        ArrayList<Supplier> supplierList = new ArrayList<>();
        
        try {
            query = "SELECT " + Database.SUPPLIER_ID + ", " + Database.SUPPLIER_NAME +
                    " FROM " + Database.SUPPLIER_TABLE + ";";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int supplierID = resultSet.getInt(Database.SUPPLIER_ID);
                String supplierName = resultSet.getString(Database.SUPPLIER_NAME);
                Supplier supplier = new Supplier(supplierID, supplierName);
                supplierList.add(supplier);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return supplierList;
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
    
    public int getMaxOrderID() {
        try {
            query = "select ifnull(max(" + Database.ORDER_ID + "), -1) from " + Database.ORDERS_TABLE + ";";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return -1;
    }
    
    public int insertOrder(Order order) {
        int row = 0;
        
        try {
            query = "INSERT INTO " + Database.ORDERS_TABLE + "(" + 
                    Database.ORDER_NO + ", " +
                    Database.ORDER_TIMESTAMP + ", " +
                    Database.ORDER_TOTAL_PRICE + ", " +
                    Database.ORDER_SUPPLIER_ID + ", " +
                    Database.ORDER_IS_PAID + ", " +
                    Database.ORDER_SELLER_ID + ", " +
                    Database.ORDER_STATUS + ", " + 
                    Database.ORDER_TARGET_DATE +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, order.getOrderNo());
            preparedStatement.setString(2, order.getOrderDatetime());
            preparedStatement.setDouble(3, order.getOrderTotalPrice());
            preparedStatement.setInt(4, order.getSupplierID());
            preparedStatement.setBoolean(5, order.isIsPaid());
            preparedStatement.setInt(6, order.getSellerID());
            preparedStatement.setString(7, order.getStatus());
            preparedStatement.setString(8, order.getTargetDate());
            
            row = preparedStatement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return row;
    }
    
    public int insertProductOrders(ArrayList<ProductOrder> orders) {
        int rows = 0;
        
        for(ProductOrder po : orders) {
            try {
                query = "INSERT INTO " + Database.ORDER_LINE_TABLE + " (" +
                        Database.ORDER_LINE_PRODUCT_ID + ", " +
                        Database.ORDER_LINE_ORDER_ID + ", " +
                        Database.ORDER_LINE_QUANTITY + ", " +
                        Database.ORDER_LINE_UNIT_PRICE + ", " +
                        Database.ORDER_LINE_TOTAL_PRICE +
                        ") VALUES(?, ?, ?, ?, ?)";
            
                preparedStatement = connection.prepareStatement(query);
                
                preparedStatement.setInt(1, po.getProductID());
                preparedStatement.setInt(2, po.getOrderID());
                preparedStatement.setInt(3, po.getQuantity());
                preparedStatement.setDouble(4, po.getUnitPrice());
                preparedStatement.setDouble(5, po.getSubtotal());
                
                rows += preparedStatement.executeUpdate();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        
        return rows;
    }
}
