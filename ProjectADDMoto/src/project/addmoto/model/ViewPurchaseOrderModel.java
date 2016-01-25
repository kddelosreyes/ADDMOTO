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
import project.addmoto.data.OrderLine;
import project.addmoto.database.Database;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class ViewPurchaseOrderModel {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public ViewPurchaseOrderModel(final Connection connection) {
        this.connection = connection;
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
}