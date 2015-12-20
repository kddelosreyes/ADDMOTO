package project.addmoto.database;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import project.addmoto.data.ProductLine;
import project.addmoto.data.Products;
import project.addmoto.data.SellerAccount;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Query {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public Query() {
        connection = new Database().getConnection();
    }
    
    public int countSellerAccount(String username, String password) {
        try {
            query = "SELECT count(*) FROM " + Database.SELLER_ACCOUNT_TABLE +
                    " WHERE " + Database.SELLER_ACCOUNT_USERNAME + " = '" + username + "' " +
                    " AND " + Database.SELLER_ACCOUNT_PASSWORD + " = '" + password + "';";
            
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
    
    public boolean doesUsernameExists(String username) {
        try {
            query = "SELECT count(*) FROM " + Database.SELLER_ACCOUNT_TABLE + 
                    " WHERE " + Database.SELLER_ACCOUNT_USERNAME + " = '" + username + "';";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                return resultSet.getInt(1) == 1;
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return false;
    }
    
    public SellerAccount getSellerAcount(String username, String password) {
        SellerAccount sellerAccount = null;
        
        try {
            query = "SELECT * FROM " + Database.SELLER_ACCOUNT_TABLE + 
                    " WHERE " + Database.SELLER_ACCOUNT_USERNAME + " = '" + username + "' " +
                    " AND " + Database.SELLER_ACCOUNT_PASSWORD + " = '" + password + "';";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt(Database.SELLER_ID);
                String firstName = resultSet.getString(Database.SELLER_ACCOUNT_FIRST_NAME);
                String lastName = resultSet.getString(Database.SELLER_ACCOUNT_LAST_NAME);
                String userName = resultSet.getString(Database.SELLER_ACCOUNT_USERNAME);
                String passsword = resultSet.getString(Database.SELLER_ACCOUNT_PASSWORD);
                
                sellerAccount = new SellerAccount(id, firstName, lastName,
                        userName, password);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        
        return sellerAccount;
    }
    
    public int insertLog(SellerAccount sellerAccount) {
        try {
            final DateFormat dateTimeFormat = new SimpleDateFormat("MMM dd YYYY, HH:mm:ss");
            Date date = new Date();
            String dateTime = dateTimeFormat.format(date);
            
            query = "INSERT INTO " + Database.LOGS_TABLE + " (" + Database.LOG_DATETIME + ", " +  Database.LOG_SELLER_ID_FK + ")"
                    + " VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dateTime);
            preparedStatement.setInt(2, sellerAccount.getSellerID());
            
            return preparedStatement.executeUpdate();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }
    
    public int createSellerAccount(String firstname, String lastname, String username, String password) {
        try {
            query = "INSERT INTO " + Database.SELLER_ACCOUNT_TABLE + " (" + Database.SELLER_ACCOUNT_FIRST_NAME + ", " + Database.SELLER_ACCOUNT_LAST_NAME + ", " + Database.SELLER_ACCOUNT_USERNAME + ", " +  Database.SELLER_ACCOUNT_PASSWORD + ")"
                    + " VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            return preparedStatement.executeUpdate();
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        return 0;
    }
    
    public Products getProduct(String productCode) {
        Products product = null;
        
        try {
            query = "SELECT * FROM " + Database.PRODUCTS_TABLE + 
                    " WHERE " + Database.PRODUCT_ADDMOTO_CODE + " = '" + productCode + "';";
            
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
    
    public ProductLine getProductLine(int productLineID) {
        ProductLine productLine = null;
        
        try {
            query = "SELECT * FROM " + Database.PRODUCT_LINE_TABLE + 
                    " WHERE " + Database.PRODUCT_LINE_ID + " = " + productLineID + ";";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                int id = resultSet.getInt(Database.PRODUCT_LINE_ID);
                String productLineName = resultSet.getString(Database.PRODUCT_LINE_NAME);
                
                productLine = new ProductLine(id, productLineName);
            }
        } catch(Exception exc) {
            exc.printStackTrace();
        }
        
        return productLine;
    }
}