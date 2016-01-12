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
import project.addmoto.data.OrderLineData;
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
}
