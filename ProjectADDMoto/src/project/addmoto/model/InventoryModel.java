package project.addmoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class InventoryModel {
    
    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public InventoryModel(final Connection connection) {
        this.connection = connection;
        
        System.out.println("Inventory model is called.");
    }
    
}