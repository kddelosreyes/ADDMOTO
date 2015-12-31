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
import project.addmoto.database.Database;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class DashboardModel {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DashboardModel(final Connection connection) {
        this.connection = connection;

        System.out.println("Dashboard Model is called");
    }
    
    public int countTransactionsToday() {
        try {
            query = "SELECT count(*) FROM " + Database.RECEIPTS_TABLE +
                    " WHERE STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y') = CURDATE();";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }
    
    public double getTotalAmountToday() {
        try {
            query = "SELECT SUM(" + Database.RECEIPT_TOTAL_PRICE + ") FROM " + Database.RECEIPTS_TABLE +
                    " WHERE STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y') = CURDATE()" +
                    " GROUP BY STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y');";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }
    
    public double getAmountAverageToday() {
        try {
            query = "SELECT AVG(" + Database.RECEIPT_TOTAL_PRICE + ") FROM " + Database.RECEIPTS_TABLE +
                    " WHERE STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y') = CURDATE()" +
                    " GROUP BY STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y');";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }
    
    public double getGoodCostToday() {
        try {
            query = "SELECT SUM(S." + Database.SOLD_ITEM_TOTAL_PRICE + " - S." + Database.SOLD_ITEM_TOTAL_PROFIT +
                    ") FROM " + Database.RECEIPTS_TABLE + " R JOIN " + Database.SOLD_ITEMS_TABLE + " S " +
                    " WHERE R." + Database.RECEIPT_ID + " = S." + Database.RECEIPT_ID_FK + 
                    " AND STR_TO_DATE(R." + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y') = CURDATE()" +
                    " GROUP BY STR_TO_DATE(R." + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y');";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }
    
    public double getTotalSalesLastMonth() {
        try {
            query = "SELECT IFNULL(SUM(" + Database.RECEIPT_TOTAL_PRICE + "), 0) FROM " +
                    Database.RECEIPTS_TABLE + " WHERE YEAR(STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y')) = " +
                    "YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND MONTH(STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y')) " +
                    "=  MONTH(CURRENT_DATE - INTERVAL 1 MONTH)";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }
    
    public double getTotalSalesThisMonth() {
        try {
            query = "SELECT IFNULL(SUM(" + Database.RECEIPT_TOTAL_PRICE + "), 0) FROM " +
                    Database.RECEIPTS_TABLE + " WHERE YEAR(STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y')) = " +
                    "YEAR(CURRENT_DATE - INTERVAL 0 MONTH) AND MONTH(STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y')) " +
                    "=  MONTH(CURRENT_DATE - INTERVAL 0 MONTH)";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }
    
    public int getTotalTransactionsLastMonth() {
        try {
            query = "SELECT IFNULL(count(*), 0) FROM " +
                    Database.RECEIPTS_TABLE + " WHERE YEAR(STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y')) = " +
                    "YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND MONTH(STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y')) " +
                    "=  MONTH(CURRENT_DATE - INTERVAL 1 MONTH)";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }
    
    public int getTotalTransactionsThisMonth() {
        try {
            query = "SELECT IFNULL(count(*), 0) FROM " +
                    Database.RECEIPTS_TABLE + " WHERE YEAR(STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y')) = " +
                    "YEAR(CURRENT_DATE - INTERVAL 0 MONTH) AND MONTH(STR_TO_DATE(" + Database.RECEIPT_TRANSACTION_TIMESTAMP + ", '%b %d, %Y')) " +
                    "=  MONTH(CURRENT_DATE - INTERVAL 0 MONTH)";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }
    
    public double getTotalExpensesLastMonth() {
        try {
            query = "SELECT IFNULL(SUM(" + Database.EXPENSE_AMOUNT + "), 0) FROM " +
                    Database.EXPENSE_TABLE + " WHERE YEAR(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y')) = " +
                    "YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND MONTH(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y')) " +
                    "=  MONTH(CURRENT_DATE - INTERVAL 1 MONTH)";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }
    
    public double getTotalExpensesThisMonth() {
        try {
            query = "SELECT IFNULL(SUM(" + Database.EXPENSE_AMOUNT + "), 0) FROM " +
                    Database.EXPENSE_TABLE + " WHERE YEAR(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y')) = " +
                    "YEAR(CURRENT_DATE - INTERVAL 0 MONTH) AND MONTH(STR_TO_DATE(" + Database.EXPENSE_TIMESTAMP + ", '%b %d, %Y')) " +
                    "=  MONTH(CURRENT_DATE - INTERVAL 0 MONTH)";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0.00;
    }
}
