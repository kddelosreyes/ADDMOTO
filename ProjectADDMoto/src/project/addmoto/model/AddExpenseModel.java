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
import project.addmoto.data.Expense;
import project.addmoto.data.MonthAverage;
import project.addmoto.database.Database;

/**
 *
 * @author Kim Howel delos Reyes
 */
public class AddExpenseModel {

    private final Connection connection;
    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public AddExpenseModel(final Connection connection) {
        this.connection = connection;
        
        System.out.println("Add Expense Model is called.");
    }
    
    public int insertExpense(Expense expense) {
        try {
            query = "INSERT INTO " + Database.EXPENSE_TABLE + " (" +
                    Database.EXPENSE_AMOUNT + ", " + Database.EXPENSE_TIMESTAMP + ", " + Database.EXPENSE_REASON + ", " +
                    Database.EXPENSE_DETAIL + ", " + Database.EXPENSE_SELLER_ID_FK +
                    ") VALUES(?, ?, ?, ?, ?);";
            System.out.println("QUERY: " + query);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, expense.getExpenseAmount());
            preparedStatement.setString(2, expense.getExpenseTimestamp());
            preparedStatement.setString(3, expense.getExpenseReason());
            preparedStatement.setString(4, expense.getExpenseDetail());
            preparedStatement.setInt(5, expense.getSellerID());
            
            return preparedStatement.executeUpdate();
        } catch(Exception e) {
            return 0;
        }
    }
}
