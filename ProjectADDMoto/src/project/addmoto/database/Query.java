package project.addmoto.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class Query {
    
    private Connection connection;
    private String query;
    private Statement statement;
    private ResultSet resultSet;
    
    public Query() {
        connection = new Database().getConnection();
    }
    
    public int getSellerAccount(String username, String password) {
        try {
            query = "SELECT count(*) FROM " + Database.SELLER_ACCOUNT_TABLE +
                " WHERE " + Database.SELLER_ACCOUNT_USERNAME + " = '" + username + "' " +
                " AND " + Database.SELLER_ACCOUNT_PASSWORD + " = '" + password + "';";
            
            System.out.println(query);
            
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
}