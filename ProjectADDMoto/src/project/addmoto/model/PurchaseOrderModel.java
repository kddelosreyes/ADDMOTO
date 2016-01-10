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
}
