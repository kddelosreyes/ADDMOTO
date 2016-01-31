/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.addmoto.model;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import project.addmoto.data.InventoryChange;
import project.addmoto.data.OrderLine;
import project.addmoto.data.OrderLineData;
import project.addmoto.data.Products;
import project.addmoto.database.Database;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class PurchaseOrderModel {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public PurchaseOrderModel(final Connection connection) {
        this.connection = connection;
        
        System.out.println("Purchase Order Model is called.");
    }
    
    public ArrayList<OrderLineData> getOrderLineList() {
        ArrayList<OrderLineData> orderList = new ArrayList<>();
        
        try {
            query = "SELECT A." + Database.ORDER_ID + ", A." + Database.ORDER_NO + ", A." + Database.ORDER_TIMESTAMP + 
                    ", S." + Database.SUPPLIER_NAME + ", A." + Database.ORDER_TOTAL_PRICE + ", SUM(B." + Database.ORDER_LINE_QUANTITY +
                    ") AS '" + Database.ORDER_TOTAL_QUANTITY + "', A." + Database.ORDER_IS_PAID + ", A." + Database.ORDER_STATUS +
                    ", A." + Database.ORDER_TARGET_DATE + ", A." + Database.ORDER_SELLER_ID + " FROM " + Database.ORDERS_TABLE +
                    " A JOIN " + Database.ORDER_LINE_TABLE + " B ON A." + Database.ORDER_ID + " = B." + Database.ORDER_LINE_ORDER_ID +
                    " JOIN " + Database.SUPPLIER_TABLE + " S ON S." + Database.SUPPLIER_ID + " = A." + Database.ORDER_SUPPLIER_ID +
                    " GROUP BY B." + Database.ORDER_LINE_ORDER_ID;
            
            System.out.println("Query: " + query);
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                int orderID = resultSet.getInt(Database.ORDER_ID);
                String orderNo = resultSet.getString(Database.ORDER_NO);
                String timestamp = resultSet.getString(Database.ORDER_TIMESTAMP);
                String supplierName = resultSet.getString(Database.SUPPLIER_NAME);
                double totalPrice = resultSet.getDouble(Database.ORDER_TOTAL_PRICE);
                int totalQty = resultSet.getInt(Database.ORDER_TOTAL_QUANTITY);
                boolean isPaid = resultSet.getBoolean(Database.ORDER_IS_PAID);
                String status = resultSet.getString(Database.ORDER_STATUS);
                String targetDate = resultSet.getString(Database.ORDER_TARGET_DATE);
                int sellerID = resultSet.getInt(Database.ORDER_SELLER_ID);
                
                OrderLineData orderLineData = new OrderLineData(
                        orderID,
                        orderNo,
                        timestamp,
                        supplierName,
                        totalPrice,
                        totalQty,
                        isPaid,
                        status,
                        targetDate,
                        sellerID
                );
                
                orderList.add(orderLineData);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return orderList;
    }
    
    public int updatePaid(int poID, boolean paid) {
        try {
            query = "UPDATE " + Database.ORDERS_TABLE + " SET " + Database.ORDER_IS_PAID +
                    " = ? WHERE " + Database.ORDER_ID + " = ?;";
            
            preparedStatement = connection.prepareCall(query);
            preparedStatement.setBoolean(1, paid);
            preparedStatement.setInt(2, poID);
            
            return preparedStatement.executeUpdate();
        } catch(Exception exc) {
            return 0;
        }
    }
    
    public int updateReceived(int poID, String received) {
        try {
            query = "UPDATE " + Database.ORDERS_TABLE + " SET " + Database.ORDER_STATUS +
                    " = ? WHERE " + Database.ORDER_ID + " = ?;";
            
            preparedStatement = connection.prepareCall(query);
            preparedStatement.setString(1, received);
            preparedStatement.setInt(2, poID);
            
            return preparedStatement.executeUpdate();
        } catch(Exception exc) {
            return 0;
        }
    }
    
    public ArrayList<OrderLine> getOrderProducts(int orderID) {
        ArrayList<OrderLine> orderProducts = new ArrayList<OrderLine>();
        
        try {
            query = "SELECT O.*, CONCAT(" + Database.PRODUCT_LINE_NAME + ", ' ', " + Database.PRODUCT_DESCRIPTION +
                    ", ' ', " + Database.PRODUCT_CHARACTERISTICS + ", ' ', " + Database.PRODUCT_MOTORS + ") AS '" + 
                    Database.ORDER_LINE_PRODUCT_NAME + "' FROM " + Database.ORDER_LINE_TABLE + " O JOIN " +
                    Database.PRODUCTS_TABLE + " P ON O." + Database.ORDER_LINE_PRODUCT_ID + " = P." + Database.PRODUCT_ID +
                    " JOIN " + Database.PRODUCT_LINE_TABLE + " PL ON P." + Database.PRODUCT_LINE_ID_FK + " = PL." +
                    Database.PRODUCT_LINE_ID + " WHERE " + Database.ORDER_ID + " = " + orderID + ";";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            while(resultSet.next()) {
                int orderLineID = resultSet.getInt(Database.ORDER_LINE_ID);
                int productID = resultSet.getInt(Database.PRODUCT_ID);
                int orderID_ = resultSet.getInt(Database.ORDER_ID);
                int quantity = resultSet.getInt(Database.ORDER_LINE_QUANTITY);
                double unitPrice = resultSet.getDouble(Database.ORDER_LINE_UNIT_PRICE);
                double totalPrice = resultSet.getDouble(Database.ORDER_LINE_TOTAL_PRICE);
                String productName = resultSet.getString(Database.ORDER_LINE_PRODUCT_NAME);
                
                orderProducts.add(
                        new OrderLine(
                                orderLineID,
                                productID,
                                orderID_,
                                quantity,
                                unitPrice,
                                totalPrice,
                                productName
                        )
                );
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        
        return orderProducts;
    }
    
    public Products getProduct(int productID) {
        Products product = null;

        try {
            query = "SELECT * FROM " + Database.PRODUCTS_TABLE +
                    " WHERE " + Database.PRODUCT_ID + " = " + productID + ";";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt(Database.PRODUCT_ID);
                String addmotoCode = resultSet.getString(Database.PRODUCT_ADDMOTO_CODE);
                String supplierCode = resultSet.getString(Database.PRODUCT_SUPPLIER_CODE);
                int quantity = resultSet.getInt(Database.PRODUCT_CURRENT_QUANTITY);
                double unitPrice = resultSet.getDouble(Database.PRODUCT_UNIT_PRICE);
                double sellingPrice = resultSet.getDouble(Database.PRODUCT_SELLING_PRICE);
                double profitMargin = resultSet.getDouble(Database.PRODUCT_PROFIT_MARGIN);
                int threshold = resultSet.getInt(Database.PRODUCT_THRESHOLD_COUNT);
                Image imagePicture = null;
                String description = resultSet.getString(Database.PRODUCT_DESCRIPTION);
                String characteristics = resultSet.getString(Database.PRODUCT_CHARACTERISTICS);
                String motors = resultSet.getString(Database.PRODUCT_MOTORS);
                int productLineID = resultSet.getInt(Database.PRODUCT_LINE_ID_FK);
                int supplierID = resultSet.getInt(Database.PRODUCT_SUPPLIER_ID_FK);

                product = new Products(id, addmotoCode, supplierCode,
                        quantity, unitPrice, sellingPrice,
                        profitMargin, threshold, imagePicture,
                        description, characteristics, motors,
                        productLineID, supplierID);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }

        return product;
    }
    
    public int insertChangeItems(ArrayList<InventoryChange> changeList) {
        int insertedValue = 0;

        for (InventoryChange change : changeList) {
            try {
                query = "INSERT INTO " + Database.INVENTORY_CHANGE_TABLE + " (" + Database.CHANGE_TIMESTAMP + ", " + Database.CHANGE_BEFORE + ", "
                        + Database.CHANGE_AFTER + ", " + Database.CHANGE_QTY + ", " + Database.CHANGE_PRODUCT_ID + ", TYPE)"
                        + " VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, change.getTimestamp());
                preparedStatement.setInt(2, change.getChangeBefore());
                preparedStatement.setInt(3, change.getChangeAfter());
                preparedStatement.setInt(4, change.getChangeQty());
                preparedStatement.setInt(5, change.getProductID());
                preparedStatement.setInt(6, 2);

                insertedValue += preparedStatement.executeUpdate();
                System.out.println("-> Inserting\n" + change.toString() + "\nValue: " + insertedValue);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        
        return insertedValue;
    }
    
    public int updateChangeItems(int itemID, int quantity) {
        int updatedValue = 0;
       
        try {
            query = "UPDATE " + Database.PRODUCTS_TABLE + " SET " + Database.PRODUCT_CURRENT_QUANTITY
                    + " = " + quantity + " WHERE "
                    + Database.PRODUCT_ID + " = " + itemID + " ;";
            statement = connection.createStatement();
            System.out.println(query);

            updatedValue += statement.executeUpdate(query);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return updatedValue;
    }
}
