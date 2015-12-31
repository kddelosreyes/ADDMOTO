package project.addmoto.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
}